package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.domain.CityName;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class DiscordCommunication {

    private UpdatingController updatingController;
    private CitiesNeedingUpdateController citiesNeedingUpdateController;
    private NextStepController nextStepController;
    private ResetController resetController;

    public DiscordCommunication(UpdatingController updatingController, CitiesNeedingUpdateController citiesNeedingUpdateController, NextStepController nextStepController, ResetController resetController) {
        this.updatingController = updatingController;
        this.citiesNeedingUpdateController = citiesNeedingUpdateController;
        this.nextStepController = nextStepController;
        this.resetController = resetController;
    }

//TODO create all cities at launch

    @PostConstruct
    public void run() {

        DiscordClient client = DiscordClient.create(System.getenv("bot_token"));

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {

            Mono<Void> handleUpdateCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                // TODO try-catch with message to Discord about Exception
                if (message.getContent().toLowerCase().startsWith("!add")) {
                    CityName cityName = updatingController.getCityName(message.getContent());
                    // TODO method checking if attachments are valid (they exist, there's two of them, they end on .jpg or .png)
                    String[] imagesUrl = getUrlFromAttachment(message.getAttachments());
                    updatingController.updateCity(cityName, imagesUrl);
                    String userName = message.getUserData().username();
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage(cityName + " updated, thanks " + userName + " :kissing_heart:"));
                }
                return Mono.empty();
            }).then();

            Mono<Void> handleEmptyCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                // TODO try-catch with message to Discord about Exception
                if (message.getContent().toLowerCase().startsWith("!empty")) {
                    CityName cityName = updatingController.getCityName(message.getContent());
                    updatingController.markCityAsEmpty(cityName);
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage(cityName + " marked as empty :grimacing:"));
                }
                return Mono.empty();
            }).then();

            Mono<Void> handleUpdateNeededCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                if (message.getContent().equalsIgnoreCase("!update")) {
                    String citiesNeedingUpdate = citiesNeedingUpdateController.getCitiesNeedingUpdate();
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage(citiesNeedingUpdate));
                }
                return Mono.empty();
            }).then();

            Mono<Void> handleNextStepCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                // TODO try-catch with message to Discord about Exception
                if (message.getContent().toLowerCase().startsWith("!next")) {
                    CityName nextStepCity = nextStepController.getNameOfNextCity(message.getContent(), false);
                    String userName = message.getUserData().username();
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage(userName + ", your best shot is " + nextStepCity + " :moneybag:"));
                }
                return Mono.empty();
            }).then();

            Mono<Void> handleLastStepCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                // TODO try-catch with message to Discord about Exception
                if (message.getContent().toLowerCase().startsWith("!last")) {
                    CityName lastStepCity = nextStepController.getNameOfNextCity(message.getContent(), true);
                    String userName = message.getUserData().username();
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage(userName + ", you should sell your goods in " + lastStepCity + " :money_mouth:"));
                }
                return Mono.empty();
            }).then();

            Mono<Void> handleResetCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                // TODO try-catch with message to Discord about Exception
                if (message.getContent().equalsIgnoreCase("!reset")) {
                    resetController.resetDatabase();
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage("Every city is cleared and ready for update! Fair winds and following seas, Captains! :ship:"));
                }
                return Mono.empty();
            }).then();

            return handleUpdateCommand.and(handleEmptyCommand).and(handleUpdateNeededCommand).and(handleNextStepCommand).and(handleLastStepCommand).and(handleResetCommand);
        });

        login.block();

    }

    private String[] getUrlFromAttachment(List<Attachment> attachments) {
        String[] imagesUrl = new String[2];
        for (int i = 0; i < attachments.size(); i++) {
            imagesUrl[i] = attachments.get(i).getUrl();
        }
        return imagesUrl;
    }

}