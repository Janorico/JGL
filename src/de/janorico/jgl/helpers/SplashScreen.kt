package de.janorico.jgl.helpers

import de.janorico.jgl.JGL
import java.awt.*
import javax.swing.*
import kotlin.math.min

/**
 * Contain methods for showing simple splash screes.
 * @author Janosch Lion
 * @since 2.0
 * @see javax.swing.JWindow
 */
object SplashScreen {
    /**
     * Shows a splash screen for the given time.
     * @see java.lang.Thread.sleep
     */
    fun show(icon: ImageIcon, millis: Long, scale: Boolean = true) {
        val window = create(icon, scale)
        window.isVisible = true
        Thread.sleep(millis)
        window.dispose()
    }

    /**
     * Shows a splash screen while the tread is alive. If the thread not alive, it was by started.
     * @see java.lang.Thread.isAlive
     * @see java.lang.Thread.start
     */
    fun show(icon: ImageIcon, thread: Thread, minDuration: Int = 0, scale: Boolean = true) {
        val window = create(icon, scale)
        window.isVisible = true
        val start = System.currentTimeMillis()
        if (!thread.isAlive) thread.start()
        while (thread.isAlive);
        val end = System.currentTimeMillis()
        if (end - start < minDuration)
            Thread.sleep(minDuration - (end - start))
        window.dispose()
    }

    /**
     * Creates a JWindow splash screen.
     * @param icon The icon to display.
     * @param scale If true, the icon was scaled.
     */
    fun create(icon: ImageIcon, scale: Boolean = true, scaleHints: Int = Image.SCALE_SMOOTH): JWindow {
        // Creating window
        val window = JWindow()
        window.background = Color(0, 0, 0, 0)
        // Scaling icon
        if (scale) {
            val screenSize = Toolkit.getDefaultToolkit().screenSize
            val size = min(screenSize.width, screenSize.height) - 200
            // Scale simple, if the icon a square
            if (icon.iconWidth == icon.iconHeight) {
                icon.image = icon.image.getScaledInstance(size, size, scaleHints)
            } else {
                val scaled = JGL.scaleProportional(size, icon.iconWidth, icon.iconHeight)
                icon.image = icon.image.getScaledInstance(scaled.width, scaled.height, scaleHints)
            }
        }
        // Add icon to window via JLabel
        window.add(JLabel(icon))

        // Pack the windows size
        window.pack()
        // Center window
        window.setLocationRelativeTo(null)
        // Return window
        return window
    }
}
