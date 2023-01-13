package de.janorico.jgl.helpers

import java.awt.event.ActionListener
import javax.swing.*

/**
 * Contains methods for creating MenuItems with javax.swing.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JMenuItem
 */
object MenuItem {
    // METHOD WITHOUT FOUR PARAMETERS.

    /**
     * Creates a menu item without all optional parameters.
     */
    fun create(text: String, actionListener: ActionListener): JMenuItem =
        create(text, null, null, null, null, actionListener)

    // METHODS WITHOUT THREE PARAMETERS.

    /**
     * Creates a menu item without icon, tooltip and mnemonic.
     */
    fun create(text: String, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, null, null, null, accelerator, actionListener)

    /**
     * Creates a menu item without icon, tooltip and accelerator.
     */
    fun create(text: String, mnemonic: Char?, actionListener: ActionListener): JMenuItem =
        create(text, null, null, mnemonic, null, actionListener)

    /**
     * Creates a menu item without icon, mnemonic and accelerator.
     */
    fun create(text: String, toolTip: String?, actionListener: ActionListener): JMenuItem =
        create(text, null, toolTip, null, null, actionListener)

    /**
     * Creates a menu item without tooltip, mnemonic and accelerator.
     */
    fun create(text: String, icon: Icon?, actionListener: ActionListener): JMenuItem =
        create(text, icon, null, null, null, actionListener)

    // METHODS WITHOUT TWO PARAMETERS.

    /**
     * Creates a menu item without icon and tooltip.
     */
    fun create(text: String, mnemonic: Char?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, null, null, mnemonic, accelerator, actionListener)

    /**
     * Creates a menu item without icon and mnemonic.
     */
    fun create(text: String, toolTip: String?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, null, toolTip, null, accelerator, actionListener)

    /**
     * Creates a menu item without icon and accelerator.
     */
    fun create(text: String, toolTip: String?, mnemonic: Char?, actionListener: ActionListener): JMenuItem =
        create(text, null, toolTip, mnemonic, null, actionListener)

    /**
     * Creates a menu item without tooltip and mnemonic.
     */
    fun create(text: String, icon: Icon?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, icon, null, null, accelerator, actionListener)

    /**
     * Creates a menu item without tooltip and accelerator.
     */
    fun create(text: String, icon: Icon?, mnemonic: Char?, actionListener: ActionListener): JMenuItem =
        create(text, icon, null, mnemonic, null, actionListener)

    /**
     * Creates a menu item without mnemonic and accelerator.
     */
    fun create(text: String, icon: Icon?, toolTip: String?, actionListener: ActionListener): JMenuItem =
        create(text, icon, toolTip, null, null, actionListener)

    // METHODS WITHOUT ONE PARAMETER.

    /**
     * Creates a menu item without icon.
     */
    fun create(text: String, toolTip: String?, mnemonic: Char?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, null, toolTip, mnemonic, accelerator, actionListener)

    /**
     * Creates a menu item without tooltip
     */
    fun create(text: String, icon: Icon?, mnemonic: Char?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, icon, null, mnemonic, accelerator, actionListener)

    /**
     * Creates a menu item without mnemonic.
     */
    fun create(text: String, icon: Icon?, toolTip: String?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        create(text, icon, toolTip, null, accelerator, actionListener)

    /**
     * Creates a menu item without accelerator.
     */
    fun create(text: String, icon: Icon?, toolTip: String?, mnemonic: Char?, actionListener: ActionListener): JMenuItem =
        create(text, icon, toolTip, mnemonic, null, actionListener)

    // BASE METHOD, WITH ALL PARAMETERS.

    /**
     * Base method for creating menu items.
     * @param text The text for the new menu item.
     * @param icon Optional, the icon for the new menu item.
     * @param toolTip Optional, the tooltip for the new menu item.
     * @param mnemonic Optional, the mnemonic for the new menu item.
     * @param accelerator Optional, the accelerator for the new menu item.
     * @param actionListener The action listener for the new menu item.
     * @return The new menu item.
     */
    fun create(text: String, icon: Icon?, toolTip: String?, mnemonic: Char?, accelerator: KeyStroke?, actionListener: ActionListener): JMenuItem =
        JMenuItem(text).apply {
            if (icon != null) this.icon = icon
            if (toolTip != null) this.toolTipText = toolTip
            if (mnemonic != null) this.setMnemonic(mnemonic)
            if (accelerator != null) this.accelerator = accelerator
            addActionListener(actionListener)
        }
}
