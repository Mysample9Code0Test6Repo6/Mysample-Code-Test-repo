/**
 * 
 */
package com.incyyte.app.service.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpException;

import com.rackspacecloud.client.cloudfiles.FilesClient;

/**
 * @author Remi Oseni
 *
 */
public class Cftest {

	/**
	 * @throws Exception 
	 * @param args
	 * @throws  
	 */
	public static void main(String[] args) throws HttpException, IOException{
		FilesClient client = new FilesClient();
		client.login();
		//client.createContainer("incyyte_image");
		File f = new File("C:\\Users\\Remi Oseni\\Pictures\\kitten.jpg");

        Logger.debug("file path ->  " + f.getAbsolutePath());
		String contentType = getType("file:" + f.getAbsolutePath());
        Logger.debug("file type ->  " + contentType);
		
		String storageURL = client.cdnEnableContainer("image");
        Logger.debug("%%%%%%% storageURL - " +storageURL);
        Logger.debug("%%%%%%% cdnURL - " +client.getAuthenticationURL());
        Logger.debug("%%%%%%% cdnURL - " +client.getStorageURL());
		//client.storeObject("incyyte_image", f, contentType);
		//InputStream in = client.getObjectAsStream("image", "Autumn_Leaves_09062012_051347.jpg");

		/*FileOutputStream out = new FileOutputStream("C:\\Remi\\Autumn Leaves.jpg");
		byte[] buffer = new byte[10240];
		while (true) {
	        int bytes = in.read(buffer);
	        if (bytes < 0)
	          break;
	        out.write(buffer, 0, bytes);	        
	      }
	      out.close();
	      in.close();*/
		
	}

	public static String getType(String fileUrl) throws IOException  {
	    URL u = new URL(fileUrl);
	    URLConnection uc = u.openConnection();
	    String type = uc.getContentType();
	    return type;
	 }
}
