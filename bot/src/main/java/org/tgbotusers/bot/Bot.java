package org.tgbotusers.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.tgbotusers.bot.handler.main.Handler;
import org.tgbotusers.bot.handler.status.model.config.BotConfig;
import org.tgbotusers.bot.handler.status.model.repository.*;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot
{
    private final BotConfig config;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AppealDAO appealDAO;

    @Autowired
    private FileDAO fileDAO;

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Handler handler = new Handler(config, userDAO, appealDAO, fileDAO);
        handler.getUpdateMessage(update);
    }
}
