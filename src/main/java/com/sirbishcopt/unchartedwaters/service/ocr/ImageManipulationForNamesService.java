package com.sirbishcopt.unchartedwaters.service.ocr;

import com.sirbishcopt.unchartedwaters.exceptions.OcrServiceException;
import net.sourceforge.tess4j.util.ImageHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ImageManipulationForNamesService implements ImageManipulationService {

    final double percentageInDoubleOfBasicImageToBeCopiedFromTop = 9.1;

    public BufferedImage prepareImage(String url, boolean isRightSideNeeded) throws OcrServiceException {

        BufferedImage bufferedImage;
        try {
            URL imageFile = new URL(url);
            bufferedImage = Optional.ofNullable(ImageIO.read(imageFile)).orElseThrow();
        } catch (IOException | NoSuchElementException e) {
            throw new OcrServiceException();
        }

        BufferedImage preparedImage;
        if (isRightSideNeeded) {
            preparedImage = ImageHelper.getSubImage(bufferedImage, bufferedImage.getWidth() / 2, 0,
                    bufferedImage.getWidth(), (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeCopiedFromTop));
        } else {
            preparedImage = ImageHelper.getSubImage(bufferedImage, 0, 0,
                    bufferedImage.getWidth() / 2, (int) (bufferedImage.getHeight() * percentageInDoubleOfBasicImageToBeCopiedFromTop));
        }
        preparedImage = ImageHelper.invertImageColor(preparedImage);
        preparedImage = ImageHelper.convertImageToGrayscale(preparedImage);
        preparedImage = ImageHelper.convertImageToBinary(preparedImage);

        return preparedImage;

    }

}