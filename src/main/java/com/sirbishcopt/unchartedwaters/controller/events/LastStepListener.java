package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.NextStepController;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LastStepListener implements EventListener<MessageCreateEvent> {

    private NextStepController nextStepController;

    public LastStepListener(NextStepController nextStepController) {
        this.nextStepController = nextStepController;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().toLowerCase().startsWith("!last")) {
            CityName lastStepCity = nextStepController.getNameOfNextCity(message.getContent(), true);
            String userName = message.getUserData().username();
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(userName + ", you should sell your goods in " + lastStepCity + " :money_mouth:"))
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