package de.janorico.jgl.components

import de.janorico.jgl.JGL
import java.awt.*
import java.awt.event.*
import javax.swing.*
import javax.swing.border.LineBorder

/**
 * Simple color picker. Example:
 * fun main() {
 *     JFrame().apply Frame@{
 *         add(JPanel().apply {
 *             val picker = ColorPicker(Color.YELLOW)
 *             add(picker)
 *             add(Button.create("Print color") { println(Integer.toHexString(picker.color.rgb)) })
 *             add(Button.create("Set background") { this@Frame.background = picker.color })
 *         })
 *     }.isVisible = true
 * }
 * @author Janosch Lion
 * @since 1.0
 * @see java.awt.Color
 * @see javax.swing.JLabel
 * @see javax.swing.JColorChooser
 */
class ColorPicker(initialColor: Color) : JLabel() {
    var color = initialColor
        set(value) {
            field = value
            updateColor()
        }

    init {
        border = LineBorder(Color.BLACK)
        foreground = Color.GRAY
        isOpaque = true
        updateColor()
        val size = Dimension(64, 24)
        setSize(size)
        minimumSize = size
        preferredSize = size
        maximumSize = size
        addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                val result = JColorChooser.showDialog(JGL.dialogOwner, "Choose color", color)
                if (result != null) {
                    color = result
                }
            }
        })
    }

    private fun updateColor() {
        background = color
        text = "#" + Integer.toHexString(color.rgb)
        toolTipText = "${color.red}, ${color.green}, ${color.blue}"
    }
}
