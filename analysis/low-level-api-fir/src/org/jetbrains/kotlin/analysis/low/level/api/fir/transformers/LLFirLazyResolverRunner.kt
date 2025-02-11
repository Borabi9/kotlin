/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.low.level.api.fir.transformers

import org.jetbrains.kotlin.analysis.low.level.api.fir.api.targets.LLFirResolveTarget
import org.jetbrains.kotlin.analysis.low.level.api.fir.file.builder.LLFirLockProvider
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase
import org.jetbrains.kotlin.fir.resolve.ScopeSession
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirResolveContextCollector

internal object LLFirLazyResolverRunner {
    fun runLazyResolverByPhase(
        phase: FirResolvePhase,
        target: LLFirResolveTarget,
        scopeSession: ScopeSession,
        lockProvider: LLFirLockProvider,
        towerDataContextCollector: FirResolveContextCollector?,
    ) {
        val lazyResolver = LLFirLazyPhaseResolverByPhase.getByPhase(phase)
        lockProvider.withGlobalLock {
            lockProvider.withGlobalPhaseLock(phase) {
                lazyResolver.resolve(target, lockProvider, scopeSession, towerDataContextCollector)
            }
        }

        lazyResolver.checkIsResolved(target)
    }
}