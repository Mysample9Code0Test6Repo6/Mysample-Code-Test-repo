/**
 * 
 */
package com.incyyte.app.web.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.incyyte.app.service.util.Logger;
import org.apache.commons.net.tftp.TFTP;
import org.apache.commons.net.tftp.TFTPClient;
import org.jibble.simpleftp.SimpleFTP;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author Remi Oseni
 *
 */
public class TFTPClientUtils {
	
	static final String USAGE =
	        "Usage: tftp [options] hostname localfile remotefile\n\n" +
	        "hostname   - The name of the remote host\n" +
	        "localfile  - The name of the local file to send or the name to use for\n" +
	        "\tthe received file\n" +
	        "remotefile - The name of the remote file to receive or the name for\n" +
	        "\tthe remote server to use to name the local file being sent.\n\n" +
	        "options: (The default is to assume -r -b)\n" +
	        "\t-s Send a local file\n" +
	        "\t-r Receive a remote file\n" +
	        "\t-a Use ASCII transfer mode\n" +
	        "\t-b Use binary transfer mode\n";

	private static final String HOST_NAME = "www.panamedia.co.uk";
	private static final String ROOT_PATH = "incyyte";
	
	    public final static void main(String[] args)
	    {
	        boolean receiveFile = true, closed;
	        int transferMode = TFTP.BINARY_MODE, argc;
	        String arg, hostname, localFilename, remoteFilename;
	        TFTPClient tftp;
	        
	        // Parse options
	        for (argc = 0; argc < args.length; argc++)
	        {
	            arg = args[argc];
	            if (arg.startsWith("-"))
	            {
	                if (arg.equals("-r"))
	                    receiveFile = true;
	                else if (arg.equals("-s"))
	                    receiveFile = false;
	                else if (arg.equals("-a"))
	                    transferMode = TFTP.ASCII_MODE;
	                else if (arg.equals("-b"))
	                    transferMode = TFTP.BINARY_MODE;
	                else
	                {
                        Logger.error("Error: unrecognized option.");
                        Logger.error(USAGE);
	                    System.exit(1);
	                }
	            }
	            else
	                break;
	        }

	        // Make sure there are enough arguments
	        if (args.length - argc != 3)
	        {
                Logger.error("Error: invalid number of arguments.");
                Logger.error(USAGE);
	            System.exit(1);
	        }

	        // Get host and file arguments
	        hostname = args[argc];
	        localFilename = args[argc + 1];
	        remoteFilename = args[argc + 2];

	        // Create our TFTP instance to handle the file transfer.
	        tftp = new TFTPClient();

	        // We want to timeout if a response takes longer than 60 seconds
	        tftp.setDefaultTimeout(60000);

	        // Open local socket
	        try
	        {
	            tftp.open();
	        }
	        catch (SocketException e)
	        {
	            System.err.println("Error: could not open local UDP socket.");
	            System.err.println(e.getMessage());
	            System.exit(1);
	        }

	        // We haven't closed the local file yet.
	        closed = false;

	        // If we're receiving a file, receive, otherwise send.
	        if (receiveFile)
	        {
	            FileOutputStream output = null;
	            File file;

	            file = new File(localFilename);

	            // If file exists, don't overwrite it.
	            if (file.exists())
	            {
	                System.err.println("Error: " + localFilename + " already exists.");
	                System.exit(1);
	            }

	            // Try to open local file for writing
	            try
	            {
	                output = new FileOutputStream(file);
	            }
	            catch (IOException e)
	            {
	                tftp.close();
	                System.err.println("Error: could not open local file for writing.");
	                System.err.println(e.getMessage());
	                System.exit(1);
	            }

	            // Try to receive remote file via TFTP
	            try
	            {
	                tftp.receiveFile(remoteFilename, transferMode, output, hostname);
	            }
	            catch (UnknownHostException e)
	            {
	                System.err.println("Error: could not resolve hostname.");
	                System.err.println(e.getMessage());
	                System.exit(1);
	            }
	            catch (IOException e)
	            {
	                System.err.println(
	                    "Error: I/O exception occurred while receiving file.");
	                System.err.println(e.getMessage());
	                System.exit(1);
	            }
	            finally
	            {
	                // Close local socket and output file
	                tftp.close();
	                try
	                {
	                    output.close();
	                    closed = true;
	                }
	                catch (IOException e)
	                {
	                    closed = false;
	                    System.err.println("Error: error closing file.");
	                    System.err.println(e.getMessage());
	                }
	            }

	            if (!closed)
	                System.exit(1);

	        }
	        else
	        {
	            // We're sending a file
	            FileInputStream input = null;

	            // Try to open local file for reading
	            try
	            {
	                input = new FileInputStream(localFilename);
	            }
	            catch (IOException e)
	            {
	                tftp.close();
	                System.err.println("Error: could not open local file for reading.");
	                System.err.println(e.getMessage());
	                System.exit(1);
	            }

	            // Try to send local file via TFTP
	            try
	            {
	                tftp.sendFile(remoteFilename, transferMode, input, hostname);
	            }
	            catch (UnknownHostException e)
	            {
	                System.err.println("Error: could not resolve hostname.");
	                System.err.println(e.getMessage());
	                System.exit(1);
	            }
	            catch (IOException e)
	            {
	                System.err.println(
	                    "Error: I/O exception occurred while sending file.");
	                System.err.println(e.getMessage());
	                System.exit(1);
	            }
	            finally
	            {
	                // Close local socket and input file
	                tftp.close();
	                try
	                {
	                    input.close();
	                    closed = true;
	                }
	                catch (IOException e)
	                {
	                    closed = false;
	                    System.err.println("Error: error closing file.");
	                    System.err.println(e.getMessage());
	                }
	            }

	            if (!closed)
	                System.exit(1);

	        }

	    }
	    
	   
	    private static TFTPClient getTFTPClient(){
	    	
	    	// Create our TFTP instance to handle the file transfer.
	    	TFTPClient tftp = new TFTPClient();

	        // We want to timeout if a response takes longer than 60 seconds
	        tftp.setDefaultTimeout(60000);

	        // Open local socket
	        try
	        {
	            tftp.open();
	        }
	        catch (SocketException e)
	        {
	            System.err.println("Error: could not open local UDP socket.");
	            System.err.println(e.getMessage());
	        }
	    	
	        return tftp;
	    }
	    
	   public static String uploadFile(CommonsMultipartFile multipartFile, String filepath){
	    	
	    	TFTPClient tftp = getTFTPClient();
	    	
	    	String remoteFilename = generateFileName(multipartFile.getOriginalFilename());
			String ctype = multipartFile.getContentType();
			String type = ctype.substring(0,ctype.indexOf("/"));
	    	
	    	int transferMode = TFTP.BINARY_MODE;
	    	
	    	String path = ROOT_PATH;
	    	
			Logger.debug("FTP uploadFile  path - " + path + "/" + type);

			Logger.debug("%%%%%%% multipartFile size - " + multipartFile.getSize());

	    	
	    	// We're sending a file
	    	InputStream input = null;

            // Try to open local file for reading
            try
            {
                input = multipartFile.getInputStream();
            }
            catch (IOException e)
            {
                tftp.close();
                System.err.println("Error: could not open local file for reading.");
                System.err.println(e.getMessage());
                System.exit(1);
            }
	    	
	    	// Try to send local file via TFTP
            try
            {
                tftp.sendFile(remoteFilename, transferMode, input, HOST_NAME);
            }
            catch (UnknownHostException e)
            {
                System.err.println("Error: could not resolve hostname.");
                System.err.println(e.getMessage());
            }
            catch (IOException e)
            {
                System.err.println(
                    "Error: I/O exception occurred while sending file.");
                System.err.println(e.getMessage());
            }
            finally
            {
                // Close local socket and input file
                tftp.close();
                try
                {
                    input.close();
                }
                catch (IOException e)
                {
                    System.err.println("Error: error closing file.");
                    System.err.println(e.getMessage());
                }
            }
            
           return  path + "/" + type + "/" + remoteFilename;


        }
	   
	   public FileOutputStream receiveFile(String localFilename, String remoteFilename){
		   
		   TFTPClient tftp = getTFTPClient();
		   FileOutputStream output = null;
           File file = new File(localFilename);
           
           int transferMode = TFTP.BINARY_MODE;

           // If file exists, don't overwrite it.
           if (file.exists())
           {
               System.err.println("Error: " + localFilename + " already exists.");
           }

           // Try to open local file for writing
           try
           {
               output = new FileOutputStream(file);
           }
           catch (IOException e)
           {
               tftp.close();
               System.err.println("Error: could not open local file for writing.");
               System.err.println(e.getMessage());
               return null;
           }

           // Try to receive remote file via TFTP
           try
           {
               tftp.receiveFile(remoteFilename, transferMode, output, HOST_NAME);
           }
           catch (UnknownHostException e)
           {
               System.err.println("Error: could not resolve hostname.");
               System.err.println(e.getMessage());
           }
           catch (IOException e)
           {
               System.err.println(
                   "Error: I/O exception occurred while receiving file.");
               System.err.println(e.getMessage());
           }
           finally
           {
               // Close local socket and output file
               tftp.close();
              /* try
               {
                   output.close();
               }
               catch (IOException e)
               {
                   System.err.println("Error: error closing file.");
                   System.err.println(e.getMessage());
               }*/
           }

           return output;
	   }
	   
	   private static String generateFileName(String fileName){

			Logger.debug("File name = " + fileName);

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

				Logger.debug("File name prefix = " + fName.toString());

				ext = fileName.substring(mid, fileName.length());  		
				Logger.debug("Extension = " + ext);

				fName.append(ext);

				Logger.debug("File name = " + fName.toString().replace(" ", "_"));

				return fName.toString().replace(" ", "_");
			}

			return fileName;
		}	


}
