package com.sirbishcopt.unchartedwaters.service.ocr;

import java.awt.image.BufferedImage;

public interface ImageManipulationService {

    BufferedImage prepareImage(String url, boolean isRightSideNeeded);

}