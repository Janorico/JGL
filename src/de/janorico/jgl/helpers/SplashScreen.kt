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
     * @param icon The icon that gets displayed.
     * @param millis The time the splash screen is visible in milliseconds.
     * @param scale Indicates if the icon should be scaled to screen size.
     * @see java.lang.Thread.sleep
     */
    fun show(icon: ImageIcon, millis: Long, scale: Boolean = true) {
        val window = create(icon, scale)
        window.isVisible = true
        Thread.sleep(millis)
        window.dispose()
    }

    /**
     * Shows a splash screen while the tread is alive. If the thread is not alive, it will be started.
     * @param icon The icon that gets displayed.
     * @param scale Indicates if the icon should be scaled to screen size.
     * @param minDuration The minimum duration the splashscreen is visible.
     * @see java.lang.Thread.isAlive
     * @see java.lang.Thread.start
     */
    @Deprecated("There is a better alternative.", ReplaceWith("show(icon, function, minDuration, scale)", "de.janorico.jgl.helpers.SplashScreen"))
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
     * Shows a splash screen and calls the function. The splash will close when the function is done.
     * @param icon The icon that gets displayed.
     * @param function A function that gets executed.
     * @param minDuration The minimum duration the splashscreen is visible, use it if the function is faster than you want the splashscreen to be displayed.
     * @param scale Indicates if the icon should be scaled to screen size.
     */
    fun show(icon: ImageIcon, function: () -> Unit, minDuration: Int = 0, scale: Boolean = true) {
        val window = create(icon, scale)
        window.isVisible = true
        val start = System.currentTimeMillis()
        function()
        val end = System.currentTimeMillis()
        if (end - start < minDuration)
            Thread.sleep(minDuration - (end - start))
        window.dispose()
    }

    /**
     * Creates a JWindow splash screen. All other functions are based on this one.
     * @param icon The icon to display.
     * @param scale If true, the icon was scaled.
     * @param scaleHints Which mode should be used for scaling the image. See Image.SCALE_
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
        window.cursor = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)

        // Pack the windows size
        window.pack()
        // Center window
        window.setLocationRelativeTo(null)
        // Return window
        return window
    }
}
