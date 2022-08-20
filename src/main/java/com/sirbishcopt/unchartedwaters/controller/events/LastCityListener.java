package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.domain.Inventory;
import com.sirbishcopt.unchartedwaters.exceptions.InvalidCommandException;
import com.sirbishcopt.unchartedwaters.exceptions.RepositoryException;
import com.sirbishcopt.unchartedwaters.service.NextCityService;
import com.sirbishcopt.unchartedwaters.utils.ExtractionUtil;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class LastCityListener implements EventListener<MessageCreateEvent> {

    private final NextCityService nextCityService;

    public LastCityListener(NextCityService nextCityService) {
        this.nextCityService = nextCityService;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().toLowerCase().startsWith("!last")) {

            Inventory inventory;
            CityName lastCity;
            try {
                inventory = ExtractionUtil.extractInventoryFromMessage(message.getContent());
                lastCity = nextCityService.getNextCity(inventory, true);
            } catch (InvalidCommandException | RepositoryException e) {
                return Mono.just(message)
                        .flatMap(Message::getChannel)
                        .flatMap(channel -> channel.createMessage(message.getAuthor().get().getMention() + e.getMessage()))
                        .then();
            }

            String userName = message.getUserData().username();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(userName + ", you should sell your goods in " + lastCity + " :money_mouth:"))
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