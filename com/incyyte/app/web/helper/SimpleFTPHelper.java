/**
 * 
 */
package com.incyyte.app.web.helper;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.incyyte.app.service.util.Logger;
import org.apache.commons.io.IOUtils;
import org.jibble.simpleftp.SimpleFTP;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Remi Oseni
 *
 */
public class SimpleFTPHelper {

	public static final String PREVIEW_PATH = "pre_path";
	public static final String ORIGINAL_PATH = "ori_path";
	private static final String ROOT_PATH = "incyyte";


	public static String uploadFile(CommonsMultipartFile multipartFile, String filepath){

		String flocation = null;
		SimpleFTP client = new SimpleFTP(); 
		InputStream fis = null;

		try { 

			client.connect("www.panamedia.co.uk", 21, "panacouk", "chinedu1");
			client.bin();

			Logger.debug("%%%%%%% Login Success");

			String fname = generateFileName(multipartFile.getOriginalFilename());
			String ctype = multipartFile.getContentType();
			String type = ctype.substring(0,ctype.indexOf("/"));

			String path = ROOT_PATH;
			client.cwd(path); 

			client.cwd(type); 

			Logger.debug("FTP uploadFile  path - " + path + "/" + type);

			Logger.debug("%%%%%%% multipartFile size - " + multipartFile.getSize());

			if(type.equals("image"))
				client.bin(); 
			if(type.equals("application"))
				client.ascii(); 

			fis = multipartFile.getInputStream();

			// Store file to server 
			client.stor(fis, fname);

			flocation = path + "/" + type + "/" + fname;

		} catch (Exception e) {
            Logger.error("%%%%%%% FTP Exception : Login Failed !!! ");
			e.printStackTrace(); 

		} finally { 

			IOUtils.closeQuietly( fis );
			try {

				client.disconnect( );
			} catch (IOException e) {
                Logger.error( "Problem disconnecting from FTP server" );
			}

		} 

		return flocation;

	}

	private static String generateFileName(String fileName){

		Logger.debug("File name = "+fileName);

		if(fileName != null){

			StringBuilder fName = new StringBuilder();
			String ext = "";
			int mid	= fileName.lastIndexOf(".");
			fName.append(fileName.substring(0,mid));

			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hhmmss");
			String fDate = sdf.format(date);
			Logger.debug("Today's Date - " + fDate);

			fName.append(" ");
			fName.append(fDate);

			Logger.debug("File name prefix = "+fName.toString());

			ext = fileName.substring(mid, fileName.length());  		
			Logger.debug("Extension = "+ext);

			fName.append(ext);

			Logger.debug("File name = "+fName.toString().replace(" ", "_"));

			return fName.toString().replace(" ", "_");
		}

		return fileName;
	}
	
	public static void main(String args[]){
		
	//	CommonsMultipartFile file = new CommonsMultipartFile();
		
	}

}
