/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.targets.js.npm.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.file.FileCollection
import org.gradle.api.file.RegularFile
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.*
import org.gradle.work.DisableCachingByDefault
import org.gradle.work.NormalizeLineEndings
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin.Companion.kotlinNodeJsExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.KotlinNpmResolutionManager
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmProject
import org.jetbrains.kotlin.gradle.targets.js.npm.UsesKotlinNpmResolutionManager
import org.jetbrains.kotlin.gradle.targets.js.npm.asNodeJsEnvironment
import org.jetbrains.kotlin.gradle.targets.js.npm.resolver.KotlinRootNpmResolver
import org.jetbrains.kotlin.gradle.utils.getFile
import java.io.File

@DisableCachingByDefault
abstract class KotlinNpmInstallTask :
    DefaultTask(),
    UsesKotlinNpmResolutionManager {
    init {
        check(project == project.rootProject)
    }

    // Only in configuration phase
    // Not part of configuration caching

    private val nodeJs: NodeJsRootExtension
        get() = project.rootProject.kotlinNodeJsExtension

    private val rootResolver: KotlinRootNpmResolver
        get() = nodeJs.resolver

    private val packagesDir: Provider<Directory>
        get() = nodeJs.projectPackagesDirectory

    // -----

    private val nodsJsEnvironment by lazy {
        nodeJs.requireConfigured().asNodeJsEnvironment
    }

    private val packageManagerEnv by lazy {
        nodeJs.packageManagerExtension.get().environment
    }

    @Input
    val args: MutableList<String> = mutableListOf()

    @get:PathSensitive(PathSensitivity.ABSOLUTE)
    @get:IgnoreEmptyDirectories
    @get:NormalizeLineEndings
    @get:InputFiles
    val preparedFiles: Collection<File> by lazy {
        nodeJs.packageManagerExtension.get().packageManager.preparedFiles(nodsJsEnvironment)
    }

    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:IgnoreEmptyDirectories
    @get:NormalizeLineEndings
    @get:InputFiles
    val packageJsonFiles: FileCollection = project.objects.fileCollection().from(
        {
            rootResolver.projectResolvers.values
                .flatMap { it.compilationResolvers }
                .map { it.compilationNpmResolution }
                .map { resolution ->
                    val name = resolution.npmProjectName
                    packagesDir.map { it.dir(name).file(NpmProject.PACKAGE_JSON) }
                }
        }
    )

    @get:OutputFiles
    val additionalFiles: FileCollection by lazy {
        nodeJs.packageManagerExtension.get().additionalInstallOutput
    }

    @Deprecated(
        "This property is deprecated and will be removed in future. Use additionalFiles instead",
        replaceWith = ReplaceWith("additionalFiles")
    )
    @get:Internal
    val yarnLockFile: Provider<RegularFile> = nodeJs.rootPackageDirectory.map { it.file("yarn.lock") }

    @Deprecated(
        "This property is deprecated and will be removed in future. Use additionalFiles instead",
        replaceWith = ReplaceWith("additionalFiles")
    )
    @get:Internal
    val yarnLock: File
        get() = yarnLockFile.getFile()

    // node_modules as OutputDirectory is performance problematic
    // so input will only be existence of its directory
    @get:Internal
    val nodeModules: Provider<Directory> = nodeJs.rootPackageDirectory.map { it.dir("node_modules") }

    @TaskAction
    fun resolve() {
        npmResolutionManager.get()
            .installIfNeeded(
                args = args,
                services = services,
                logger = logger,
                nodsJsEnvironment,
                packageManagerEnv
            ) ?: throw (npmResolutionManager.get().state as KotlinNpmResolutionManager.ResolutionState.Error).wrappedException
    }

    companion object {
        const val NAME = "kotlinNpmInstall"
    }
}