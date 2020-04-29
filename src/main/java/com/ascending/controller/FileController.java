package com.ascending.controller;

import com.ascending.model.Resource;
import com.ascending.repository.ResourceDao;
import com.ascending.service.FileService;
import com.ascending.service.JWTService;
import com.ascending.service.MessageService;
import com.ascending.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "files")
public class FileController {

    @Autowired
    private FileService fileService;
    Logger logger = (Logger)LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JWTService jwt;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest req) throws IOException {
        logger.info("in: "+file.getOriginalFilename());
        HttpSession session = req.getSession();
        String key = fileService.fileUpload("yyh-buket1",file);
        Long id = new Long((Long)session.getAttribute("appUserId"));
        Resource res = new Resource(id,file.getOriginalFilename(),key, LocalDateTime.now());
        resourceService.save(res);
        messageService.sendMessage(res.toString(),1);
        return key;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List listResources(@PathVariable("id") Long id) throws IOException {
        List<Resource> res = resourceService.getByUserId(id);
        return res.stream().map(i-> Arrays.asList(i.getFileName(),i.getS3key())).collect(Collectors.toList());
    }


}
