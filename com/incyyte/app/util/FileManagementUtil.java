package com.incyyte.app.util;

import com.incyyte.app.domain.StorageProvider;
import com.incyyte.app.service.util.ConfigManager;
import com.incyyte.app.service.util.ConfigProperties;
import com.incyyte.app.service.util.IncyyteProperties;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.helper.AmazonS3ClientHelper;
import com.incyyte.app.web.helper.RackSpaceFileClientHelper;
import com.incyyte.app.web.model.Photo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManagementUtil {

    private static final Log log = LogFactory.getLog(FileManagementUtil.class);

    public static String uploadFile(String containerName, String fileName, CommonsMultipartFile multipartFile, Photo searchedPicture) throws Exception {
        StorageProvider provider = getProvider();
        String storageURL = null;
        if (provider.equals(StorageProvider.AMAZON_AWS)) {
            log.debug("Inside AWS Upload");
            if (multipartFile == null) {
                log.debug("Storage using Manual load:");
                storageURL = AmazonS3ClientHelper.uploadSearchedPicture(searchedPicture);
                log.debug("Storage URL:" + storageURL);
            } else {
                storageURL = AmazonS3ClientHelper.uploadFile(containerName, fileName, multipartFile);
                log.debug("Storage URL:" + storageURL);
            }
        } else if (provider.equals(StorageProvider.RACKSPACE_CDN)) {
            log.debug("Inside Rackspace Upload");
            if (multipartFile == null) {
                log.debug("Storage using Manual load:");
                storageURL = RackSpaceFileClientHelper.uploadSearchedPicture(searchedPicture);
                log.debug("Storage URL:" + storageURL);
            } else {
                storageURL = RackSpaceFileClientHelper.uploadFile(containerName, fileName, multipartFile);
                log.debug("Storage URL:" + storageURL);
            }
        }
        return storageURL;
    }

    public static String getProfileURL(String containerName, String fileName) {
        String profileURL = getFileURL(containerName, fileName);
        if (StringUtils.isBlank(profileURL)) {
            profileURL = getDefaultAvatar();
        }
        return profileURL;
    }

    public static String getFileURL(String containerName, String fileName) {
        try {
            log.debug("getFileURL: Start");
            if (StringUtils.isBlank(containerName) || StringUtils.isBlank(fileName)) {
                return null;
            }
            StorageProvider provider = getProvider();
            String storageURL = null;
            if (provider.equals(StorageProvider.AMAZON_AWS)) {
                storageURL = AmazonS3ClientHelper.getFileURL(containerName, fileName);
            } else if (provider.equals(StorageProvider.RACKSPACE_CDN)) {
                log.debug("Inside Rackspace GetURL");
                storageURL = getProviderURL(containerName) + fileName;
                log.debug("Storage GetURL:" + storageURL);
            }
            log.debug("getFileURL: End");
            return storageURL;
        } catch (Exception e) {
            log.error("getFileURL: Error:", e);
            return null;
        }
    }

    private static StorageProvider getProvider() throws Exception {
        ConfigManager icfg = ConfigManager.getInstance();
        StorageProvider provider;
        try {
            icfg.setIncyyteProperties(new IncyyteProperties(null));
            provider = StorageProvider.valueOf(icfg.getProperty(ConfigProperties.PROVIDER_NAME));
        } catch (Exception e) {
            throw new Exception("Storage Provider is not mapped. Error:", e);
        }
        return provider;
    }

    private static String getProviderURL(String containerName) throws Exception {
        ConfigManager icfg = ConfigManager.getInstance();
        String provider = null;
        try {
            if (containerName == null) {
                return null;
            }
            icfg.setIncyyteProperties(new IncyyteProperties(null));
            if (containerName.equalsIgnoreCase("image")) {
                provider = icfg.getProperty(ConfigProperties.RACKSPACE_IMAGE_URL);
            } else if (containerName.equalsIgnoreCase("application")) {
                provider = icfg.getProperty(ConfigProperties.RACKSPACE_APPL_URL);
            } else if (containerName.equalsIgnoreCase("video")) {
                provider = icfg.getProperty(ConfigProperties.RACKSPACE_VIDEO_URL);
            } else if (containerName.equalsIgnoreCase("text")) {
                provider = icfg.getProperty(ConfigProperties.RACKSPACE_TEXT_URL);
            }
        } catch (Exception e) {
            throw new Exception("Storage Provider is not mapped. Error:", e);
        }
        return provider;
    }

    public static String getDefaultAvatar() {
        ConfigManager icfg = ConfigManager.getInstance();
        String defaultProfileImage;
        try {
            icfg.setIncyyteProperties(new IncyyteProperties(null));
            String contextPath = icfg.getProperty(ConfigProperties.CONTEXT_PATH);
            defaultProfileImage = icfg.getProperty(ConfigProperties.INCYYTE_DEFAULY_AVATAR_URL);
            defaultProfileImage = contextPath + defaultProfileImage ; 
        } catch (Exception e) {
            log.error("Storage Provider is not mapped. Error:", e);
            return null;
        }
        return defaultProfileImage;
    }

    public static String getMimeType(String fileURL) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileURL);
        log.debug("type:" + type);
        return type;
    }

    public static String getContainerType(String cType) throws Exception {
        String fileType = cType.substring(0, cType.indexOf("/"));
        log.debug("fileType:" + fileType);
        return fileType;
    }
    
    public static String generateFileName(String fileName) {
        Logger.debug("File name = " + fileName);
        if (fileName != null) {
            StringBuilder fName = new StringBuilder();
            String ext = "";
            int mid = fileName.lastIndexOf(".");
            fName.append(fileName.substring(0, mid));

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

    public static void deleteRemoteFile(String containerName, String fileName) throws Exception {
    	 Logger.debug("enter into filemangementutil");
        StorageProvider provider = getProvider();
        if (provider.equals(StorageProvider.AMAZON_AWS)) {
            Logger.debug("Inside AWS Delete");
            AmazonS3ClientHelper.deleteRemoteFile(fileName, containerName);
        } else if (provider.equals(StorageProvider.RACKSPACE_CDN)) {
            Logger.debug("Inside Rack space Delete");
            RackSpaceFileClientHelper.deleteRemoteFile(fileName, containerName);
        }
    }
}