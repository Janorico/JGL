package de.janorico.jgl.components

import java.awt.*
import javax.swing.*

/**
 * A combo box that contains all system fonts, call the reload function to reload the list.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JComboBox
 * @see java.awt.GraphicsEnvironment.getAllFonts
 */
class FontComboBox(defaultFont: String? = null) : JComboBox<String>() {
    /**
     * Default constructor.
     * @param defaultFont The font that is selected on start.
     */
    init {
        renderer = ListCellRenderer { list: JList<out String>?, value: String, index: Int, isSelected: Boolean, cellHasFocus: Boolean ->
            return@ListCellRenderer JLabel().apply {
                font = Font(value, Font.PLAIN, 12)
                text = font.name
                toolTipText = "Family: ${font.family} Name: ${font.fontName}"
                if (cellHasFocus) requestFocus()
                if (list != null) {
                    background = if (isSelected) list.selectionBackground else list.background
                    foreground = if (isSelected) list.selectionForeground else list.foreground
                }
            }
        }
        loadSystemFonts()
        if (defaultFont != null) selectedItem = defaultFont
    }

    fun setItems(items: Array<String>) {
        model = DefaultComboBoxModel(items)
    }

    override fun getSelectedItem(): String? {
        return super.getSelectedItem()?.toString()
    }

    fun loadSystemFonts() {
        val fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().allFonts
        setItems(Array(fonts.size) {
            return@Array fonts[it].name
        })
    }
}
