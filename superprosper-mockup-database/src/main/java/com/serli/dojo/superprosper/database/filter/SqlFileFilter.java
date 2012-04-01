package com.serli.dojo.superprosper.database.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.myjavadev.utility.FileTools;

public class SqlFileFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        String extension = FileTools.getExtension(f);
        if (extension != null) {
            if (extension.equals("sql") || extension.equals("SQL")){
                    return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public String getDescription() {
        return "Structured Query Language file";
    }
}
