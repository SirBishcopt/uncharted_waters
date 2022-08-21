package com.sirbishcopt.unchartedwaters.service.ocr;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageManipulationService {

    BufferedImage prepareImage(String url, boolean isRightSideNeeded) throws IOException;

}