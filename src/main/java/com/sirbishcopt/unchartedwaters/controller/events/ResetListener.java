package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.ResetController;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ResetListener implements EventListener<MessageCreateEvent> {

    private ResetController resetController;

    public ResetListener(ResetController resetController) {
        this.resetController = resetController;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().equalsIgnoreCase("!reset")) {
            resetController.resetDatabase();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage("Every city is cleared and ready for update! Fair winds and following seas, Captains! :ship:"))
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