package com.api.book.bootrestbook.controllers;

//import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bootrestbook.helper.FileUploadHelper;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;
    
    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        // System.out.println(file.getName());

        try{
        
        //validation
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
        }
        if(!file.getContentType().equals("image/png")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only png are allowed");
        }

        //upload code
        boolean f= fileUploadHelper.uploadFile(file);
        // if(f){
        //     return ResponseEntity.ok("File is successfully uploaded");
        //     //return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(file.getOriginalFilename()).toUriString());
        // }
        if(f){
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + file.getOriginalFilename())
                .toUriString();

            return ResponseEntity.ok("File uploaded successfully at: " + fileDownloadUri);
        }


        }catch(Exception e){
            e.printStackTrace();
        }
    
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong, try again!!");
    }
}
