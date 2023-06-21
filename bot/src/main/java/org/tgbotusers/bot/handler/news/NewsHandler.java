package org.tgbotusers.bot.handler.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.keyboard.NewsButtons;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsHandler
{
    private final Bot bot;

    private void sendSelectionNewsMenu(long chatId) {
        NewsButtons newsButtons = new NewsButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите раздел:");
        message.setReplyMarkup(newsButtons.getInlineKeyboardSectionNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void getSendSelectionNewsMenu(long chatId) {
        sendSelectionNewsMenu(chatId);
    }
}
