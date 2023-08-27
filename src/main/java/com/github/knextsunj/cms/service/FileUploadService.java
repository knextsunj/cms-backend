package com.github.knextsunj.cms.service;


import com.oracle.bmc.objectstorage.responses.PutObjectResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {


    PutObjectResponse upload(MultipartFile file);
}
