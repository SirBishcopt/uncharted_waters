package com.sirbishcopt.unchartedwaters.controller.events;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import com.sirbishcopt.unchartedwaters.service.IncompleteCitiesService;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;

@Controller
public class WhereToGoListener implements EventListener<MessageCreateEvent> {

    private final IncompleteCitiesService incompleteCitiesService;

    public WhereToGoListener(IncompleteCitiesService incompleteCitiesService) {
        this.incompleteCitiesService = incompleteCitiesService;
    }

    public Mono<Void> processCommand(Message message) {

        if (message.getContent().equalsIgnoreCase("!where to go")) {
            Map<CityName, Integer> incompleteCities = incompleteCitiesService.listNamesOfIncompleteCitiesAndAmountOfLackingCommodities();
            String incompleteCitiesMessage = createIncompleteCitiesMessage(incompleteCities);
            return Mono.just(message)
                    .flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(incompleteCitiesMessage))
                    .then();
        }
        return Mono.empty();
    }

    private String createIncompleteCitiesMessage(Map<CityName, Integer> incompleteCities) {

        if (incompleteCities.isEmpty()) {
            return "We're good, Captains :grin: Every city has been checked.";
        } else {
            StringBuilder citiesAndCommodities = new StringBuilder();
            for (Map.Entry<CityName, Integer> incompleteCityEntry : incompleteCities.entrySet()) {
                citiesAndCommodities.append("\n- ")
                        .append(incompleteCityEntry.getKey().toString())
                        .append(" (lacking ")
                        .append(incompleteCityEntry.getValue())
                        .append(" commodities)");
            }
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