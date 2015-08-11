package com.incyyte.app.web.helper;

import com.incyyte.app.service.util.Logger;
import com.incyyte.app.util.FileManagementUtil;
import com.incyyte.app.web.model.Photo;
import com.rackspacecloud.client.cloudfiles.FilesClient;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @author Remi Oseni
 */
public class RackSpaceFileClientHelper {

    private static FilesClient client;

    public static String uploadFile(String containerName, String fileName, CommonsMultipartFile multipartFile) {
        String storageURL = null;
        try {
            FilesClient client = getClient();
            if (!client.isLoggedin())
                client.login();
            if (!client.containerExists(containerName))
                client.createContainer(containerName);
            client.storeStreamedObject(containerName, multipartFile.getInputStream(), multipartFile.getContentType(), fileName, new HashMap<String, String>());
            storageURL = client.cdnEnableContainer(containerName) + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageURL;
    }

    public static InputStream downloadFile(String remoteFile, String containerName) {
        //FileOutputStream  outStream = null;
        InputStream input = null;
        try {
            FilesClient client = getClient();
            if (!client.isLoggedin())
                client.login();
            input = client.getObjectAsStream(containerName, remoteFile);
            // Write the contents of the remote file to a FileOutputStream
            //outStream = new FileOutputStream(newFilename);
            //IOUtils.copy(input, outStream);
        } catch (Exception ioe) {
            Logger.debug("Error communicating with FTP server.");
            ioe.printStackTrace();
        } finally {
            //IOUtils.closeQuietly(input);
        }
        return input;
    }

    public static boolean deleteRemoteFile(String remoteFile, String containerName) {
        try {
            Logger.debug("%%%%%%%%% deleteRemoteFile : Delete this file - " + remoteFile);
            Logger.debug("%%%%%%%%% deleteRemoteFile : container Name - " + containerName);
            FilesClient client = getClient();
            if (!client.isLoggedin())
                client.login();
            client.deleteObject(containerName, remoteFile);
            Logger.debug("%%%%%%%%% deleteRemoteFile : File Deleted");
            return true;
        } catch (Exception ioe) {
            Logger.error("Error communicating with FTP server.");
            ioe.printStackTrace();
            return false;
        }
    }

    private static FilesClient getClient() {
        if (client == null)
            client = new FilesClient();
        return client;
    }

    public static final void main(String[] args) throws Exception {
        deleteRemoteFile("Forest_10022013_101444.jpg", "image");
    }

    public static String uploadSearchedPicture(Photo searchedPicture) {
        String storageURL = null;
        try {
            FilesClient client = getClient();
            if (!client.isLoggedin()) {
                client.login();
            }
            String container = FileManagementUtil.getContainerType(searchedPicture.getContainerName());
            if (!client.containerExists(container)) {
                client.createContainer(container);
            }
            client.storeStreamedObject(container, searchedPicture.getData(), searchedPicture.getContentType(), searchedPicture.getFileName(), new HashMap<String, String>());
            storageURL = client.cdnEnableContainer(container) + "/" + searchedPicture.getFileName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return storageURL;
    }
}