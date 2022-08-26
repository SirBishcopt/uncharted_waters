package com.sirbishcopt.unchartedwaters.utils;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.domain.Inventory;
import com.sirbishcopt.unchartedwaters.exceptions.InvalidCommandException;
import discord4j.core.object.entity.Attachment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ExtractionUtilTest {

    @Test
    void extractCityNameFromMessageShouldExtractCityNameFromString() {
        // given
        String message = "!empty Aden";
        // when
        CityName cityName = ExtractionUtil.extractCityNameFromMessage(message);
        // then
        assertThat(cityName, is(CityName.ADEN));
    }

    @Test
    void exceptionShouldBeThrownIfNoCityNameFoundInMessage() {
        // given
        String message = "!empty";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractCityNameFromMessage(message));
    }

    @Test
    void exceptionShouldBeThrownIfWrongCityNameFoundInMessage() {
        // given
        String message = "!empty Warsaw";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractCityNameFromMessage(message));
    }

    @Test
    void extractUrlFromAttachmentShouldExtractUrlFromAttachment() {
        // given
        Attachment attachment1 = mock(Attachment.class);
        Attachment attachment2 = mock(Attachment.class);
        List<Attachment> attachments = new ArrayList<>(List.of(attachment1, attachment2));

        String url1 = "https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.png";
        given(attachment1.getUrl()).willReturn(url1);
        String url2 = "https://cdn.discordapp.com/attachments/970011748956524657/997784017124466728/Screenshot_20220716-103705.jpg";
        given(attachment2.getUrl()).willReturn(url2);

        // then
        assertThat(ExtractionUtil.extractUrlFromAttachment(attachments)[0], is(url1));
        assertThat(ExtractionUtil.extractUrlFromAttachment(attachments)[1], is(url2));
    }

    @Test
    void extractInventoryFromMessageShouldExtractOneInventoryItemFromMessage() {
        // given
        String message = "!next tea leaves 100";
        // when
        Inventory inventory = ExtractionUtil.extractInventoryFromMessage(message);
        // then
        assertThat(inventory.getCommodities().size(), is(1));
        assertThat(inventory.getCommodities().get(CommodityName.TEA_LEAVES), is(100));
    }

    @Test
    void extractInventoryFromMessageShouldExtractTwoInventoryItemsFromMessage() {
        // given
        String message = "!next alcohol 120 bananas 50";
        // when
        Inventory inventory = ExtractionUtil.extractInventoryFromMessage(message);
        // then
        assertThat(inventory.getCommodities().size(), is(2));
        assertThat(inventory.getCommodities().get(CommodityName.ALCOHOL), is(120));
        assertThat(inventory.getCommodities().get(CommodityName.BANANAS), is(50));
    }

    @Test
    void exceptionShouldBeThrownIfNoCommodityNameFoundInMessage() {
        // given
        String message = "!next 50";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractInventoryFromMessage(message));
    }

    @Test
    void exceptionShouldBeThrownIfWrongCommodityNameFoundInMessage() {
        // given
        String message = "!next crisps 22";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractInventoryFromMessage(message));
    }

    @Test
    void exceptionShouldBeThrownIfNoCommodityPriceFoundInMessage() {
        // given
        String message = "!next diamonds";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractInventoryFromMessage(message));
    }

    @Test
    void exceptionShouldBeThrownIfIncorrectCommodityPriceFoundInMessage() {
        // given
        String message = "!next paper 12d";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractInventoryFromMessage(message));
    }

    @Test
    void exceptionShouldBeThrownIfTwoSameCommoditiesFoundInMessage() {
        // given
        String message = "!next cloth 44 cloth 22";
        // then
        assertThrows(InvalidCommandException.class, () -> ExtractionUtil.extractInventoryFromMessage(message));
    }

}