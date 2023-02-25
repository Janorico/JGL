package de.janorico.jgl

import java.awt.*
import kotlin.math.*

object JGL {
    /**
     * The name of the program that use JGL.
     */
    var programName: String? = null

    /**
     * The active window.
     */
    var dialogOwner: Window? = null

    /**
     * Scaling proportional.
     * @param targetSize The target size.
     * @param originalWidth The original width.
     * @param originalHeight The original height.
     * @return A new dimension, the largest edge is equal to target size.
     */
    fun scaleProportional(targetSize: Int, originalWidth: Int, originalHeight: Int): Dimension =
        if (originalWidth > targetSize || originalHeight > targetSize) { // Original size is greater than target size.
            val divider = max(originalWidth, originalHeight).toDouble() / targetSize.toDouble()
            Dimension((originalWidth.toDouble() / divider).roundToInt(), (originalHeight.toDouble() / divider).roundToInt())
        } else if (originalWidth < targetSize || originalHeight < targetSize) { // Original size is smaller than target size.
            val factor = targetSize / max(originalWidth, originalHeight)
            Dimension((originalWidth * factor), (originalHeight * factor))
        } else Dimension(originalWidth, originalHeight) // Original size is target size, don't scale.
}
