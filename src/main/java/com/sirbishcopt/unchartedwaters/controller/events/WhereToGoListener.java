package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.controller.IncompleteCitiesController;
import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class WhereToGoListener implements EventListener<MessageCreateEvent> {

    private IncompleteCitiesController incompleteCitiesController;

    public WhereToGoListener(IncompleteCitiesController incompleteCitiesController) {
        this.incompleteCitiesController = incompleteCitiesController;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().equalsIgnoreCase("!where to go")) {
            Map<CityName, Integer> incompleteCities = incompleteCitiesController.getIncompleteCities();
            String incompleteCitiesMessage = createIncompleteCitiesMessage(incompleteCities);
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(incompleteCitiesMessage))
                    .then();
        }
        return Mono.empty();
    }

    private String createIncompleteCitiesMessage(Map<CityName, Integer> incompleteCities) {

        String citiesAndCommodities = "";
        for (Map.Entry<CityName, Integer> incompleteCityEntry : incompleteCities.entrySet()) {
            citiesAndCommodities = citiesAndCommodities + "\n- " + incompleteCityEntry.getKey().toString()
                    + " (lacking " + incompleteCityEntry.getValue() + " commodities)";
        }

        if (citiesAndCommodities.isEmpty()) {
            return "We're good, Captains :grin: Every city has been checked.";
        } else {
            return "All hands on deck, Captains! We still need to update:" + citiesAndCommodities;
        }

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