package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.exceptions.RepositoryException;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import com.sirbishcopt.unchartedwaters.service.ocr.OcrService;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UpdatingServiceTest {

    @InjectMocks
    UpdatingService updatingService;
    @Mock
    LeaderRepository leaderRepository;
    @Mock
    OcrService ocrService;


    @Test
    void updateCity() throws TesseractException, IOException {
        // given
        given(ocrService.doOcr(notNull())).willReturn("url1");

        // when
        // then
    }

    // test happi paf
    // test ocr empty
    // test throws exception


    @Test
    void markCityAsEmptyWywołujeMetodęNaRepozytorium() {

    }
    @Test
    void markCityAsEmptyThrowsExceptionWhenNoCityFound() {

    }

}