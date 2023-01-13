package de.janorico.jgl.helpers

import java.awt.event.ActionListener
import javax.swing.*

/**
 * Contains methods for creating buttons with javax.swing.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JButton
 */

object Button {
    // METHODS FOR CREATING BUTTONS WITH TEXT AND ICON

    /**
     * Creates a button without tooltip and mnemonic.
     */
    fun create(text: String, icon: Icon, actionListener: ActionListener): JButton = create(text, icon, null, null, actionListener)

    /**
     * Creates a button with tooltip.
     */
    fun create(text: String, icon: Icon, toolTip: String?, actionListener: ActionListener): JButton =
        create(text, icon, toolTip, null, actionListener)

    /**
     * Creates a button with mnemonic.
     */
    fun create(text: String, icon: Icon, mnemonic: Char?, actionListener: ActionListener): JButton =
        create(text, icon, null, mnemonic, actionListener)

    /**
     * Base method for creating buttons with text and icon.
     */
    fun create(text: String, icon: Icon, toolTip: String?, mnemonic: Char?, actionListener: ActionListener): JButton = JButton(text, icon).apply {
        if (toolTip != null) toolTipText = toolTip
        if (mnemonic != null) setMnemonic(mnemonic)
        addActionListener(actionListener)
    }

    // METHODS FOR CREATING BUTTONS WITH TEXT

    /**
     * Creates a text button without tooltip and mnemonic.
     */
    fun create(text: String, actionListener: ActionListener): JButton = create(text, null, null, actionListener)

    /**
     * Creates a text button with tooltip.
     */
    fun create(text: String, toolTip: String?, actionListener: ActionListener): JButton = create(text, toolTip, null, actionListener)

    /**
     * Creates a text button with mnemonic.
     */
    fun create(text: String, mnemonic: Char?, actionListener: ActionListener): JButton = create(text, null, mnemonic, actionListener)

    /**
     * Base method for creating text buttons.
     * @param text The text for the new button.
     * @param toolTip Optional, the tooltip for the new button.
     * @param actionListener The action listener for the new button.
     * @return The new button.
     */
    fun create(text: String, toolTip: String?, mnemonic: Char?, actionListener: ActionListener): JButton = JButton(text).apply {
        if (toolTip != null) toolTipText = toolTip
        if (mnemonic != null) setMnemonic(mnemonic)
        addActionListener(actionListener)
    }

    // METHODS FOR CREATING BUTTONS WITH ICONS

    /**
     * Creates an icon button without tooltip and mnemonic.
     */
    fun create(icon: Icon, actionListener: ActionListener): JButton = create(icon, null, null, actionListener)

    /**
     * Creates an icon button with tooltip.
     */
    fun create(icon: Icon, toolTip: String?, actionListener: ActionListener): JButton = create(icon, toolTip, null, actionListener)

    /**
     * Creates an icon button with mnemonic.
     */
    fun create(icon: Icon, mnemonic: Char?, actionListener: ActionListener): JButton = create(icon, null, mnemonic, actionListener)

    /**
     * Base method for creating icon buttons.
     * @param icon The icon for the new button.
     * @param toolTip Optional, the tooltip for the new button.
     * @param mnemonic Optional, the mnemonic for the new button.
     * @param actionListener The action listener for the new button.
     * @return The new button.
     */
    fun create(icon: Icon, toolTip: String?, mnemonic: Char?, actionListener: ActionListener): JButton = JButton(icon).apply {
        if (toolTip != null) toolTipText = toolTip
        if (mnemonic != null) setMnemonic(mnemonic)
        addActionListener(actionListener)
    }
}
