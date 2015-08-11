/**
 *
 */
package com.incyyte.app.web.helper;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.incyyte.app.service.util.Logger;
import com.incyyte.app.web.model.Photo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author RemiOseni
 */
public class AmazonS3ClientHelper {

    private static AmazonS3 client;
    private static String BUCKET_NAME = "incyyte-prod";

    public static String uploadFile(String containerName, String fileName, CommonsMultipartFile multipartFile) throws IOException {
        String storageURL = null;
        Long contentLength = Long.valueOf(multipartFile.getBytes().length);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(multipartFile.getContentType()); //binary data
        try {
            Logger.debug("Uploading a new object to S3 from a file");
            AmazonS3 s3 = getClient();
            if (!s3.doesBucketExist(BUCKET_NAME))
                s3.createBucket(BUCKET_NAME);

            s3.putObject(new PutObjectRequest(BUCKET_NAME, containerName + "/" + fileName, multipartFile.getInputStream(), metadata));
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, containerName + "/" + fileName);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2030"));
            storageURL = client.generatePresignedUrl(generatePresignedUrlRequest).toString();
            Logger.debug("Pre-Signed URL = " + storageURL);
        } catch (AmazonServiceException ase) {
            Logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            Logger.error("Error Message:    " + ase.getMessage());
            Logger.error("HTTP Status Code: " + ase.getStatusCode());
            Logger.error("AWS Error Code:   " + ase.getErrorCode());
            Logger.error("Error Type:       " + ase.getErrorType());
            Logger.error("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            Logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            Logger.error("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (ParseException pe) {
            Logger.error("Parse Exception Message: " + pe.getMessage());
            throw new IOException(pe);
        }
        return storageURL;
    }

    public static InputStream downloadFile(String remoteFile, String containerName) {
        InputStream input = null;
        try {
            Logger.debug("Downloading an object");
            AmazonS3 s3 = getClient();
            S3Object s3object = s3.getObject(new GetObjectRequest(BUCKET_NAME, containerName + "/" + remoteFile));
            Logger.debug("Content-Type: " + s3object.getObjectMetadata().getContentType());
            input = s3object.getObjectContent();
        } catch (AmazonServiceException ase) {
            Logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            Logger.error("Error Message:    " + ase.getMessage());
            Logger.error("HTTP Status Code: " + ase.getStatusCode());
            Logger.error("AWS Error Code:   " + ase.getErrorCode());
            Logger.error("Error Type:       " + ase.getErrorType());
            Logger.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            Logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            Logger.error("Error Message: " + ace.getMessage());
        }
        return input;
    }

    public static String getFileURL(String containerName, String fileName) {
        String storageURL = "";
        try {
            client = getClient();
            Logger.error("Start getFileURL");
            if (StringUtils.isBlank(containerName) || StringUtils.equals(containerName, "null")
                    || StringUtils.isBlank(fileName) || StringUtils.equals(fileName, "null")) {
                Logger.error("Return Null");
                return null;
            }
            Logger.error("getFileURL:containerName:" + containerName);
            Logger.error("getFileURL:fileName:" + fileName);
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, containerName + "/" + fileName);
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2030"));
            storageURL = client.generatePresignedUrl(generatePresignedUrlRequest).toString();
            Logger.error("getFileURL:storageURL:" + storageURL);
        } catch (AmazonServiceException ase) {
            Logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            Logger.error("Error Message:    " + ase.getMessage());
            Logger.error("HTTP Status Code: " + ase.getStatusCode());
            Logger.error("AWS Error Code:   " + ase.getErrorCode());
            Logger.error("Error Type:       " + ase.getErrorType());
            Logger.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            Logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            Logger.error("Error Message: " + ace.getMessage());
        } catch (Exception e) {
            Logger.error("Error Message: " + e);
        }
        return StringUtils.defaultString(storageURL);
    }

    public static void deleteRemoteFile(String remoteFile, String containerName) {
        try {
            AmazonS3 s3 = getClient();
            s3.deleteObject(new DeleteObjectRequest(BUCKET_NAME, containerName + "/" + remoteFile));
        } catch (AmazonServiceException ase) {
            Logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            Logger.error("Error Message:    " + ase.getMessage());
            Logger.error("HTTP Status Code: " + ase.getStatusCode());
            Logger.error("AWS Error Code:   " + ase.getErrorCode());
            Logger.error("Error Type:       " + ase.getErrorType());
            Logger.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            Logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            Logger.error("Error Message: " + ace.getMessage());
        }
    }

    private static AmazonS3 getClient() {
        if (client == null) {
            try {
                client = new AmazonS3Client(new PropertiesCredentials(AmazonS3ClientHelper.class.getResourceAsStream("AwsCredentials.properties")));
            } catch (IOException e) {
                e.printStackTrace();
                Logger.error("IOException:", e);
            }
        }
        return client;
    }

    public static final void main(String[] args) throws Exception {
        AmazonS3 s3client = getClient();
        try {
            Logger.debug("Uploading a new object to S3 from a file\n");
            File file = new File("C:\\Users\\RemiOseni\\Pictures\\PhotoDirector\\2.0\\RemiOseni\\Sample Images\\Cat.jpg");
            s3client.putObject(new PutObjectRequest("incyyte-prod", "image/Cat-10-08.jpg", file));
            Logger.debug("Uploading completed ... \n");
        } catch (AmazonServiceException ase) {
            Logger.error("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            Logger.error("Error Message:    " + ase.getMessage());
            Logger.error("HTTP Status Code: " + ase.getStatusCode());
            Logger.error("AWS Error Code:   " + ase.getErrorCode());
            Logger.error("Error Type:       " + ase.getErrorType());
            Logger.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            Logger.error("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            Logger.error("Error Message: " + ace.getMessage());
        }
    }

    public static String uploadSearchedPicture(Photo searchPicture) throws IOException {
        String storageURL = null;
        Long contentLength = searchPicture.getSize();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(searchPicture.getContentType()); //binary data
        try {
            Logger.debug("Uploading a new object to S3 from a file\n");
            AmazonS3 s3 = getClient();
            if (!s3.doesBucketExist(BUCKET_NAME))
                s3.createBucket(BUCKET_NAME);

            s3.putObject(new PutObjectRequest(BUCKET_NAME, searchPicture.getContainerName() + "/" + searchPicture.getFileName(), searchPicture.getData(), metadata));
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(BUCKET_NAME, searchPicture.getContainerName() + "/" + searchPicture.getFileName());
            generatePresignedUrlRequest.setMethod(HttpMethod.GET);
            generatePresignedUrlRequest.setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2030"));
            storageURL = client.generatePresignedUrl(generatePresignedUrlRequest).toString();
            Logger.debug("Pre-Signed URL = " + storageURL);
        } catch (AmazonServiceException ase) {
            Logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            Logger.error("Error Message:    " + ase.getMessage());
            Logger.error("HTTP Status Code: " + ase.getStatusCode());
            Logger.error("AWS Error Code:   " + ase.getErrorCode());
            Logger.error("Error Type:       " + ase.getErrorType());
            Logger.error("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            Logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            Logger.error("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (ParseException pe) {
            Logger.error("Parse Exception Message: " + pe.getMessage());
            throw new IOException(pe);
        }
        return storageURL;
    }
}