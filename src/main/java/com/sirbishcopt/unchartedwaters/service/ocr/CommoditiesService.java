package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class CommoditiesService implements OcrService {

    ImageManipulationService imageManipulationService;

    public CommoditiesService(ImageManipulationService imageManipulationService) {
        this.imageManipulationService = imageManipulationService;
    }

    public String doOcr(String[] attachments) {
        try {

            ITesseract instance = new Tesseract();
            instance.setDatapath(System.getenv("TESSDATA_PREFIX"));
            instance.setLanguage("eng");

            StringBuilder ocrText = new StringBuilder("");
            for (String attachment : attachments) {
                BufferedImage preparedImageLeftSide = imageManipulationService.prepareImage(attachment, false);
                ocrText.append(instance.doOCR(preparedImageLeftSide));
                ocrText.append("\n ");
                BufferedImage preparedImageRightSide = imageManipulationService.prepareImage(attachment, true);
                ocrText.append(instance.doOCR(preparedImageRightSide));
                ocrText.append("\n ");
            }

            return ocrText.toString();

        } catch (Exception e) {
            // TODO Exception handler
        }

        return "";

    }

}