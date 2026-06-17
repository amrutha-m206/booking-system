package com.autoassess.project.autoassessAI.service;

import com.autoassess.project.autoassessAI.entity.Document;
import com.autoassess.project.autoassessAI.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    public Document uploadDocument(MultipartFile file, Long userId) throws IOException {

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();   // creates uploads/ folder automatically
        }
        String filePath=uploadDir+file.getOriginalFilename();
        File dest= new File(filePath);
        file.transferTo(dest);

        Document doc=new Document();
        doc.setUserId(userId);
        doc.setFileName(file.getOriginalFilename());
        doc.setFilePath(filePath);
        doc.setStatus("UPLOADED");

        return documentRepository.save(doc);
    }


}
