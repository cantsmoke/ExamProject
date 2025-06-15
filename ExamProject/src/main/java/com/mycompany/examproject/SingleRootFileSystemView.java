/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject;

/**
 *
 * @author Arseniy
 */
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

// Создаём собственный FileSystemView
class SingleRootFileSystemView extends FileSystemView {
    private final File root;

    public SingleRootFileSystemView(File root) {
        this.root = root;
    }

    @Override
    public File createNewFolder(File containingDir) {
        File folder = new File(containingDir, "New Folder");
        folder.mkdir();
        return folder;
    }

    @Override
    public File getDefaultDirectory() {
        return root;
    }

    @Override
    public File getHomeDirectory() {
        return root;
    }

    @Override
    public File[] getRoots() {
        return new File[]{root};
    }
}