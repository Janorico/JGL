package de.janorico.jgl.components

import java.awt.*
import javax.swing.*
import javax.swing.event.*

/**
 * @author Janosch Lion
 * @since 1.0
 * @see de.janorico.jgl.components.FontComboBox
 * @see javax.swing.JSpinner
 */
class FontChooser : JPanel {
    companion object {
        private const val DEFAULT_SIZE_VALUE = 12
        private const val DEFAULT_MIN_SIZE = 2
        private const val DEFAULT_MAX_SIZE = 200
        private const val DEFAULT_STEP_SIZE = 1

        private const val DEFAULT_FONT_NAME = "Arial"
    }

    private val fontComboBox = FontComboBox(DEFAULT_FONT_NAME)
    private val sizeSpinnerModel = SpinnerNumberModel(DEFAULT_SIZE_VALUE, DEFAULT_MIN_SIZE, DEFAULT_MAX_SIZE, DEFAULT_STEP_SIZE)
    private var sizeValue: Int = DEFAULT_SIZE_VALUE
    private var fontNameValue: String = DEFAULT_FONT_NAME
    private val changeListeners = ArrayList<ChangeListener>()

    constructor(defaultFont: Font? = null) : this(null, null, null, defaultFont)

    constructor(minimumSize: Int, maximumSize: Int, defaultFont: Font? = null) : this(minimumSize, maximumSize, null, defaultFont)

    constructor(maximumSize: Int, defaultFont: Font? = null) : this(null, maximumSize, null, defaultFont)

    constructor(stepSize: Int?, defaultFont: Font? = null) : this(null, null, stepSize, defaultFont)

    constructor(minimumSize: Int?, maximumSize: Int?, stepSize: Int?, defaultFont: Font? = null) {
        layout = BorderLayout()
        if (minimumSize != null) sizeSpinnerModel.minimum = minimumSize
        if (maximumSize != null) sizeSpinnerModel.maximum = maximumSize
        if (stepSize != null) sizeSpinnerModel.stepSize = stepSize
        val sizeSpinner = JSpinner(sizeSpinnerModel)
        sizeSpinner.addChangeListener {
            sizeValue = sizeSpinnerModel.number.toInt()
            updated()
        }
        fontComboBox.addItemListener {
            val newValue = fontComboBox.selectedItem
            if (newValue != null) fontNameValue = newValue
            updated()
        }
        if (defaultFont != null) {
            sizeSpinnerModel.value = defaultFont.size
            fontComboBox.selectedItem = defaultFont.name
        }
        add(fontComboBox)
        add(sizeSpinner, BorderLayout.EAST)
        val minimumHeight1 = fontComboBox.minimumSize.height
        val minimumHeight2 = sizeSpinner.minimumSize.height
        setMinimumSize(
            Dimension(
                fontComboBox.minimumSize.width + sizeSpinner.minimumSize.width,
                if (minimumHeight1 >= minimumHeight2) minimumHeight1 else minimumHeight2
            )
        )
    }

    fun getActiveFontSize(): Int = sizeValue

    fun setActiveFontSize(size: Int) {
        sizeValue = size
        sizeSpinnerModel.value = size
    }

    fun getActiveFontName(): String = fontNameValue
    fun setActiveFontName(name: String) {
        fontNameValue = name
        fontComboBox.selectedItem = name
    }

    fun getActiveFont(style: Int): Font = Font(getActiveFontName(), style, getActiveFontSize())

    fun setActiveFont(font: Font) {
        setActiveFontSize(font.size)
        setActiveFontName(font.name)
    }

    fun addChangeListener(listener: ChangeListener) {
        changeListeners.add(listener)
    }

    fun removeChangeListener(listener: ChangeListener) {
        changeListeners.remove(listener)
    }

    fun reloadFonts() {
        fontComboBox.loadSystemFonts()
    }

    private fun updated() {
        for (listener in changeListeners) {
            listener.stateChanged(ChangeEvent(this))
        }
    }
}
