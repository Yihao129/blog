package com.ascending.service;

import com.amazonaws.services.s3.AmazonS3;
import com.ascending.init.AppInit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class)
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Autowired
    private AmazonS3 amazonS3;

    @Test
    public void fileUploadStringTest(){
        fileService.fileUpload("1.txt","abc");
        verify(amazonS3,times(1)).putObject(anyString(),anyString(),anyString());
    }

    @Test
    public void fileUploadFileTest(){
        File f = mock(File.class);
        fileService.fileUpload(f);
        verify(amazonS3,times(1)).putObject(anyString(),any(),any(File.class));
    }

    @Test
    public void fileUploadMultipartFileTest() throws IOException {
        MultipartFile f = mock(MultipartFile.class);
        when(f.getInputStream()).thenReturn(mock(InputStream.class));
        fileService.fileUpload(f);
        verify(amazonS3,times(1)).putObject(anyString(),anyString(),any(InputStream.class),any());
    }

    @Test
    public void getUrlTest() throws MalformedURLException {
        when(amazonS3.getUrl(anyString(),anyString())).thenReturn(new URL("http","www.baidu.com",80,"xxx"));
        String r = fileService.getUrl("xxx");
        verify(amazonS3,times(1)).getUrl(anyString(),anyString());
    }


}
