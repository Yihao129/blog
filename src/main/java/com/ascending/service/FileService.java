package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.validator.internal.metadata.aggregated.MetaDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FileService {

    private AmazonS3 s3Client;

    @Value("${aws.s3.bucketName}")
    private String BUCKET;

    public FileService(@Autowired AmazonS3 amazonS3){
        this.s3Client = amazonS3;
    }

    public void fileUpload(String name, String str){
        s3Client.putObject(BUCKET,name,str);
    }

    public void fileUpload(File f){
        s3Client.putObject(BUCKET,f.getName(),f);
    }

    public String fileUpload(MultipartFile f) throws IOException {
        String name = f.getOriginalFilename();
        String newName = UUID.randomUUID() + "." + FilenameUtils.getExtension(name);
        ObjectMetadata  meta = new ObjectMetadata();
        meta.addUserMetadata("type",f.getContentType());
        meta.addUserMetadata("size", String.valueOf(f.getSize()));
        s3Client.putObject(BUCKET,newName, f.getInputStream(),meta);
        return newName;
    }

    public String getUrl(String key){
        return s3Client.getUrl(BUCKET,key).toExternalForm();
    }

}
