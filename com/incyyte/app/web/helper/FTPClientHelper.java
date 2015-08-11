/**
 * 
 */
package com.incyyte.app.web.helper;

import com.incyyte.app.service.util.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient; 
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Remi Oseni
 *
 */
public class FTPClientHelper {

	public static final String PREVIEW_PATH = "pre_path";
	public static final String ORIGINAL_PATH = "ori_path";
	private static final String ROOT_PATH = "incyyte";


	public static String uploadFile(CommonsMultipartFile multipartFile, String filepath){

		String flocation = null;
		FTPClient client = new FTPClient(); 
		InputStream fis = null;
		try { 

			login(client);		

			Logger.debug("%%%%%%% Login Success");
			
			// Use passive mode as default because most of us are
            // behind firewalls these days.
			client.enterLocalPassiveMode();

			String fname = generateFileName(multipartFile.getOriginalFilename());
			String ctype = multipartFile.getContentType();
			String type = ctype.substring(0,ctype.indexOf("/"));

			String path = ROOT_PATH;
			client.makeDirectory(path);
			client.changeWorkingDirectory(path); 

			client.makeDirectory(type);
			client.changeWorkingDirectory(type); 

			Logger.debug("FTP uploadFile  path - " + path + "/" + type);

			Logger.debug("%%%%%%% multipartFile size - " + multipartFile.getSize());

			client.setFileType(FTP.BINARY_FILE_TYPE); 
			
			if(type.equals("text"))
				client.setFileType(FTP.ASCII_FILE_TYPE); 
		
			fis = multipartFile.getInputStream();

			// Store file to server 
			client.storeFile(fname, fis); 	

			client.logout(); 

			flocation = path + "/" + type + "/" + fname;

		} catch (Exception e) {
            Logger.error("%%%%%%% FTP Exception : Login Failed !!! ");
		} finally {
			IOUtils.closeQuietly( fis );
			try {
				if (client.isConnected())
					client.disconnect( );
			} catch (IOException e) {
                Logger.error( "Problem disconnecting from FTP server" );
			}

		} 

		return flocation;

	}

	public static FileOutputStream downloadFile(String newFilename, String remoteFile){
		FTPClient client = new FTPClient(); 
		FileOutputStream  outStream = null;
		try { 

			login(client);			

			String path = remoteFile.substring(remoteFile.indexOf("/")+1, remoteFile.lastIndexOf("/"));
			String fname = remoteFile.substring(remoteFile.lastIndexOf("/")+1, remoteFile.length());

			Logger.debug("downloadFile filename - " + remoteFile);

			Logger.debug("downloadFile ROOT_PATH - " + ROOT_PATH );
			Logger.debug("downloadFile path - "  + path);

			client.changeWorkingDirectory(ROOT_PATH); 
			client.changeWorkingDirectory(path);
			client.setFileType(FTP.BINARY_FILE_TYPE);

			// Write the contents of the remote file to a FileOutputStream     
			outStream = new FileOutputStream(newFilename);    

			client.retrieveFile( fname, outStream ); 
			
			client.logout();

		} catch(IOException ioe) {
            Logger.error( "Error communicating with FTP server." );
		} finally {     
			//IOUtils.closeQuietly( outStream );     
			try {         
				client.disconnect( );     
			} catch (IOException e) {
                Logger.error( "Problem disconnecting from FTP server" );
			} 
		} 


		return outStream;
	}

/*	public static String transferPrevFile(String remoteFile){

		FTPClient client = new FTPClient(); 
		FileOutputStream  outStream = null;
		String destinationFile = remoteFile;
		try { 

			login(client);
			
			// Use passive mode as default because most of us are
            // behind firewalls these days.
			client.enterLocalPassiveMode();            

			String path = remoteFile.substring(0, remoteFile.lastIndexOf("/"));
			String ftype = remoteFile.substring(remoteFile.indexOf("/")+1, remoteFile.lastIndexOf("/"));
			String fname = remoteFile.substring(remoteFile.lastIndexOf("/")+1, remoteFile.length());

			Logger.debug("downloadFile filename - " + remoteFile);

			Logger.debug("downloadFile path - " + ROOT_PATH + "/" + path);

			client.changeWorkingDirectory(ROOT_PATH + "/" + path); 
			client.setFileType(FTP.BINARY_FILE_TYPE);

			client.remoteRetrieve(fname);	

			path = ROOT_PATH + "/" + ORIGINAL_PATH;
			client.changeToParentDirectory();

			client.makeDirectory(path);
			client.changeWorkingDirectory(path); 

			client.makeDirectory(ftype);
			client.changeWorkingDirectory(ftype); 			

			client.remoteStore(fname);

			client.completePendingCommand();            

			destinationFile = remoteFile.replace(PREVIEW_PATH, ORIGINAL_PATH);

		} catch(IOException ioe) {    
			Logger.debug( "Error communicating with FTP server." );
		} finally {     
			IOUtils.closeQuietly( outStream );     
			try {         
				client.disconnect( );     
			} catch (IOException e) {         
				Logger.debug( "Problem disconnecting from FTP server" );
			} 
		} 


		return destinationFile;
	}*/

	private static void login(FTPClient client) throws SocketException,
	IOException {
		client.connect("www.panamedia.co.uk"); 			
		client.login("panacouk", "chinedu1");
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

	public static final void main(String[] args) throws Exception{

		//InputStream inputStream = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\Forest.jpg");
		//uploadFile("Forest.jpg", inputStream);

		//String fname = "application\\DSC01779_07102011_115004.JPG";
		//OutputStream outputStream = new FileOutputStream("remi5.jpg");

		//downloadFile("Winter_Leaves.jpg", "pre_path/image/Winter_Leaves_18102011_021039.jpg");
	}

}
