package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class CommoditiesService implements OcrService {

    ImageManipulationService imageManipulationService;

    public CommoditiesService(ImageManipulationService imageManipulationService) {
        this.imageManipulationService = imageManipulationService;
    }

    public String doOcr(String[] attachments) throws IOException, TesseractException {

        ITesseract instance = new Tesseract();
        instance.setDatapath(System.getenv("TESSDATA_PREFIX"));
        instance.setLanguage("eng");

        StringBuilder ocrText = new StringBuilder();

        for (String attachment : attachments) {
            BufferedImage preparedImageLeftSide = imageManipulationService.prepareImage(attachment, false);
            ocrText.append(instance.doOCR(preparedImageLeftSide));
            ocrText.append("\n ");
            BufferedImage preparedImageRightSide = imageManipulationService.prepareImage(attachment, true);
            ocrText.append(instance.doOCR(preparedImageRightSide));
            ocrText.append("\n ");
        }


        System.out.println(ocrText.toString());
        return ocrText.toString();

    }

}