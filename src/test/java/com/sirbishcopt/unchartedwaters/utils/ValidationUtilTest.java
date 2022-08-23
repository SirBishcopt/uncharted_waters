package com.sirbishcopt.unchartedwaters.utils;

import com.sirbishcopt.unchartedwaters.exceptions.InvalidAttachmentException;
import discord4j.core.object.entity.Attachment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ValidationUtilTest {

    @Test
    void exceptionShouldNotBeThrownIfAttachmentsAreCorrect() {
        // given
        Attachment attachment1 = mock(Attachment.class);
        Attachment attachment2 = mock(Attachment.class);
        List<Attachment> attachments = new ArrayList<>(List.of(attachment1, attachment2));
        // when
        String url1 = "https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.png";
        given(attachment1.getUrl()).willReturn(url1);
        String url2 = "https://cdn.discordapp.com/attachments/970011748956524657/997784017124466728/Screenshot_20220716-103705.jpg";
        given(attachment2.getUrl()).willReturn(url2);
        // then
        assertDoesNotThrow(() -> ValidationUtil.validateAttachments(attachments));
    }

    @Test
    void exceptionShouldBeThrownIfThereAreNoAttachments() {
        // given
        List<Attachment> attachments = new ArrayList<>();
        // then
        assertThrows(InvalidAttachmentException.class,() -> ValidationUtil.validateAttachments(attachments));
    }

    @Test
    void exceptionShouldBeThrownIfThereIsOneAttachment() {
        // given
        Attachment attachment = mock(Attachment.class);
        List<Attachment> attachments = new ArrayList<>(List.of(attachment));
        // when
        String url = "https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.png";
        given(attachment.getUrl()).willReturn(url);
        // then
        assertThrows(InvalidAttachmentException.class,() -> ValidationUtil.validateAttachments(attachments));
    }

    @Test
    void exceptionShouldBeThrownIfThereAreThreeAttachments() {
        // given
        Attachment attachment1 = mock(Attachment.class);
        Attachment attachment2 = mock(Attachment.class);
        Attachment attachment3 = mock(Attachment.class);
        List<Attachment> attachments = new ArrayList<>(List.of(attachment1, attachment2, attachment3));
        // when
        String url1 = "https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.png";
        given(attachment1.getUrl()).willReturn(url1);
        String url2 = "https://cdn.discordapp.com/attachments/970011748956524657/997784017124466728/Screenshot_20220716-103705.jpg";
        given(attachment2.getUrl()).willReturn(url2);
        String url3 = "https://cdn.discordapp.com/attachments/970011748956524657/998501357017370694/image.png";
        given(attachment2.getUrl()).willReturn(url3);
        // then
        assertThrows(InvalidAttachmentException.class,() -> ValidationUtil.validateAttachments(attachments));
    }

    @Test
    void exceptionShouldBeThrownIfOneAttachmentDoesNotEndWithJpgOrPng() {
        // given
        Attachment attachment1 = mock(Attachment.class);
        Attachment attachment2 = mock(Attachment.class);
        List<Attachment> attachments = new ArrayList<>(List.of(attachment1, attachment2));
        // when
        String url1 = "https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.pdf";
        given(attachment1.getUrl()).willReturn(url1);
        String url2 = "https://cdn.discordapp.com/attachments/970011748956524657/997784017124466728/Screenshot_20220716-103705.jpg";
        given(attachment2.getUrl()).willReturn(url2);
        // then
        assertThrows(InvalidAttachmentException.class,() -> ValidationUtil.validateAttachments(attachments));
    }

    @Test
    void exceptionShouldBeThrownIfBothAttachmentsDoesntEndWithJpgOrPng() {
        // given
        Attachment attachment1 = mock(Attachment.class);
        Attachment attachment2 = mock(Attachment.class);
        List<Attachment> attachments = new ArrayList<>(List.of(attachment1, attachment2));
        // when
        String url1 = "https://cdn.discordapp.com/attachments/970010293264580738/998524337646743613/IMG_3327.pdf";
        given(attachment1.getUrl()).willReturn(url1);
        String url2 = "https://cdn.discordapp.com/attachments/970011748956524657/997784017124466728/Screenshot_20220716-103705.pdf";
        given(attachment2.getUrl()).willReturn(url2);
        // then
        assertThrows(InvalidAttachmentException.class,() -> ValidationUtil.validateAttachments(attachments));
    }

}