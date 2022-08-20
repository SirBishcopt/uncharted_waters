package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.service.ResetService;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class ResetListener implements EventListener<MessageCreateEvent> {

    private final ResetService resetService;

    public ResetListener(ResetService resetService) {
        this.resetService = resetService;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().equalsIgnoreCase("!reset")) {
            resetService.resetDatabase();
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

}