package com.app.train.model.trash.EmailCompartiment;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
@Service
public class PDFService {
    public static void generatePdf(String templatePath, String outputPdfPath, Map<String, String> placeholders) throws IOException
    {
        // Read the HTML template as a string
        String htmlContent = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);
        // Replace placeholders with actual values
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            htmlContent = htmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
        } HtmlConverter.convertToPdf(htmlContent, new FileOutputStream(outputPdfPath));
    }
}
