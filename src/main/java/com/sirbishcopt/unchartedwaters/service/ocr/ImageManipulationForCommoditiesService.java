package com.sirbishcopt.unchartedwaters.service.ocr;

import net.sourceforge.tess4j.util.ImageHelper;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Service
public class ImageManipulationForCommoditiesService implements ImageManipulationService {

    final double percentageInDoubleOfBasicImageToBeDeletedFromTop = 0.3;
    final double percentageInDoubleOfBasicImageToBeCopiedFromTop = 0.61;

    public BufferedImage prepareImage(String url, boolean isRightSideNeeded) throws IOException {

        BufferedImage bufferedImage;
        URL imageFile = new URL(url);
        bufferedImage = Optional.ofNullable(ImageIO.read(imageFile)).orElseThrow();

        BufferedImage preparedImage;
        int yStartPoint = (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeDeletedFromTop);
        int yEndPoint = (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeCopiedFromTop);
        if (isRightSideNeeded) {
            preparedImage = ImageHelper.getSubImage(bufferedImage, bufferedImage.getWidth() / 2, yStartPoint, bufferedImage.getWidth() / 2, yEndPoint);
        } else {
            preparedImage = ImageHelper.getSubImage(bufferedImage, 0, yStartPoint, bufferedImage.getWidth() / 2, yEndPoint);
        }
        preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
        preparedImage = ImageHelper.convertImageToBinary(preparedImage);

        return preparedImage;

    }

}