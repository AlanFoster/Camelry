package me.alanfoster.camelus.blueprint.dom.inspection;

import me.alanfoster.camelus.TestHelper;
import me.alanfoster.camelus.blueprint.dom.inspectors.DuplicatedBeanIdInspection;

import static javax.swing.SwingUtilities.invokeAndWait;

/**
 * Ensures the user is alerted when a blueprint bean id is duplicated within
 * the same bundle
 */
public class DuplicatedBeanIdInspectionTest extends ModuleSupportHelper {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        // Explicitly register our inspection, otherwise intellij doesn't pick up the inspection!
        myFixture.enableInspections(new DuplicatedBeanIdInspection());
    }

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/blueprint/dom/inspection";
    }

    /**
     * Test to ensure that the user is told about the deprecated 'bean' attribute on
     * the camel method XML DSL.
     */
    public void testDuplicatedBeanIdWithinSameBundle() throws Exception {
        myFixture.copyFileToProject("OneTwoThreeFourBeans.xml", myFirstModule.getName() + "/src/OSGI-INF/blueprint/OneTwoThreeFourBeans.xml");
        myFixture.copyFileToProject("TwoThreeFourFiveBeansWithError.xml", myFirstModule.getName() + "/src/OSGI-INF/blueprint/TwoThreeFourFiveBeansWithError.xml");

        myFixture.configureFromTempProjectFile(myFirstModule.getName() + "/src/OSGI-INF/blueprint/TwoThreeFourFiveBeansWithError.xml");

        invokeAndWait(new Runnable() {
            @Override
            public void run() {
                myFixture.checkHighlighting(true, true, true);
            }
        });
    }

    /**
     * Checks highlighting doesn't result in any errors if the same bean IDs exist within
     * two different bundles.
     */
    public void testDuplicatedBeanIdWithinDifferentBundleResultingInNoErrors() throws Exception {
        // Copy two different files with duplicated bean ids into different bundles
        myFixture.copyFileToProject("OneTwoThreeFourBeans.xml", myFirstModule.getName() + "/src/OSGI-INF/blueprint/OneTwoThreeFourBeans.xml");
        myFixture.copyFileToProject("TwoThreeFourFiveBeansWithOutError.xml", mySecondModule.getName() + "/src/OSGI-INF/blueprint/TwoThreeFourFiveBeansWithOutError.xml");

        myFixture.configureFromTempProjectFile(mySecondModule.getName() + "/src/OSGI-INF/blueprint/TwoThreeFourFiveBeansWithOutError.xml");

        invokeAndWait(new Runnable() {
            @Override
            public void run() {
                myFixture.checkHighlighting(true, true, true);
            }
        });
    }


    /**
     * A test which adds a camel file to a non source folder, leading to no highlighting errors
     */
    public void testNonSourceFolderCamelRouteDoesntAffectHighlighting() throws Exception {
        myFixture.copyFileToProject("OneTwoThreeFourBeans.xml", myFirstModule.getName() + "/nonSourceFolder/OSGI-INF/blueprint/OneTwoThreeFourBeans.xml");
        myFixture.copyFileToProject("TwoThreeFourFiveBeansWithOutError.xml", myFirstModule.getName() + "/src/OSGI-INF/blueprint/TwoThreeFourFiveBeansWithOutError.xml");

        myFixture.configureFromTempProjectFile(myFirstModule.getName() + "/src/OSGI-INF/blueprint/TwoThreeFourFiveBeansWithOutError.xml");

        invokeAndWait(new Runnable() {
            @Override
            public void run() {
                myFixture.checkHighlighting(true, true, true);
            }
        });
    }
}
