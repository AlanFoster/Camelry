package me.alanfoster.intellij.camel.tooling.dsl.xml;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import me.alanfoster.intellij.camel.icons.CamelIcons;

/**
 * Adds the option to create a new camel related file in the
 * 'New' context menu of intellij.
 *
 * Note this has been registered via an action in the META-INF/plugin.xml
 * file.
 *
 * This also makes use of the registered BlueprintFileTemplateManager file templates
 * so make sure to check that out for further details.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see <a href="http://devnet.jetbrains.com/message/5316246">http://devnet.jetbrains.com/message/5316246</a>
 */
public class CreateCamelFile extends CreateFileFromTemplateAction {

    public CreateCamelFile() {
        super("Create a camel file",
              "Create a new camel file",
               CamelIcons.CAMEL);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle("Create new Camel File")
                .addKind("Blueprint Definition", CamelIcons.CAMEL, BlueprintFileTemplateManager.BLUEPRINT_DEFINITION_FILE)
                .addKind("Blueprint Test File", CamelIcons.CAMEL, BlueprintFileTemplateManager.BLUEPRINT_TEST_FILE);
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return "This is the action name";
    }

    /**
     * {@inheritDoc}.
     * Decide when to show the CreateCamelFile option to the
     * user.
     *
     * For the minute, always.
     *
     * @param e
     */
    @Override
    public void update(AnActionEvent e) {
        // e.getData(...)
    }
}
