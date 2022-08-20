package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.service.UpdatingService;
import com.sirbishcopt.unchartedwaters.utils.ExtractionUtil;
import com.sirbishcopt.unchartedwaters.utils.ValidationUtil;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class UpdatingListener implements EventListener<MessageCreateEvent> {

    private final UpdatingService updatingService;

    public UpdatingListener(UpdatingService updatingService) {
        this.updatingService = updatingService;
    }

    public Mono<Void> processCommand(Message message) {

        // TODO error handling
        if (message.getContent().toLowerCase().startsWith("!add")) {
            ValidationUtil.validateAttachments(message.getAttachments());
            String[] imagesUrl = ExtractionUtil.extractUrlFromAttachment(message.getAttachments());
            CityName cityName = ExtractionUtil.extractCityNameFromMessage(message.getContent());
            updatingService.updateCity(cityName, imagesUrl);
            String userName = message.getUserData().username();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(cityName + " updated, thanks " + userName + " :kissing_heart:"))
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