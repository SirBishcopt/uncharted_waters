package com.sirbishcopt.unchartedwaters.services;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.LoadLibs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class OcrService {

    public String doOCR(String url) {
        try {
            URL imageFile = new URL(url); // 1
            BufferedImage bufferedImage = ImageIO.read(imageFile); // 2
            ITesseract instance = new Tesseract(); // 3
            instance.setDatapath("E:\\Users\\Stacjonarny\\IdeaProjects\\unchartedwaters\\src\\main\\resources"); // 4
            instance.setLanguage("eng"); // 5
            System.out.println(instance.doOCR(bufferedImage));
            return instance.doOCR(bufferedImage); // 6
        } catch (Exception e) {
            // TODO Exception handler
            e.printStackTrace();
        }
        return "";
    }

}