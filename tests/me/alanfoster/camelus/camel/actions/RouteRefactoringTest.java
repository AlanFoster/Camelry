package me.alanfoster.camelus.camel.actions;

import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import junit.framework.Assert;
import me.alanfoster.camelus.TestHelper;
import org.jetbrains.annotations.Nullable;

/**
 * Tests to ensure that route refactoring works correctly
 */
public class RouteRefactoringTest extends LightCodeInsightFixtureTestCase {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestDataPath() + "/camel/actions/routeRefactoring";
    }

    public void testSingleElementRefactor() {
        performRouteRefactoring();
    }

    // TODO
    public void ignoreMultiElementRefactor() {
        performRouteRefactoring();
    }

    // TODO
    public void ignoreNoElementRefactor() {
    }

    // TODO
    public void ignoreInvalidEndElementRefactor() {
    }

    /**
     * We need to ensure the ensure will get a useful error message if they attempt to refactor
     * elements which don't exist within a camel route.
     */
    public void testNonCamelRouteRefactor() {
        try {
            performRouteRefactoring();
            fail("An error should have been thrown during refactoring, as the user hasn't selected routes within a camel context");
        } catch(CommonRefactoringUtil.RefactoringErrorHintException refactoringErrorMessage) {
            String expectedMessage = "Cannot perform refactoring.\n" +
                    "The selected block should be a valid selection under a camel route.";
            Assert.assertEquals(expectedMessage, refactoringErrorMessage.getMessage());
        }
    }

    /**
     * When the user is requested to input the new route uri, they can press cancel.
     * Under this circumstance, we must ensure we don't continue refactoring.
     */
    public void testUserPressesCancel() {
        performRouteRefactoring(null);
    }

    /**
     * The refactoring support should only work within a given camel route,
     * and therefore should not cause any errors/refactoring to occur when not
     * inside the context of a came lroute.
     */
    public void testRefactoringOutsideOfRoute() {
        Assert.assertEquals(true, true);
    }


    private void performRouteRefactoring() {
        performRouteRefactoring("direct:refactoredRoute");
    }

    /**
     * Perform the refactoring test, following the convention of getTestName() returning the
     * correct resource prefix in the routeRefactoring folder.
     * @param routeUri The new route URI to create. This may be null, to represent the user
     *                 cancelling the input dialogue.
     */
    private void performRouteRefactoring(@Nullable final String routeUri) {
        String resourceName = getTestName(false);
        myFixture.configureByFile(resourceName + ".xml");
        // Override the route name
        Messages.setTestInputDialog(new TestInputDialog() {
            @Override
            public String show(String message) {
                return routeUri;
            }
        });
        new RouteRefactoringSupportProvider()
                .getExtractMethodHandler()
                .invoke(myFixture.getProject(), myFixture.getEditor(), myFixture.getFile(), null);
        myFixture.checkResultByFile(resourceName + "_after.xml");
    }

}
