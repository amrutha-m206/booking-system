package com.autoassess.project.autoassessAI.utils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class PDFExtractor {
    public static String extractText(String filePath) throws IOException {
        File file=new File(filePath);
        PDDocument document=PDDocument.load(file);
        PDFTextStripper stripper= new PDFTextStripper();
        String text=stripper.getText(document);
        document.close();
        return text;
    }
}
