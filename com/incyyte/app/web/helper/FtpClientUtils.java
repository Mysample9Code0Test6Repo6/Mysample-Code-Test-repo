/**
 * 
 */
package com.incyyte.app.web.helper;

/**
 * @author Remi Oseni
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

import com.incyyte.app.service.util.Logger;
import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

public class FtpClientUtils {
	
  public static int BUFFER_SIZE = 10240;

  private FtpClient m_client;

  // set the values for your server
  
  private String host = "www.panamedia.co.uk";

  private String user = "panacouk";

  private String password = "chinedu1";

  private String sDir = "/incyyte/application";

  private String m_sLocalFile = "C:\\TEMP\\ftpfolder\\application\\Property1_14102011_011429.xlsx";

  private String m_sHostFile = "Property.xlsx";

  public FtpClientUtils() {
    try {
      Logger.debug("Connecting to host " + host);
      m_client = new FtpClient(host);
      m_client.login(user, password);
      Logger.debug("User " + user + " login OK");
      Logger.debug(m_client.welcomeMsg);
      m_client.cd(sDir);
      Logger.debug("Directory: " + sDir);
      //m_client.ascii();
      putFile();
      Logger.debug("Success.");
    } catch (Exception ex) {
        Logger.error("Error: " + ex.toString());
    }
  }

  protected void disconnect() {
    if (m_client != null) {
      try {
        m_client.closeServer();
      } catch (IOException ex) {
      }
      m_client = null;
    }
  }

  public static int getFileSize(FtpClient client, String fileName)
      throws IOException {
    TelnetInputStream lst = client.list();
    String str = "";
    fileName = fileName.toLowerCase();
    while (true) {
      int c = lst.read();
      char ch = (char) c;
      if (c < 0 || ch == '\n') {
        str = str.toLowerCase();
        if (str.indexOf(fileName) >= 0) {
          StringTokenizer tk = new StringTokenizer(str);
          int index = 0;
          while (tk.hasMoreTokens()) {
            String token = tk.nextToken();
            if (index == 4)
              try {
                return Integer.parseInt(token);
              } catch (NumberFormatException ex) {
                return -1;
              }
            index++;
          }
        }
        str = "";
      }
      if (c <= 0)
        break;
      str += ch;
    }
    return -1;
  }

  protected void getFile() {
    if (m_sLocalFile.length() == 0) {
      m_sLocalFile = m_sHostFile;
    }
    byte[] buffer = new byte[BUFFER_SIZE];
    try {
    	
      int size = getFileSize(m_client, m_sHostFile);
      if (size > 0) {
        Logger.debug("File " + m_sHostFile + ": " + size
            + " bytes");
        Logger.debug("size:" + size);
      } else
        Logger.debug("File " + m_sHostFile + ": size unknown");
      FileOutputStream out = new FileOutputStream(m_sLocalFile);
      InputStream in = m_client.get(m_sHostFile);
      int counter = 0;
      while (true) {
        int bytes = in.read(buffer);
        if (bytes < 0)
          break;

        out.write(buffer, 0, bytes);
        counter += bytes;
      }
      out.close();
      in.close();
    } catch (Exception ex) {
      Logger.debug("Error: " + ex.toString());
    }
  }

  protected void putFile() {
    if (m_sLocalFile.length() == 0) {
      Logger.debug("Please enter file name");
    }
    byte[] buffer = new byte[BUFFER_SIZE];
    try {
      File f = new File(m_sLocalFile);
      int size = (int) f.length();
      Logger.debug("File " + m_sLocalFile + ": " + size + " bytes");
      Logger.debug("size:" + size);
      FileInputStream in = new FileInputStream(m_sLocalFile);
      OutputStream out = m_client.put(m_sHostFile);

      int counter = 0;
      while (true) {
        int bytes = in.read(buffer);
        if (bytes < 0)
          break;
        out.write(buffer, 0, bytes);
        counter += bytes;
        Logger.debug("counter:" + counter);
      }

      out.close();
      in.close();
    } catch (Exception ex) {
        Logger.error("Error: " + ex.toString());
    }
  }

  public static void main(String argv[]) {
    new FtpClientUtils();
  }
}

