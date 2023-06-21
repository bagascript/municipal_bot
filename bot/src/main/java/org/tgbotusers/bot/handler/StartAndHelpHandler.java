package org.tgbotusers.bot.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.keyboard.StartAndHelpButtons;
import org.tgbotusers.bot.text.BotTextResponse;

import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartAndHelpHandler implements BotTextResponse {
    private final StartAndHelpButtons startAndHelpButtons = new StartAndHelpButtons();
    private final Bot bot;
    private static final String PATH = "C:\\Users\\HP\\Downloads\\tg-bot.gif";

    private void startBot(long chatId, String userName) {
        File file = new File(PATH);
        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);

        SendAnimation sendAnimation = new SendAnimation();
        sendAnimation.setChatId(chatId);
        sendAnimation.setAnimation(inputFile);
        sendAnimation.setCaption("✋ Здравствуйте, " + userName + "!"
                + "\n\n" + "\uD83E\uDD16 Я являюсь виртуальным помощником МО Васильевский."
                + "\n\n" + "\uD83D\uDCC3 Выберите в меню команду или воспользуйтесь /help, " +
                "чтобы я вывел список доступных услуг." + "\n\n" + "⚠ Также, прошу обратить внимание на мой рабочий график," +
                " выбрав раздел в меню или же нажав на кнопку /schedule:");
        sendAnimation.setReplyMarkup(startAndHelpButtons.getInlineKeyboardHelpButton());
        try {
            bot.execute(sendAnimation);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHelpText(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(MENU_TEXT);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendBotSchedule(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(SCHEDULE_TEXT);
        message.setReplyMarkup(startAndHelpButtons. getInlineKeyboardBotScheduleButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void getStartBotCommand(long chatId, String userName) {
        startBot(chatId, userName);
    }

    public void getHelpBotCommand(long chatId) {
        sendHelpText(chatId);
    }

    public void getSendBotSchedule(long chatId) {
        sendBotSchedule(chatId);
    }

}
