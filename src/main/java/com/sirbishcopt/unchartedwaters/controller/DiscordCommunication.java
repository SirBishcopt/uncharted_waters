package com.sirbishcopt.unchartedwaters.controller;

import com.sirbishcopt.unchartedwaters.controller.events.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DiscordCommunication {

    private final Logger logger = LogManager.getLogger(DiscordCommunication.class);

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {

        GatewayDiscordClient client = null;

        try {
            client = DiscordClientBuilder.create(System.getenv("BOT_TOKEN"))
                    .build()
                    .login()
                    .block();
            for (EventListener<T> listener : eventListeners) {
                client.on(listener.getEventType())
                        .flatMap(listener::execute)
                        .subscribe();
            }
        } catch (Exception e) {
            logger.info("Exception at application setting.");
        }

        return client;

    }

}