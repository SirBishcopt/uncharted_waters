package com.sirbishcopt.unchartedwaters.utils;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.CommodityName;
import com.sirbishcopt.unchartedwaters.domain.Inventory;
import discord4j.core.object.entity.Attachment;

import java.util.List;
import java.util.regex.Pattern;

public final class ExtractionUtil {

    public static CityName extractCityNameFromMessage(String message) {
        // TODO throw exception if CityName not found
        for (CityName cityName : CityName.values()) {
            Pattern compiledPattern = Pattern.compile(cityName.getAbbrev().toLowerCase());
            if (compiledPattern.matcher(message.toLowerCase()).find()) {
                return cityName;
            }
        }
        return null;
    }

    public static  String[] extractUrlFromAttachment(List<Attachment> attachments) {
        String[] imagesUrl = new String[2];
        for (int i = 0; i < attachments.size(); i++) {
            imagesUrl[i] = attachments.get(i).getUrl();
        }
        return imagesUrl;
    }

    public static Inventory extractInventoryFromMessage(String message) {

        Inventory inventory = new Inventory();
        String[] messageInLines = message.split("\\s+");

        // TODO what if no commodity or no price
        for (int i = 0; i < messageInLines.length; i++) {
            for (CommodityName commodityName : CommodityName.values()) {
                Pattern compiledPatternCommodity = Pattern.compile(commodityName.getAbbrev().toLowerCase());
                if (compiledPatternCommodity.matcher(messageInLines[i].toLowerCase()).find()) {
                    int price = Integer.parseInt(messageInLines[i + 1]);
                    inventory.addCommodityAndAmount(commodityName, price);
                }
            }
        }

        return inventory;
    }

}