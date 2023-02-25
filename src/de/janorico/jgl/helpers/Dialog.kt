package de.janorico.jgl.helpers

import de.janorico.jgl.*
import de.janorico.jgl.components.InputPanel
import java.awt.*
import java.awt.event.KeyEvent
import javax.swing.*

/**
 * Contains methods for creating and showing dialogs.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JDialog
 */
object Dialog {
    /**
     * Shows an input dialog with multiple values.
     * @see createInputDialog
     * @see de.janorico.jgl.Input
     * @see de.janorico.jgl.components.InputPanel
     */
    fun showInputDialog(
        title: String,
        inputs: Array<Input>,
        onOk: (states: Map<String, String>) -> Unit,
        onCancel: (states: Map<String, String>) -> Unit,
        titlePrefix: Boolean = true,
    ) {
        createInputDialog(title, inputs, onOk, onCancel, titlePrefix).isVisible = true
    }

    /**
     * Creates an input dialog with multiple values.
     * @see de.janorico.jgl.Input
     * @see de.janorico.jgl.components.InputPanel
     */
    fun createInputDialog(
        title: String,
        inputs: Array<Input>,
        onOk: (states: Map<String, String>) -> Unit,
        onCancel: (states: Map<String, String>) -> Unit,
        titlePrefix: Boolean = true,
    ): JDialog {
        val panels = Array(inputs.size) {
            InputPanel(inputs[it])
        }
        return createDialog(title, { JPanel(GridLayout(inputs.size, 1)).apply { for (panel in panels) add(panel) } }, {
            onOk(panelsToMap(panels))
        }, {
            onCancel(panelsToMap(panels))
        }, titlePrefix)
    }

    private fun panelsToMap(panels: Array<InputPanel>): Map<String, String> = buildMap {
        for (panel in panels) this[panel.getKey()] = panel.getValue()
    }

    /**
     * Shows a dialog with OK and Cancel buttons.
     */
    fun showDialog(title: String, content: (dialog: JDialog) -> JComponent, onOk: () -> Unit, onCancel: () -> Unit, titlePrefix: Boolean = true) {
        createDialog(title, content, onOk, onCancel, titlePrefix).isVisible = true
    }

    /**
     * Shows a simple dialog.
     */
    fun showDialog(
        title: String,
        content: (dialog: JDialog) -> JComponent,
        bottomContent: (dialog: JDialog) -> JComponent? = { null },
        titlePrefix: Boolean = true,
    ) {
        createDialog(title, content, bottomContent, titlePrefix).isVisible = true
    }

    /**
     * Creates a dialog with OK and Cancel buttons.
     */
    fun createDialog(
        title: String,
        content: (dialog: JDialog) -> JComponent,
        onOk: () -> Unit,
        onCancel: () -> Unit = {},
        titlePrefix: Boolean = true,
    ): JDialog = createDialog(title, content, BottomPanel@{ dialog: JDialog ->
        val panel = JPanel(FlowLayout(FlowLayout.RIGHT))
        val okButton = Button.create("OK", "Approves the dialog.") {
            dialog.dispose()
            onOk()
        }
        panel.add(okButton)
        dialog.rootPane.defaultButton = okButton
        panel.add(Button.create("Cancel", "Cancels the dialog.") {
            dialog.dispose()
            onCancel()
        })
        return@BottomPanel panel
    }, titlePrefix)

    /**
     * Creates a simple dialog.
     */
    fun createDialog(
        title: String,
        content: (dialog: JDialog) -> JComponent,
        bottomContent: (dialog: JDialog) -> JComponent? = { null },
        titlePrefix: Boolean = true,
    ): JDialog {
        val dialog = JDialog(JGL.dialogOwner)
        val content = content(dialog)
        content.registerKeyboardAction({ dialog.dispose() }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
        dialog.add(content)
        val bottomContentResult = bottomContent(dialog)
        if (bottomContentResult != null) dialog.add(bottomContentResult, BorderLayout.SOUTH)

        dialog.pack()
        dialog.defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE
        dialog.isModal = true
        dialog.setLocationRelativeTo(JGL.dialogOwner)
        dialog.title = if (titlePrefix) "${JGL.programName}: $title" else title
        return dialog
    }
}
