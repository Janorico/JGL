package de.janorico.jgl.helpers

import de.janorico.jgl.JGL
import java.awt.*
import javax.swing.JDialog
import javax.swing.JPanel

/**
 * Contains methods for creating and showing dialogs.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JDialog
 */
object Dialog {
    /**
     * Shows a dialog with OK and Cancel buttons.
     */
    fun showDialog(title: String, content: (dialog: JDialog) -> Component, onOk: () -> Unit, onCancel: () -> Unit) {
        createDialog(title, content, onOk, onCancel).isVisible = true
    }

    /**
     * Shows a simple dialog.
     */
    fun showDialog(title: String, content: (dialog: JDialog) -> Component, bottomContent: (dialog: JDialog) -> Component? = { null }) {
        createDialog(title, content, bottomContent).isVisible = true
    }

    /**
     * Creates a dialog with OK and Cancel buttons.
     */
    fun createDialog(title: String, content: (dialog: JDialog) -> Component, onOk: () -> Unit, onCancel: () -> Unit = {}): JDialog =
        createDialog(title, content) { dialog: JDialog ->
            val panel = JPanel(FlowLayout(FlowLayout.RIGHT))
            panel.add(Button.create("OK", "Approves the dialog.") {
                dialog.dispose()
                onOk()
            })
            panel.add(Button.create("Cancel", "Cancels the dialog.") {
                dialog.dispose()
                onCancel()
            })
            return@createDialog panel
        }

    /**
     * Creates a simple dialog.
     */
    fun createDialog(title: String, content: (dialog: JDialog) -> Component, bottomContent: (dialog: JDialog) -> Component? = { null }): JDialog {
        val dialog = JDialog(JGL.dialogOwner)
        dialog.add(content(dialog))
        val bottomContentResult = bottomContent(dialog)
        if (bottomContentResult != null) dialog.add(bottomContentResult, BorderLayout.SOUTH)

        dialog.pack()
        dialog.defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE
        dialog.isModal = true
        dialog.setLocationRelativeTo(JGL.dialogOwner)
        dialog.title = title
        return dialog
    }
}
