package com.example.AmazonS3.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.util.HashMap;
import java.util.Map;

import com.example.AmazonS3.Service.AmazonS3ClientService;

@RestController
@RequestMapping("/s3bucket")
public class FileHandlerController {

    @Autowired
    private AmazonS3ClientService amazonS3ClientService;

    @PostMapping("uploadone")
    public Map<String, String> uploadSingleFile(@RequestPart(value = "file") MultipartFile file)
    {

        System.out.println("get data..");
        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @PostMapping("uploadmultiple")
    public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files)throws IOException
    {
        for(MultipartFile uploadedFile : files) {
            System.out.println(uploadedFile.getOriginalFilename());
            uploadSingleFile(uploadedFile);
        }

        return "OK";
    }

    @DeleteMapping
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }


    @DeleteMapping("delete")
    public String deleteFiles(@RequestBody Map<String, Object> userMap)
    {
        String metaInfoId = (String) userMap.get("metaInfoId");

        deleteFile(metaInfoId);
        
        return "OK -->"+metaInfoId;
    }
}