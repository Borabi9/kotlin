/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.standalone.fir.test.cases.generated.cases.components.scopeProvider;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.analysis.api.standalone.fir.test.configurators.AnalysisApiFirStandaloneModeTestConfiguratorFactory;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfiguratorFactoryData;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfigurator;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.TestModuleKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.FrontendKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisSessionMode;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiMode;
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.components.scopeProvider.AbstractCombinedDeclaredMemberScopeTest;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope")
@TestDataPath("$PROJECT_ROOT")
public class FirStandaloneNormalAnalysisSourceModuleCombinedDeclaredMemberScopeTestGenerated extends AbstractCombinedDeclaredMemberScopeTest {
    @NotNull
    @Override
    public AnalysisApiTestConfigurator getConfigurator() {
        return AnalysisApiFirStandaloneModeTestConfiguratorFactory.INSTANCE.createConfigurator(
            new AnalysisApiTestConfiguratorFactoryData(
                FrontendKind.Fir,
                TestModuleKind.Source,
                AnalysisSessionMode.Normal,
                AnalysisApiMode.Standalone
            )
        );
    }

    @Test
    public void testAllFilesPresentInCombinedDeclaredMemberScope() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("class.kt")
    public void testClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/class.kt");
    }

    @Test
    @TestMetadata("classWithJavaSuperclass.kt")
    public void testClassWithJavaSuperclass() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/classWithJavaSuperclass.kt");
    }

    @Test
    @TestMetadata("enumClass.kt")
    public void testEnumClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumClass.kt");
    }

    @Test
    @TestMetadata("enumClassWithAbstractMembers.kt")
    public void testEnumClassWithAbstractMembers() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumClassWithAbstractMembers.kt");
    }

    @Test
    @TestMetadata("enumClassWithFinalMembers.kt")
    public void testEnumClassWithFinalMembers() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumClassWithFinalMembers.kt");
    }

    @Test
    @TestMetadata("enumEntryInitializer.kt")
    public void testEnumEntryInitializer() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumEntryInitializer.kt");
    }

    @Test
    @TestMetadata("enumEntryInitializerWithEmptyBody.kt")
    public void testEnumEntryInitializerWithEmptyBody() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumEntryInitializerWithEmptyBody.kt");
    }

    @Test
    @TestMetadata("enumEntryInitializerWithFinalEnumMember.kt")
    public void testEnumEntryInitializerWithFinalEnumMember() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumEntryInitializerWithFinalEnumMember.kt");
    }

    @Test
    @TestMetadata("enumEntryInitializerWithOverriddenMember.kt")
    public void testEnumEntryInitializerWithOverriddenMember() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/enumEntryInitializerWithOverriddenMember.kt");
    }

    @Test
    @TestMetadata("innerClass.kt")
    public void testInnerClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/innerClass.kt");
    }

    @Test
    @TestMetadata("javaClass.kt")
    public void testJavaClass() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/javaClass.kt");
    }

    @Test
    @TestMetadata("javaDeclaredEnhancementScope.kt")
    public void testJavaDeclaredEnhancementScope() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/javaDeclaredEnhancementScope.kt");
    }

    @Test
    @TestMetadata("javaDeclaredInheritList.kt")
    public void testJavaDeclaredInheritList() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/javaDeclaredInheritList.kt");
    }

    @Test
    @TestMetadata("javaInnerClassConstructor.kt")
    public void testJavaInnerClassConstructor() throws Exception {
        runTest("analysis/analysis-api/testData/components/scopeProvider/combinedDeclaredMemberScope/javaInnerClassConstructor.kt");
    }
}
