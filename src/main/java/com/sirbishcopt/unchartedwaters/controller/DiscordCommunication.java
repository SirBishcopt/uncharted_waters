package com.sirbishcopt.unchartedwaters.controller;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

public class DiscordCommunication {

    public void run() {

        // System.getenv("key") gives access to environment variables on Heroku
        DiscordClient client = DiscordClient.create(System.getenv("bot_token"));

        //Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> Mono.empty());

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(ReadyEvent.class, event ->
                        Mono.fromRunnable(() -> {
                            final User self = event.getSelf();
                            System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                        })));

        login.block();

    }

}