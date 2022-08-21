package com.sirbishcopt.unchartedwaters.utils;

import com.sirbishcopt.unchartedwaters.exceptions.InvalidAttachmentException;
import discord4j.core.object.entity.Attachment;

import java.util.List;

public final class ValidationUtil {

    public static void validateAttachments(List<Attachment> attachments) {
        if (attachments.size() != 2 ||
                (!attachments.get(0).getUrl().toLowerCase().endsWith("jpg") &&
                        !attachments.get(0).getUrl().toLowerCase().endsWith("png")) ||
                (!attachments.get(1).getUrl().toLowerCase().endsWith("jpg") &&
                        !attachments.get(1).getUrl().toLowerCase().endsWith("png"))) {
            System.out.println(!attachments.get(0).getUrl().toLowerCase().endsWith("jpg"));

            throw new InvalidAttachmentException(" Make sure you've added two screenshots to your message.");
        }
    }

}