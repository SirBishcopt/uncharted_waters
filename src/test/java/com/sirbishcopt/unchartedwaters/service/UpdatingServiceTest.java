package com.sirbishcopt.unchartedwaters.service;

import com.sirbishcopt.unchartedwaters.domain.City;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.repository.LeaderRepository;
import com.sirbishcopt.unchartedwaters.service.ocr.OcrService;
import com.sirbishcopt.unchartedwaters.testdata.TestData;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UpdatingServiceTest {

    @InjectMocks
    UpdatingService updatingService;
    @Mock
    LeaderRepository leaderRepository;
    @Mock
    OcrService ocrService;


    @Test
    void updateCityShouldSetCommodityPricesBasedOnOcrText() throws TesseractException, IOException {
        // given
        String[] attachments = new String[]{"url1", "url2"};
        given(leaderRepository.getCityByName(notNull())).willReturn(new City(CityName.EDO));
        given(ocrService.doOcr(notNull())).willReturn(TestData.OCR_TEXT);
        ArgumentCaptor<City> argumentCaptor = ArgumentCaptor.forClass(City.class);
        // when
        updatingService.updateCity(CityName.EDO, attachments);
        // then
        then(leaderRepository).should().save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(TestData.EXPECTED_CITY));
    }

    @Test
    void updateCityShouldSetCommodityPricesToZeroBasedOnEmptyOcrText() throws TesseractException, IOException {
        // given
        String[] attachments = new String[]{"url1", "url2"};
        given(leaderRepository.getCityByName(notNull())).willReturn(new City(CityName.EDO));
        given(ocrService.doOcr(notNull())).willReturn("");
        ArgumentCaptor<City> argumentCaptor = ArgumentCaptor.forClass(City.class);
        // when
        updatingService.updateCity(CityName.EDO, attachments);
        // then
        then(leaderRepository).should().save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue(), is(new City(CityName.EDO)));
    }

    @Test
    void markCityAsEmptyShouldCallMethodMarkCityAsEmptyFromLeaderRepository() {
        //when
        updatingService.markCityAsEmpty(CityName.LONDON);
        //then
        then(leaderRepository).should().markCityAsEmpty(CityName.LONDON);
    }

}