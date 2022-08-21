package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public interface OcrService {

    String doOcr(String[] attachments) throws IOException, TesseractException;

}