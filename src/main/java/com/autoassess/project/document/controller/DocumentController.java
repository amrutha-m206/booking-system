package com.autoassess.project.document.controller;

import com.autoassess.project.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.autoassess.project.document.entity.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public Document upload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        return documentService.uploadDocument(file,userId);
    }

}
