package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.CitiesNeedingUpdateController;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UpdateNeededListener implements EventListener<MessageCreateEvent> {

    private CitiesNeedingUpdateController citiesNeedingUpdateController;

    public UpdateNeededListener(CitiesNeedingUpdateController citiesNeedingUpdateController) {
        this.citiesNeedingUpdateController = citiesNeedingUpdateController;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().equalsIgnoreCase("!update")) {
            String citiesNeedingUpdate = citiesNeedingUpdateController.getCitiesNeedingUpdate();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(citiesNeedingUpdate))
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