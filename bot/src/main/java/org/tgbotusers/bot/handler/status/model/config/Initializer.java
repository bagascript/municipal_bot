package org.tgbotusers.bot.handler.status.model.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.tgbotusers.bot.Bot;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import static org.glassfish.jersey.client.ClientProperties.PROXY_PASSWORD;

@Slf4j
@Component
public class Initializer
{
    @Autowired
    private Bot bot;

    @EventListener({ContextRefreshedEvent.class})
    public void init()
    {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
