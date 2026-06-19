package com.autoassess.project.document.service;

import com.autoassess.project.document.entity.Document;
import com.autoassess.project.document.repository.DocumentRepository;
import com.autoassess.project.document.utils.PDFExtractor;
import com.autoassess.project.kafka.DocumentEvent;
import com.autoassess.project.kafka.producer.DocumentProducer;
import com.autoassess.project.security.AuthUtil;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private DocumentProducer documentProducer;


    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    public Document uploadDocument(MultipartFile file) throws IOException {

        String email= authUtil.getCurrentUserEmail();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        log.info("Upload request received for userId: {}", user.getId());

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();   // creates uploads/ folder automatically
        }
        String filePath=uploadDir+file.getOriginalFilename();
        File dest= new File(filePath);
        file.transferTo(dest);

        Document doc=new Document();
        doc.setUserId(user.getId());
        doc.setFileName(file.getOriginalFilename());
        doc.setFilePath(filePath);
        doc.setStatus("UPLOADED");

        doc=documentRepository.save(doc);
        log.info("Document saved with ID: {} and status: UPLOADED", doc.getId());

        DocumentEvent event=new DocumentEvent();
        event.setDocumentId(doc.getId());
        event.setUserId(user.getId());
        documentProducer.sendDocumentEvent(event);

        return doc;
    }
    public Document processDocument(Document doc) throws IOException{
        try{
            //marking processing
            log.info("Processing document ID: {}", doc.getId());

            doc.setStatus("PROCESSING");
            documentRepository.save(doc);

            //extract text
            String text= PDFExtractor.extractText(doc.getFilePath());
            log.info("Text extracted successfully (length: {})", text.length());


            //update final state
            doc.setExtractedText(text);
            doc.setStatus("PROCESSED");

            Document saved = documentRepository.save(doc);

            log.info("Document PROCESSED successfully ID: {}", saved.getId());

            return saved;
        }catch(Exception e){
            log.error("Error processing document ID: {}", doc.getId(), e);

            doc.setStatus("FAILED");
           documentRepository.save(doc);
           throw e;
        }
    }


}
