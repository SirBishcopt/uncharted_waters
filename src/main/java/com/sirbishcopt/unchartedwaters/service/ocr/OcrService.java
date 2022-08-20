package com.sirbishcopt.unchartedwaters.service.ocr;

import com.sirbishcopt.unchartedwaters.exceptions.OcrServiceException;

public interface OcrService {

    String doOcr(String[] attachments) throws OcrServiceException;

}