package org.tgbotusers.bot.handler.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.handler.files.FileHandler;
import org.tgbotusers.bot.handler.files.OtherFileMsgSender;
import org.tgbotusers.bot.handler.status.model.repository.*;
import org.tgbotusers.bot.handler.textappeal.TextAppealHandler;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.handler.status.model.config.BotConfig;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultHandler {
    private final Bot bot;

    private final BotConfig config;

    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final AppealDAO appealDAO;

    @Autowired
    private final FileDAO fileDAO;

    private void sendDefaultMessage(long chatId, String receivedMessage, PhotoSize photo, Document document) {
        PersonalDataHandler personalDataHandler = new PersonalDataHandler(bot, appealDAO);
        OtherFileMsgSender otherFileMsgSender = new OtherFileMsgSender(bot, userDAO, appealDAO, fileDAO);
        TextAppealHandler textAppealHandler = new TextAppealHandler(bot, appealDAO);
        FileHandler fileHandler = new FileHandler(bot, config, appealDAO);

        HashMap<Long, String> statusMap = PersonalDataHandler.STATUS_MAP;

        if (statusMap.get(chatId).equals("ФИО")) {
            personalDataHandler.getEndRecordFullName(chatId, receivedMessage);
        } else if (statusMap.get(chatId).equals("Телефон")) {
            personalDataHandler.getEndRecordPhoneNumber(chatId, receivedMessage);
        } else if (statusMap.get(chatId).equals("Почта")) {
            personalDataHandler.getEndRecordEmail(chatId, receivedMessage);
        } else if (statusMap.get(chatId).equals("Адрес")) {
            textAppealHandler.getEndRecordAddress(chatId, receivedMessage);
        } else if (statusMap.get(chatId).equals("Текст")) {
            textAppealHandler.getEndRecordTextAppeal(chatId, receivedMessage);
        } else if (statusMap.get(chatId).equals("Файл")) {
            fileHandler.getEndRecordFile(chatId, receivedMessage, photo, document);
        } else if (statusMap.get(chatId).equals("Конец")) {
            otherFileMsgSender.getSetFinalAppealStatus(chatId);
        }
    }

    public void getDefaultMessage(long chatId, String receivedMessage, PhotoSize photo, Document document) {
        sendDefaultMessage(chatId, receivedMessage, photo, document);
    }
}
