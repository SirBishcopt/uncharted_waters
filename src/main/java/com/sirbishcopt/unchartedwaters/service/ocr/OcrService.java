package com.sirbishcopt.unchartedwaters.service.ocr;

import java.awt.image.BufferedImage;

public interface OcrService {

    public String doOcr(BufferedImage preparedImage);

}