package de.janorico.jgl.helpers

import de.janorico.jgl.JGL
import java.awt.Toolkit
import javax.swing.Icon
import javax.swing.JOptionPane

/**
 * Contains methods for showing option panes.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JOptionPane
 */
object OptionPane {
    // CONFIRM DIALOG

    fun showSaveOnCloseDialog(): Int = showConfirmDialog("Do you want to save before close?", "Close", JOptionPane.YES_NO_CANCEL_OPTION)

    fun overwriteFileDialog(): Int = showConfirmDialog("Overwrite file?", "Overwrite file", JOptionPane.YES_NO_OPTION)

    fun showConfirmDialog(message: Any, title: String, optionType: Int, messageType: Int = JOptionPane.QUESTION_MESSAGE, icon: Icon? = null): Int =
        JOptionPane.showConfirmDialog(JGL.dialogOwner, message, title, optionType, messageType, icon)

    // INPUT DIALOG

    fun showOptionsDialog(
        message: Any,
        title: String,
        options: Array<Any>,
        initialOption: Any? = null,
        messageType: Int = JOptionPane.QUESTION_MESSAGE,
        icon: Icon? = null,
    ): String? = showInputDialog(message, title, messageType, icon, options, initialOption) as String?

    fun showPromptDialog(message: Any, title: String, initialValue: Any? = null, messageType: Int = JOptionPane.QUESTION_MESSAGE, icon: Icon? = null): String? =
        showInputDialog(message, title, messageType, icon, null, initialValue) as String?

    fun showInputDialog(
        message: Any,
        title: String,
        messageType: Int,
        icon: Icon? = null,
        selectionValues: Array<Any>? = null,
        initialSelectionValue: Any? = null,
    ): Any? = JOptionPane.showInputDialog(JGL.dialogOwner, message, title, messageType, icon, selectionValues, initialSelectionValue)

    // MESSAGE DIALOG

    fun showPlain(message: Any, title: String, beep: Boolean = false, icon: Icon? = null) {
        showMessageDialog(message, title, JOptionPane.PLAIN_MESSAGE, beep, icon)
    }

    fun showInformation(message: Any, beep: Boolean = false, icon: Icon? = null) {
        showMessageDialog(message, "Information", JOptionPane.INFORMATION_MESSAGE, beep, icon)
    }

    fun showWarning(message: Any, beep: Boolean = false, icon: Icon? = null) {
        showMessageDialog(message, "Warning", JOptionPane.WARNING_MESSAGE, beep, icon)
    }

    fun showError(message: Any, beep: Boolean = true, icon: Icon? = null) {
        showMessageDialog(message, "Error", JOptionPane.ERROR_MESSAGE, beep, icon)
    }

    fun showMessageDialog(message: Any, title: String, messageType: Int, beep: Boolean = false, icon: Icon? = null) {
        if (beep) Toolkit.getDefaultToolkit().beep()
        JOptionPane.showMessageDialog(JGL.dialogOwner, message, title, messageType, icon)
    }

    // EXTRA METHOD
    fun showException(exception: Exception, beep: Boolean = true, icon: Icon? = null) {
        showError("An '${exception::class.qualifiedName}' with message '${exception.message}' has occurred.", beep, icon)
    }
}
