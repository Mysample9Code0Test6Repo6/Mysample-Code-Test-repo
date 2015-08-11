package com.incyyte.app.web.model;

import java.io.InputStream;

public class Photo {

    String containerName;
    String fileName;
    Long size;
    String contentType;
    InputStream data;

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public InputStream getData() {
        return data;
    }

    public void setData(InputStream data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "containerName='" + containerName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", size=" + size +
                ", contentType='" + contentType + '\'' +
                ", data=" + data +
                '}';
    }
}