/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle

import org.gradle.util.GradleVersion
import org.jetbrains.kotlin.gradle.testbase.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import kotlin.io.path.appendText


@DisplayName("Kapt 4 base checks")
class Kapt4IT : Kapt3IT() {
    override val defaultBuildOptions = super.defaultBuildOptions.copyEnsuringK2()

    @DisplayName("Kapt doesn't run in fallback mode with languageVersion = 2.0")
    @GradleTest
    fun noFallBackModeWithLanguageVersion2_0(gradleVersion: GradleVersion) {
        project("simple".withPrefix, gradleVersion) {
            build("build") {
                assertKaptSuccessful()
                assertTasksExecuted(":kaptGenerateStubsKotlin", ":kaptKotlin", ":compileKotlin")
                assertOutputDoesNotContain("Falling back to 1.9.")
            }
        }
    }

    @DisplayName("KT-61879: K2 KAPT works with proguarded compiler jars and enum class")
    @GradleTest
    fun testEnumClass(gradleVersion: GradleVersion) {
        project("simple".withPrefix, gradleVersion) {
            javaSourcesDir().resolve("test.kt").appendText("\nenum class TestEnum")
            build("build") {
                assertKaptSuccessful()
                assertFileExists(kotlinClassesDir().resolve("example/TestEnum.class"))
            }
        }
    }
}

@DisplayName("Kapt 4 with classloaders cache")
class Kapt4ClassLoadersCacheIT : Kapt3ClassLoadersCacheIT() {
    override val defaultBuildOptions = super.defaultBuildOptions.copyEnsuringK2()

    @Disabled("Enable when KT-61845 is fixed")
    override fun testKt18799(gradleVersion: GradleVersion) {}

    @Disabled("KT-63102 Incremental compilation doesn't work in 2.0")
    override fun testSimpleWithIC(gradleVersion: GradleVersion) {}

    @Disabled("KT-63102 Incremental compilation doesn't work in 2.0")
    override fun testSimpleWithIC_withClasspathSnapshot(gradleVersion: GradleVersion) {}

    @Disabled("KT-63102 Incremental compilation doesn't work in 2.0")
    override fun testChangeClasspathICRebuild(gradleVersion: GradleVersion) {}
}
