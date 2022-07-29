package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.util.ImageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

// TODO check if correct annotation
@Service
public class ImageManipulationForCommoditiesService implements ImageManipulationService {

    public BufferedImage prepareImage(String url, boolean isRightSideNeeded) {

        BufferedImage bufferedImage = null;
        try {
            URL imageFile = new URL(url);
            bufferedImage = ImageIO.read(imageFile);
        } catch (Exception e) {
        }

        BufferedImage preparedImage = null;
        if (isRightSideNeeded) {
            preparedImage = ImageHelper.getSubImage(bufferedImage, bufferedImage.getWidth() / 2, 600, bufferedImage.getWidth() / 2, 1800);
            preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
            preparedImage = ImageHelper.convertImageToBinary(preparedImage);
        } else {
            preparedImage = ImageHelper.getSubImage(bufferedImage, 0, 600, bufferedImage.getWidth() / 2, 1800);
            preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
            preparedImage = ImageHelper.convertImageToBinary(preparedImage);
        }

        return preparedImage;

    }

}