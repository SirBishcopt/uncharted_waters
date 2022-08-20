package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.util.ImageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@Service
public class ImageManipulationForCommoditiesService implements ImageManipulationService {

    public BufferedImage prepareImage(String url, boolean isRightSideNeeded) {

        final double percentageInDoubleOfBasicImageToBeDeletedFromTop = 0.3;
        final double percentageInDoubleOfBasicImageToBeCopiedFromTop = 0.61;

        BufferedImage bufferedImage = null;
        try {
            URL imageFile = new URL(url);
            bufferedImage = ImageIO.read(imageFile);
        } catch (Exception e) {
        }

        // TODO Optional.ofNullable(bufferedImage).orElseThrow()

        BufferedImage preparedImage;
        int yStartPoint = (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeDeletedFromTop);
        int yEndPoint = (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeCopiedFromTop);
        if (isRightSideNeeded) {
            preparedImage = ImageHelper.getSubImage(bufferedImage, bufferedImage.getWidth() / 2, yStartPoint, bufferedImage.getWidth() / 2, yEndPoint);
            preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
            preparedImage = ImageHelper.convertImageToBinary(preparedImage);
        } else {
            preparedImage = ImageHelper.getSubImage(bufferedImage, 0, yStartPoint, bufferedImage.getWidth() / 2, yEndPoint);
            preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
            preparedImage = ImageHelper.convertImageToBinary(preparedImage);
        }

        return preparedImage;

    }

}