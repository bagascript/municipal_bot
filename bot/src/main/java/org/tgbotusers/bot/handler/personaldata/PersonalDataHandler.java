package org.tgbotusers.bot.handler.personaldata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.handler.textappeal.TextAppealHandler;
import org.tgbotusers.bot.keyboard.PersonalDataButtons;
import org.tgbotusers.bot.handler.status.AppealStatus;
import org.tgbotusers.bot.keyboard.SendingAppealFilesButtons;
import org.tgbotusers.bot.text.BotTextResponse;
import org.tgbotusers.bot.text.appeal.ErrorTextResponse;
import org.tgbotusers.bot.handler.status.model.entity.User;
import org.tgbotusers.bot.handler.status.model.repository.AppealDAO;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class PersonalDataHandler implements ErrorTextResponse, BotTextResponse {
    public static final HashMap<Long, User> userDataMap = new HashMap<>();

    private static User user = new User();

    private AppealStatus appealStatus;

    public static HashMap<Long, String> STATUS_MAP = new HashMap<>();
    public static HashMap<Long, User> mapDataForAppeal = new HashMap<>();

    private final PersonalDataButtons personalDataButtons = new PersonalDataButtons();
    private final SendingAppealFilesButtons sendingAppealFilesButtons = new SendingAppealFilesButtons();
    private final Bot bot;

    @Autowired
    private final AppealDAO appealDAO;

    private void recordFullName(long chatId) {
        SendMessage sendFullNameMessage;


        if (!STATUS_MAP.get(chatId).equals("Конец")) {
            sendFullNameMessage = new SendMessage();
            sendFullNameMessage.setChatId(chatId);
            sendFullNameMessage.setText("Введите ваше ФИО:");
            appealStatus = new AppealStatus();
            appealStatus.setStatus("ФИО");
            String status = appealStatus.getStatus();
            STATUS_MAP.put(chatId, status);
        } else {
            sendFullNameMessage = sendButtonRequestErrorMsg(chatId);
        }
        try {
            bot.execute(sendFullNameMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void endRecordFullName(long chatId, String receivedMessage) {
        SendMessage endFullNameMessage;

        if (receivedMessage.matches("(?<=\s|^)[А-Яа-яёЁ]+(\s+(?<=\s|^)[А-Яа-яёЁ]+)\\.?" +
                "(\s+(?<=\s|^)[А-Яа-яёЁ]+)?\\.?")) {

            if (!STATUS_MAP.get(chatId).equals("Конец")) {
                endFullNameMessage = new SendMessage();
                endFullNameMessage.setChatId(chatId);
                endFullNameMessage.setText("☑ Подтверждено.\n\nИдём дальше или хотите изменить ФИО?");
                endFullNameMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardFIOButton());

                appealStatus = new AppealStatus();
                appealStatus.setStatus("ФИО подтверждено");
                String status = appealStatus.getStatus();
                STATUS_MAP.put(chatId, status);
            } else {
                endFullNameMessage = sendButtonRequestErrorMsg(chatId);
            }

            user.setChatId(chatId);
            user.setFullName(receivedMessage);
            if (!userDataMap.containsKey(chatId)) {
                userDataMap.put(chatId, user);
            } else if (userDataMap.get(chatId).getChatId() == chatId) {
                userDataMap.put(chatId, user);
            }
            user = new User();
        } else {
            if (!STATUS_MAP.get(chatId).equals("Конец")) {
                endFullNameMessage = new SendMessage();
                endFullNameMessage.setChatId(chatId);
                endFullNameMessage.setText(ERROR_TEXT);
                endFullNameMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardFIOErrorButton());
            } else {
                endFullNameMessage = sendButtonRequestErrorMsg(chatId);
            }
        }

        try {
            bot.execute(endFullNameMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendFIOBadRequestErrorMsg(long chatId) {
        SendMessage sendErrorMessage = new SendMessage();
        sendErrorMessage.setChatId(chatId);
        sendErrorMessage.setText(FIO_BAD_REQUEST_TEXT);
        sendErrorMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardFIOButton());
        try {
            bot.execute(sendErrorMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void askForSkipOrRecordPhoneData(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(SKIP_OR_WRITE_PHONE_TEXT);
        sendMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardSkipOrRecordPhoneButton());
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void recordPhoneNumber(long chatId) {
        SendMessage sendPhoneNumberMessage;

        if (!STATUS_MAP.get(chatId).equals("Конец")) {
            sendPhoneNumberMessage = new SendMessage();
            sendPhoneNumberMessage.setChatId(chatId);
            sendPhoneNumberMessage.setText("Введите номер мобильного телефона:");
            appealStatus = new AppealStatus();
            appealStatus.setStatus("Телефон");
            String status = appealStatus.getStatus();
            STATUS_MAP.put(chatId, status);
        } else {
            sendPhoneNumberMessage = sendButtonRequestErrorMsg(chatId);
        }
        try {
            bot.execute(sendPhoneNumberMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void endRecordPhoneNumber(long chatId, String receivedMessage) {
        SendMessage endPhoneNumberMessage;

        if (receivedMessage.matches("\\+?[7|8]\\(?\\d{3}\\)?\\d{3}-?\\d{2}-?\\d{2}")) {

            if (!STATUS_MAP.get(chatId).equals("Конец")) {
                endPhoneNumberMessage = new SendMessage();
                endPhoneNumberMessage.setChatId(chatId);
                endPhoneNumberMessage.setText("☑ Подтверждено.\n\nИдём дальше или хотите изменить номер телефона?");
                endPhoneNumberMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardPhoneButton());
                appealStatus = new AppealStatus();
                appealStatus.setStatus("Номер телефона подтвержден");
                String status = appealStatus.getStatus();
                STATUS_MAP.put(chatId, status);
            } else {
                endPhoneNumberMessage = sendButtonRequestErrorMsg(chatId);
            }

            if (userDataMap.get(chatId).getChatId() == chatId) {
                user.setChatId(userDataMap.get(chatId).getChatId());
                user.setFullName(userDataMap.get(chatId).getFullName());
                user.setPhoneNumber(receivedMessage);
                userDataMap.put(chatId, user);
            }
            user = new User();

        } else {
            if (!STATUS_MAP.get(chatId).equals("Конец")) {
                endPhoneNumberMessage = new SendMessage();
                endPhoneNumberMessage.setChatId(chatId);
                endPhoneNumberMessage.setText(ERROR_TEXT);
                endPhoneNumberMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardPhoneErrorButton());
            } else {
                endPhoneNumberMessage = sendButtonRequestErrorMsg(chatId);
            }
        }
        try {
            bot.execute(endPhoneNumberMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendPhoneBadRequestErrorMsg(long chatId) {
        SendMessage sendErrorMessage = new SendMessage();
        sendErrorMessage.setChatId(chatId);
        sendErrorMessage.setText(PHONE_BAD_REQUEST_TEXT);
        sendErrorMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardPhoneButton());
        try {
            bot.execute(sendErrorMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void recordEmail(long chatId) {
        SendMessage sendEmailMessage;

        if (!STATUS_MAP.get(chatId).equals("Конец")) {
            sendEmailMessage = new SendMessage();
            sendEmailMessage.setChatId(chatId);
            sendEmailMessage.setText("Введите адрес электронной почты:");
            appealStatus = new AppealStatus();
            appealStatus.setStatus("Почта");
            String status = appealStatus.getStatus();
            STATUS_MAP.put(chatId, status);
        } else {
            sendEmailMessage = sendButtonRequestErrorMsg(chatId);
        }

        try {
            bot.execute(sendEmailMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void endRecordEmail(long chatId, String receivedMessage) {
        SendMessage endEmailMessage;

        if (receivedMessage.matches("^[A-Za-z0-9_.+%-]+@[A-Za-z0-9_.-]+\\.[a-zA-Z]{2,6}$")) {

            if (!STATUS_MAP.get(chatId).equals("Конец")) {
                endEmailMessage = new SendMessage();
                endEmailMessage.setChatId(chatId);
                endEmailMessage.setText("☑ Подтверждено.\n\nИдём дальше или хотите изменить адрес электронной почты?");
                endEmailMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardEmailButton());
                appealStatus = new AppealStatus();
                appealStatus.setStatus("Почта подтверждена");
                String status = appealStatus.getStatus();
                STATUS_MAP.put(chatId, status);
            } else {
                endEmailMessage = sendButtonRequestErrorMsg(chatId);
            }

            if (userDataMap.get(chatId).getChatId() == chatId) {
                user.setChatId(userDataMap.get(chatId).getChatId());
                user.setFullName(userDataMap.get(chatId).getFullName());
                user.setPhoneNumber(userDataMap.get(chatId).getPhoneNumber());
                user.setEmail(receivedMessage);
                userDataMap.put(chatId, user);
                mapDataForAppeal.put(chatId, user);
            }
            user = new User();
        } else {

            if (!STATUS_MAP.get(chatId).equals("Конец")) {
                endEmailMessage = new SendMessage();
                endEmailMessage.setChatId(chatId);
                endEmailMessage.setText(ERROR_TEXT);
                endEmailMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardEmailErrorButton());
            } else {
                endEmailMessage = sendButtonRequestErrorMsg(chatId);
            }
        }
        try {
            bot.execute(endEmailMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendEmailBadRequestErrorMsg(long chatId) {
        SendMessage sendErrorMessage = new SendMessage();
        sendErrorMessage.setChatId(chatId);
        sendErrorMessage.setText(EMAIL_BAD_REQUEST_TEXT);
        sendErrorMessage.setReplyMarkup(personalDataButtons.getInlineKeyboardEmailButton());
        try {
            bot.execute(sendErrorMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private SendMessage sendButtonRequestErrorMsg(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("⚠ Что-то пошло не так! Обращение по номеру\n"
                + "#" + appealDAO.getUserAppealNumber(TextAppealHandler.mapDataForFile.get(chatId).getId())
                + " вы уже завершили, ожидайте по нему обратной связи.\n\n"
                + "Для выхода к меню или создания новой заявки нажмите на соответствующую кнопку:");
        message.setReplyMarkup(sendingAppealFilesButtons.getInlineKeyboardOfferMenuOrNewAppeal());
        return message;
    }

    public void getRecordFullName(long chatId) {
        recordFullName(chatId);
    }

    public void getEndRecordFullName(long chatId, String receivedMessage) {
        endRecordFullName(chatId, receivedMessage);
    }

    public void getSendFIOBadRequestErrorMsg(long chatId) {
        sendFIOBadRequestErrorMsg(chatId);
    }

    public void getAskSkipOrRecordPhoneData(long chatId) {
        askForSkipOrRecordPhoneData(chatId);
    }

    public void getRecordPhoneNumber(long chatId) {
        recordPhoneNumber(chatId);
    }

    public void getEndRecordPhoneNumber(long chatId, String receivedMessage) {
        endRecordPhoneNumber(chatId, receivedMessage);
    }

    public void getSendPhoneBadRequestErrorMsg(long chatId) {
        sendPhoneBadRequestErrorMsg(chatId);
    }

    public void getRecordEmail(long chatId) {
        recordEmail(chatId);
    }

    public void getEndRecordEmail(long chatId, String receivedMessage) {
        endRecordEmail(chatId, receivedMessage);
    }

    public void getSendEmailBadRequestErrorMsg(long chatId) {
        sendEmailBadRequestErrorMsg(chatId);
    }
}
