package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Inventory;
import com.sirbishcopt.unchartedwaters.service.NextCityService;
import com.sirbishcopt.unchartedwaters.utils.ExtractionUtil;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class NextCityListener implements EventListener<MessageCreateEvent> {

    private final NextCityService nextCityService;

    public NextCityListener(NextCityService nextCityService) {
        this.nextCityService = nextCityService;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().toLowerCase().startsWith("!next")) {
            Inventory inventory = ExtractionUtil.extractInventoryFromMessage(message.getContent());
            CityName nextCity = nextCityService.getNextCity(inventory, false);
            String userName = message.getUserData().username();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(userName + ", your best shot is " + nextCity + " :moneybag:"))
                    .then();
        }
        return Mono.empty();
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event.getMessage());
    }

}