package com.sirbishcopt.unchartedwaters.service.ocr;

import com.sirbishcopt.unchartedwaters.exceptions.OcrServiceException;

import java.awt.image.BufferedImage;

public interface ImageManipulationService {

    BufferedImage prepareImage(String url, boolean isRightSideNeeded) throws OcrServiceException;

}