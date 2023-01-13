package de.janorico.jgl.helpers

import de.janorico.jgl.JGL
import java.beans.PropertyChangeEvent
import java.beans.PropertyChangeListener
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

/**
 * Contains methods for showing file choosers with javax.swing.
 * @author Janosch Lion
 * @since 1.0
 * @see javax.swing.JFileChooser
 */

object FileChooser {
    /**
     * Save a directory.
     */
    fun saveDirectory(onClosed: (selectedDirectory: File?, result: Int) -> Unit) {
        saveDirectory { chooser: JFileChooser, result: Int ->
            onClosed(chooser.selectedFile, result)
        }
    }

    /**
     * Save a directory.
     */
    @JvmName("saveDirectory1")
    fun saveDirectory(onClosed: (chooser: JFileChooser, result: Int) -> Unit) {
        val chooser = createSaveDirectory()
        val result = chooser.showSaveDialog(JGL.dialogOwner)
        onClosed(chooser, result)
    }

    /**
     * Open a directory.
     */
    fun openDirectory(onClosed: (selectedDirectory: File?, result: Int) -> Unit) {
        openDirectory { chooser: JFileChooser, result: Int ->
            onClosed(chooser.selectedFile, result)
        }
    }

    /**
     * Open a directory.
     */
    @JvmName("openDirectory1")
    fun openDirectory(onClosed: (chooser: JFileChooser, result: Int) -> Unit) {
        val chooser = createOpenDirectory()
        val result = chooser.showOpenDialog(JGL.dialogOwner)
        onClosed(chooser, result)
    }

    /**
     * Open multiple directories.
     */
    fun openDirectories(onClosed: (selectedDirectorys: Array<File>, result: Int) -> Unit) {
        openDirectories { chooser: JFileChooser, result: Int ->
            onClosed(chooser.selectedFiles, result)
        }
    }

    /**
     * Open multiple directories.
     */
    @JvmName("openDirectories1")
    fun openDirectories(onClosed: (chooser: JFileChooser, result: Int) -> Unit) {
        val chooser = createOpenDirectories()
        val result = chooser.showOpenDialog(JGL.dialogOwner)
        onClosed(chooser, result)
    }

    /**
     * Save a single file.
     */
    fun saveFile(onClosed: (selectedFile: File?, result: Int) -> Unit, vararg filters: FileFilter) {
        val chooser = createSaveFile(filters)
        val result = chooser.showSaveDialog(JGL.dialogOwner)
        onClosed(chooser.selectedFile, result)
    }

    /**
     * Save a single file.
     */
    @JvmName("saveFile1")
    fun saveFile(onClosed: (chooser: JFileChooser, result: Int) -> Unit, vararg filters: FileFilter) {
        val chooser = createSaveFile(filters)
        val result = chooser.showSaveDialog(JGL.dialogOwner)
        onClosed(chooser, result)
    }

    /**
     * Open a single file.
     */
    fun openFile(onClosed: (selectedFile: File?, result: Int) -> Unit, vararg filters: FileFilter) {
        val chooser = createOpenFile(filters)
        val result = chooser.showOpenDialog(JGL.dialogOwner)
        onClosed(chooser.selectedFile, result)
    }

    /**
     * Open a single file.
     */
    @JvmName("openFile1")
    fun openFile(onClosed: (chooser: JFileChooser, result: Int) -> Unit, vararg filters: FileFilter) {
        val chooser = createOpenFile(filters)
        val result = chooser.showOpenDialog(JGL.dialogOwner)
        onClosed(chooser, result)
    }

    /**
     * Open multiple files.
     */
    fun openFiles(onClosed: (selectedFiles: Array<File>, result: Int) -> Unit, vararg filters: FileFilter) {
        val chooser = createOpenFiles(filters)
        val result = chooser.showOpenDialog(JGL.dialogOwner)
        onClosed(chooser.selectedFiles, result)
    }

    /**
     * Open multiple files.
     */
    @JvmName("openFiles1")
    fun openFiles(onClosed: (chooser: JFileChooser, result: Int) -> Unit, vararg filters: FileFilter) {
        val chooser = createOpenFiles(filters)
        val result = chooser.showOpenDialog(JGL.dialogOwner)
        onClosed(chooser, result)
    }

    fun createSaveDirectory(): JFileChooser = create("${JGL.programName}: Save directory", JFileChooser.SAVE_DIALOG, JFileChooser.DIRECTORIES_ONLY, null)

    fun createOpenDirectory(): JFileChooser = create("${JGL.programName}: Open directory", JFileChooser.OPEN_DIALOG, JFileChooser.DIRECTORIES_ONLY, null)
    fun createOpenDirectories(): JFileChooser =
        create("${JGL.programName}: Open directory", JFileChooser.OPEN_DIALOG, JFileChooser.DIRECTORIES_ONLY, null, true)

    fun createSaveFile(filters: Array<out FileFilter>): JFileChooser =
        create("${JGL.programName}: Save file", JFileChooser.SAVE_DIALOG, JFileChooser.FILES_ONLY, null, filters)

    fun createOpenFile(filters: Array<out FileFilter>): JFileChooser =
        create("${JGL.programName}: Open file", JFileChooser.OPEN_DIALOG, JFileChooser.FILES_ONLY, null, filters)

    fun createOpenFiles(filters: Array<out FileFilter>): JFileChooser =
        create("${JGL.programName}: Open file", JFileChooser.OPEN_DIALOG, JFileChooser.FILES_ONLY, null, filters, true)

    fun create(
        dialogTitle: String,
        dialogType: Int,
        fileSelectionMode: Int,
        propertyChangeListener: PropertyChangeListener? = null,
        filters: Array<out FileFilter>,
        isMultiSelectionEnabled: Boolean = false,
    ): JFileChooser = create(dialogTitle, dialogType, fileSelectionMode, propertyChangeListener, isMultiSelectionEnabled).apply {
        for (filter in filters) this.addChoosableFileFilter(filter)
    }


    fun create(
        dialogTitle: String,
        dialogType: Int,
        fileSelectionMode: Int,
        propertyChangeListener: PropertyChangeListener? = null,
        isMultiSelectionEnabled: Boolean = false,
    ): JFileChooser = JFileChooser().apply {
        this.dialogTitle = dialogTitle
        this.dialogType = dialogType
        this.fileSelectionMode = fileSelectionMode
        this.isMultiSelectionEnabled = isMultiSelectionEnabled
        if (propertyChangeListener == null) {
            this.addPropertyChangeListener { e: PropertyChangeEvent ->
                if (e.propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) || e.propertyName.equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    e.newValue
                }
            }
        } else this.addPropertyChangeListener(propertyChangeListener)
    }
}
