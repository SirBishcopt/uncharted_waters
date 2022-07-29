package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

// TODO check if correct annotation
@Service
public class Tess4jService implements OcrService {

    public String doOcr(BufferedImage preparedImage) {

        try {
            ITesseract instance = new Tesseract();
            // TODO set right Datapath
            instance.setDatapath("E:\\Users\\Stacjonarny\\IdeaProjects\\unchartedwaters\\src\\main\\resources");
            instance.setLanguage("eng");
            System.out.println(instance.doOCR(preparedImage));
            return instance.doOCR(preparedImage);
        } catch (Exception e) {
            // TODO Exception handler
        }
        return "";

    }

}