package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.UpdatingController;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmptyListener implements EventListener<MessageCreateEvent> {

    private UpdatingController updatingController;

    public EmptyListener(UpdatingController updatingController) {
        this.updatingController = updatingController;
    }

    public Mono<Void> processCommand(Message message) {

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

    private String[] getUrlFromAttachment(List<Attachment> attachments) {
        String[] imagesUrl = new String[2];
        for (int i = 0; i < attachments.size(); i++) {
            imagesUrl[i] = attachments.get(i).getUrl();
        }
        return imagesUrl;
    }

}