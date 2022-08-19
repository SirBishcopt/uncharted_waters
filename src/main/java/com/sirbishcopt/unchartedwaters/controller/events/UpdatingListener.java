package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.UpdatingController;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class UpdatingListener implements EventListener<MessageCreateEvent> {

    private UpdatingController updatingController;

    public UpdatingListener(UpdatingController updatingController) {
        this.updatingController = updatingController;
    }

    public Mono<Void> processCommand(Message message) {

        // TODO error handling
        if (message.getContent().toLowerCase().startsWith("!add")) {
            CityName cityName = updatingController.getCityName(message.getContent());
            // TODO method checking if attachments are valid (they exist, there's two of them, they end on .jpg or .png)
            String[] imagesUrl = getUrlFromAttachment(message.getAttachments());
            updatingController.updateCity(cityName, imagesUrl);
            String userName = message.getUserData().username();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(cityName + " updated, thanks " + userName + " :kissing_heart:"))
                    .then();
        }
        return Mono.empty();
    }

    private String[] getUrlFromAttachment(List<Attachment> attachments) {
        String[] imagesUrl = new String[2];
        for (int i = 0; i < attachments.size(); i++) {
            imagesUrl[i] = attachments.get(i).getUrl();
        }
        return imagesUrl;
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