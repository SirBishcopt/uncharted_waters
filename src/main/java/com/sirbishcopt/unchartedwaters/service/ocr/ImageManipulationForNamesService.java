package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class ImageManipulationForNamesService implements ImageManipulationService {

    public BufferedImage prepareImage(String url, boolean isRightSideNeeded) {

        final double percentageInDoubleOfBasicImageToBeCopiedFromTop = 9.1;

        BufferedImage bufferedImage = null;
        try {
            URL imageFile = new URL(url);
            bufferedImage = ImageIO.read(imageFile);
        } catch (Exception e) {
        }

        BufferedImage preparedImage;
        if (isRightSideNeeded) {
            preparedImage = ImageHelper.getSubImage(bufferedImage, bufferedImage.getWidth() / 2, 0, bufferedImage.getWidth(), (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeCopiedFromTop));
            preparedImage = ImageHelper.invertImageColor(preparedImage);
            preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
            preparedImage = ImageHelper.convertImageToBinary(preparedImage);
        } else {
            preparedImage = ImageHelper.getSubImage(bufferedImage, 0, 0, bufferedImage.getWidth() / 2, (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeCopiedFromTop));
            preparedImage = ImageHelper.invertImageColor(preparedImage);
            preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
            preparedImage = ImageHelper.convertImageToBinary(preparedImage);
        }

        return preparedImage;

    }

}