package com.sirbishcopt.unchartedwaters.service.collecting;

import java.awt.image.BufferedImage;

public interface ImageManipulationService {

    BufferedImage prepareImage(String url, boolean isRightSideNeeded);

}