package com.github.knextsunj.cms.controller;


import com.github.knextsunj.cms.dto.CustomerDTO;
import com.github.knextsunj.cms.exception.ValidationFailureException;
import com.github.knextsunj.cms.service.FileUploadService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;


@RestController
@RequestMapping("/idProofUpload")
public class IdProofUploadController {

    private static final Logger logger = LogManager.getLogger(IdProofUploadController.class.getName());

    @Autowired
    private FileUploadService fileUploadService;

//,consumes = {MULTIPART_FORM_DATA_VALUE}
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
//    @PostMapping(value = "/upload/{customerId}")
    public ResponseEntity<String> uploadData(@RequestPart("customerData") CustomerDTO customerDTO, @RequestPart("file") MultipartFile file) throws Exception {
//        if (file == null) {
//            logger.error("No file available as input for customerId: {}", customerId);
//            throw new ValidationFailureException("File not provided for upload");
//
//        }
        logger.info("JSON Request Body Data: {}", customerDTO);

//        InputStream inputStream = file.getInputStream();
//        String originalName = file.getOriginalFilename();
//        String name = file.getName();
//        String contentType = file.getContentType();
//        long size = file.getSize();
//        logger.info("inputStream: " + inputStream);
//        logger.info("originalName: " + originalName);
//        logger.info("name: " + name);
//        logger.info("contentType: " + contentType);
//        logger.info("size: " + size);
        // Do processing with uploaded file data in Service layer
//        return new ResponseEntity<String>(originalName, HttpStatus.OK);

//        for(MultipartFile file : files) {
            logger.info(file.getOriginalFilename());
//        }
        fileUploadService.upload(file);
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}