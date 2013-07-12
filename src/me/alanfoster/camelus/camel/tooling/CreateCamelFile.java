package me.alanfoster.camelus.camel.tooling;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import me.alanfoster.camelus.icons.PluginIcons;
import me.alanfoster.camelus.blueprint.support.BlueprintFileTemplateManager;

import static me.alanfoster.camelus.CamelusBundle.message;

/**
 * Adds the option to create a new camel related file in the
 * 'New' context menu of camelus.
 * <p/>
 * Note this has been registered via an action in the META-INF/plugin.xml
 * file.
 * <p/>
 * This also makes use of the registered BlueprintFileTemplateManager file templates
 * so make sure to check that out for further details.
 *
 * @author Alan Foster
 * @version 1.0.0-SNAPSHOT
 * @see <a href="http://devnet.jetbrains.com/message/5316246">http://devnet.jetbrains.com/message/5316246</a>
 */
public class CreateCamelFile extends CreateFileFromTemplateAction {

    public CreateCamelFile() {
        super(message("camelus.templates.text"),
                message("camelus.templates.description"),
                PluginIcons.CAMEL);
    }

    @Override
    protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
        builder.setTitle(message("camelus.templates.title"))
                .addKind(message("camelus.templates.blueprint.route"), PluginIcons.CAMEL, BlueprintFileTemplateManager.BLUEPRINT_DEFINITION_FILE)
                .addKind(message("camelus.templates.blueprint.test"), PluginIcons.CAMEL, BlueprintFileTemplateManager.BLUEPRINT_TEST_FILE);
    }

    @Override
    protected String getActionName(PsiDirectory directory, String newName, String templateName) {
        return message("camelus.templates.actionname");
    }

    /**
     * {@inheritDoc}.
     * Decide when to show the CreateCamelFile option to the
     * user.
     * <p/>
     * For the minute, always.
     *
     * @param e
     */
    @Override
    public void update(AnActionEvent e) {
        // e.getData(...)
    }
}
