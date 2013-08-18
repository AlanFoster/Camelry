package me.alanfoster.camelry.camel.actions;

import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TestInputDialog;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import junit.framework.Assert;
import me.alanfoster.camelry.CamelryProjectDescriptorBuilder;
import me.alanfoster.camelry.CamelryTestSupport;
import me.alanfoster.camelry.TestHelper;
import org.jetbrains.annotations.Nullable;

import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.CreateCamelryProject;
import static me.alanfoster.camelry.CamelryProjectDescriptorBuilder.blueprintFiles;

/**
 * Tests to ensure that route refactoring works correctly
 */
public class RouteRefactoringTest extends CamelryTestSupport {

    @Override
    public String getTestDataPath() {
        return TestHelper.getTestRoot() + "/camel/actions/routeRefactoring";
    }

    /****************************************************************************
     * Happy Path tests which should refactor successfully
     ****************************************************************************/

    public void testSingleElementRefactor() {
        performRouteRefactoring();
    }

    public void testMultiElementRefactor() {
        performRouteRefactoring();
    }

    public void testRefactorWithLeadingComment() {
        performRouteRefactoring();
    }

    /**
     * When the user is requested to input the new route uri, they can press cancel.
     * Under this circumstance, we must ensure we don't continue refactoring.
     */
    public void testUserPressesCancel() {
        performRouteRefactoring(null);
    }

    /****************************************************************************
     * Tests which are expected to result in a user error message
     ****************************************************************************/

    public void testNoElementsSelected() {
        performRouteRefactoringWithUserError("Please select the elements you wish to extract.");
    }

    public void testNoElementRefactor() {
        performRouteRefactoringWithUserError();
    }

    public void testSelectEndElementRefactor() {
        performRouteRefactoring();
    }

    /**
     * The refactoring support should only work within a given camel route
     */
    public void testNonCamelRouteRefactor() {
        performRouteRefactoringWithUserError();
    }

    public void testInvalidSelection() {
        performRouteRefactoringWithUserError();
    }

    /****************************************************************************
     * Helpers specific to RouteRefactoring
     ****************************************************************************/

    private void performRouteRefactoring() {
        performRouteRefactoring("direct:refactoredRoute", false);
    }

    private void performRouteRefactoring(String routeUri) {
        performRouteRefactoring(routeUri, false);
    }

    private void performRouteRefactoring(boolean isErrorExpected) {
        performRouteRefactoring("direct:refactoredRoute", isErrorExpected);
    }

    private void performRouteRefactoringWithUserError(){
        performRouteRefactoringWithUserError("The selected block should be a valid selection under a camel route.");
    }

    /**
     * Attempts to perform a user refactor, and expects for a user error to occur.
     * If the refactor does not result in a error message, then this method will fail.
     */
    private void performRouteRefactoringWithUserError(String expectedUserError) {
        try {
            performRouteRefactoring();
            fail("An error should have been thrown during refactoring, as the user hasn't selected routes within a camel context");
        } catch (CommonRefactoringUtil.RefactoringErrorHintException refactoringErrorMessage) {
            String expectedMessage = "Cannot perform refactoring.\n" +
                    expectedUserError;
            Assert.assertEquals(expectedMessage, refactoringErrorMessage.getMessage());
        }
    }

    /**
     * Perform the refactoring test, following the convention of getTestName() returning the
     * correct resource prefix in the routeRefactoring folder.
     *
     * @param routeUri  The new route URI to create. This may be null, to represent the user
     *                  cancelling the input dialogue.
     * @param isErrorExpected If true the test dialogue
     */
    private void performRouteRefactoring(@Nullable final String routeUri, boolean isErrorExpected) {
        String resourceName = getTestName(false);

        CreateCamelryProject(myFixture)
                .with(blueprintFiles(resourceName + ".xml"))
                .withOpenedFileFromTempProject(blueprintFiles(resourceName + ".xml"));

        // Override the route name if we don't expect a validation error
        if(!isErrorExpected) {
            Messages.setTestInputDialog(new TestInputDialog() {
                @Override
                public String show(String message) {
                    return routeUri;
                }
            });
        }

        new RouteRefactoringSupportProvider()
                .getExtractMethodHandler()
                .invoke(myFixture.getProject(), myFixture.getEditor(), myFixture.getFile(), null);

        myFixture.checkResultByFile(resourceName + "_after.xml");
    }

}
