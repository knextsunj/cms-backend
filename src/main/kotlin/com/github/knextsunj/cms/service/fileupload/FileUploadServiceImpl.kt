package com.github.knextsunj.cms.service.fileupload

import com.github.knextsunj.cms.exception.BusinessException
import com.github.knextsunj.cms.service.FileUploadService
import com.oracle.bmc.objectstorage.ObjectStorage
import com.oracle.bmc.objectstorage.requests.PutObjectRequest
import com.oracle.bmc.objectstorage.responses.PutObjectResponse
import com.oracle.bmc.objectstorage.transfer.UploadConfiguration
import com.oracle.bmc.objectstorage.transfer.UploadManager
import com.oracle.bmc.objectstorage.transfer.UploadManager.UploadRequest
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile


@Service
open class FileUploadServiceImpl : FileUploadService {

    companion object {

        const val bucketName: String = "" //add the bucket name from OCI
        const val namespaceName: String = "" //add the namespace name from OCI

        val logger: Logger = LogManager.getLogger(FileUploadServiceImpl.javaClass)
    }


    @Autowired
    open lateinit var objectStorage: ObjectStorage;

    override fun upload(file: MultipartFile?): PutObjectResponse? {
        var putObjectResponse: PutObjectResponse? = null


        var objectName = file?.originalFilename;
        var inputStream = file?.inputStream;
        val metadata: Map<String, String>? = null

//        val uploadConfiguration = UploadConfiguration.builder()
//            .allowMultipartUploads(true)
//            .allowParallelUploads(true)
//            .build()
//
//        val uploadManager = UploadManager(objectStorage, uploadConfiguration)


        var putObjectRequest: PutObjectRequest = PutObjectRequest
            .builder()
            .namespaceName(namespaceName)
            .bucketName(bucketName)
            .objectName(objectName)
            .contentLength(file?.size)
            .putObjectBody(inputStream)
            .build();


        try {
            putObjectResponse =  objectStorage.putObject(putObjectRequest)
        } catch (ex: java.lang.Exception) {
            logger.error("Error in uploading file to Oracle cloud: {}", ex)
            throw BusinessException("Failed to upload file to cloud object storage");
        }
    finally {
        objectStorage.close()
    }

    }
}