package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.service.UpdatingService;
import com.sirbishcopt.unchartedwaters.utils.ExtractionUtil;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class MarkAsEmptyListener implements EventListener<MessageCreateEvent> {

    private final UpdatingService updatingService;

    public MarkAsEmptyListener(UpdatingService updatingService) {
        this.updatingService = updatingService;
    }

    public Mono<Void> processCommand(Message message) {

        // TODO error handling
        if (message.getContent().toLowerCase().startsWith("!empty")) {
            CityName cityName = ExtractionUtil.extractCityNameFromMessage(message.getContent());
            updatingService.markCityAsEmpty(cityName);
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