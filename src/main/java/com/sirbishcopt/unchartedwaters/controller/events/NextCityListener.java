package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.NextCityController;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class NextCityListener implements EventListener<MessageCreateEvent> {

    private NextCityController nextCityController;

    public NextCityListener(NextCityController nextCityController) {
        this.nextCityController = nextCityController;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().toLowerCase().startsWith("!next")) {
            CityName nextCity = nextCityController.getNameOfNextCity(message.getContent(), false);
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