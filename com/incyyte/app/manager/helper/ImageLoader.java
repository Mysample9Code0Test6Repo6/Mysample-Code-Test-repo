package com.incyyte.app.manager.helper;

import java.io.File;
import java.io.FileInputStream;

import com.incyyte.app.service.Exceptions.FileUploadException;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.service.util.SystemProperty;

public class ImageLoader {

	private String filename;
	private String extension;
	
	private static int pathDelimeter;
	
	public static byte[] uploadFile(String path) throws FileUploadException{
		 //save image into database
    	File file = new File(path);
        byte[] bFile = new byte[(int) file.length()];
 
        try {
        	FileInputStream fileInputStream = new FileInputStream(file);
        	//convert file into array of bytes
        	fileInputStream.read(bFile);
        	fileInputStream.close();
        } catch (Exception e) {
        	throw new FileUploadException(e.getMessage());
        }
        
        return bFile;
	}
	
	private static String analyseFile(String path, String flag){
	    String fname="";
	    String ext="";
	    
	    int mid= path.lastIndexOf(".");
	    
		if(SystemProperty.isWindows()){
			pathDelimeter= path.lastIndexOf("\\");
		}else if(SystemProperty.isMac()){
			pathDelimeter= path.lastIndexOf("/");
		}else if(SystemProperty.isUnix()){
			pathDelimeter= path.lastIndexOf("/");
		}else{
            Logger.debug("Your OS is not support!!");
		}
	    
	    fname=path.substring(pathDelimeter+1,mid);
	    ext=path.substring(mid+1,path.length());  
	    
	    if (flag.equals("EXT"))
	    	return ext;
	    else
	    	return fname;
	  }
	
	public static String getFilename(String path) {
		return analyseFile( path, "FNAME");
	}

	public static String getExtension(String path) {
		return analyseFile( path, "EXT");
	}
 
	public static void main(String args[]){
		ImageLoader ld = new ImageLoader();
		String path = "C:\\Users\\Public\\Music\\Instrumentals\\01 Adonai.mp3";
		try {
			ld.uploadFile(path);
		} catch (FileUploadException e) {
            Logger.error("Exception:", e);
        }
        Logger.debug(ld.getFilename(path));
        Logger.debug(ld.getExtension(path));
	}
	
	//The maximum packet size is easily configured by adding my.ini in the appropriate line. For examplee: 
	//located in C:\Program Files\MySQL\MySQL Server 5.1
	//set-variable	=	max_allowed_packet=10M



}
