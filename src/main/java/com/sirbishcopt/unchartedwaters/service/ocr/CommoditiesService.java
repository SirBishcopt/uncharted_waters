package com.sirbishcopt.unchartedwaters.service.ocr;

import com.sirbishcopt.unchartedwaters.exceptions.OcrServiceException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class CommoditiesService implements OcrService {

    ImageManipulationService imageManipulationService;

    public CommoditiesService(ImageManipulationService imageManipulationService) {
        this.imageManipulationService = imageManipulationService;
    }

    public String doOcr(String[] attachments) throws OcrServiceException {

        ITesseract instance = new Tesseract();
        instance.setDatapath(System.getenv("TESSDATA_PREFIX"));
        instance.setLanguage("eng");

        StringBuilder ocrText = new StringBuilder();

        try {


            for (String attachment : attachments) {
                BufferedImage preparedImageLeftSide = imageManipulationService.prepareImage(attachment, false);
                ocrText.append(instance.doOCR(preparedImageLeftSide));
                ocrText.append("\n ");
                BufferedImage preparedImageRightSide = imageManipulationService.prepareImage(attachment, true);
                ocrText.append(instance.doOCR(preparedImageRightSide));
                ocrText.append("\n ");
            }

            if (ocrText.isEmpty()) {
                throw new OcrServiceException();
            }

        } catch (OcrServiceException | TesseractException e) {
            throw new OcrServiceException(" I've encountered problems while reading your screenshots.");
        }

        return ocrText.toString();

    }

}