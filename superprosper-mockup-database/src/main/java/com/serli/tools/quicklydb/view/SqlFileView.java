package com.serli.tools.quicklydb.view;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.myjavadev.utility.FileTools;

import com.serli.tools.quicklydb.AppQuiklydb;

public class SqlFileView extends FileView {
	ImageIcon sqlIcon = null;

	public SqlFileView() {
		ResourceMap resourceMap = Application.getInstance().getContext().getResourceMap(AppQuiklydb.class);
		sqlIcon = resourceMap.getImageIcon("sqlFileView.ico");
	}

//	public String getName(File f) {
//		return null; // let the L&F FileView figure this out
//	}
//
//	public String getDescription(File f) {
//		return null; 
//	}
//
//	public Boolean isTraversable(File f) {
//		return null; 
//	}

	public String getTypeDescription(File f) {
		String extension = FileTools.getExtension(f);
		String type = null;

		if (extension != null) {
			if (extension.equals("sql") || extension.equals("SQL")) {
				type = "SQL File";
			} 
		}
		return type;
	}

	public Icon getIcon(File f) {
		String extension = FileTools.getExtension(f);
		Icon icon = null;

		if (extension != null) {
			if (extension.equals("sql") || extension.equals("SQL")) {
				icon = sqlIcon;
			} 
		}
		return icon;
	}
}