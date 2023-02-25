package de.janorico.jgl.components

import de.janorico.jgl.Input
import java.awt.BorderLayout
import javax.swing.*

/**
 * Simple input [panel][javax.swing.JPanel], uses an [de.janorico.jgl.Input] object.
 * @author Janosch Lion
 * @since 2.0
 * @see de.janorico.jgl.Input
 * @see de.janorico.jgl.helpers.Dialog
 * @see javax.swing.JTextField
 */
class InputPanel(private val input: Input, columns: Int = 20) : JPanel(BorderLayout()) {
    private val textField = JTextField(input.value, columns)

    init {
        add(JLabel(input.displayName + ": "), BorderLayout.WEST)
        add(textField)
    }

    fun getInput(): Input {
        input.value = getValue()
        return input
    }

    fun getValue(): String = textField.text
    fun getKey(): String = input.key
}
