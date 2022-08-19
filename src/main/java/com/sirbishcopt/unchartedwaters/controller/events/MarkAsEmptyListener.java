package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.UpdatingController;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MarkAsEmptyListener implements EventListener<MessageCreateEvent> {

    private UpdatingController updatingController;

    public MarkAsEmptyListener(UpdatingController updatingController) {
        this.updatingController = updatingController;
    }

    public Mono<Void> processCommand(Message message) {

        // TODO error handling
        if (message.getContent().toLowerCase().startsWith("!empty")) {
            CityName cityName = updatingController.getCityName(message.getContent());
            updatingController.markCityAsEmpty(cityName);
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(cityName + " marked as empty :grimacing:"))
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