package org.myjavadev.utility;

import java.io.File;

public class FileTools {

	    public static String getExtension(File file) {
	        String ext = null;
	        String path = file.getName();
	        int i = path.lastIndexOf('.');

	        if (i > 0 &&  i < path.length() - 1) {
	            ext = path.substring(i+1).toLowerCase();
	        }
	        return ext;
	    }
}
