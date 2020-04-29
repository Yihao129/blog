package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private AmazonS3 s3Client;
    private String BUCKET = "yyh-buket1";

    public void fileUpload(String name, String str){
        s3Client.putObject(BUCKET,name,str);
    }

    public void fileUpload(File f){
        s3Client.putObject(BUCKET,f.getName(),f);
    }

    public String fileUpload(String bucketName, MultipartFile f) throws IOException {
        String name = f.getOriginalFilename();
        String newName = UUID.randomUUID() + "." + FilenameUtils.getExtension(name);
        s3Client.putObject(bucketName,newName, f.getInputStream(),null);
        return newName;
    }

    public String getUrl(String key){
        return s3Client.getUrl(BUCKET,key).toExternalForm();
    }

}
