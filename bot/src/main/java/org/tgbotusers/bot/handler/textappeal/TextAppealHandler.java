package org.tgbotusers.bot.handler.textappeal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.keyboard.SendingAppealFilesButtons;
import org.tgbotusers.bot.keyboard.TextAppealButtons;
import org.tgbotusers.bot.handler.status.AppealStatus;
import org.tgbotusers.bot.text.appeal.ErrorTextResponse;
import org.tgbotusers.bot.handler.status.model.entity.Appeal;
import org.tgbotusers.bot.handler.status.model.repository.AppealDAO;

import java.time.LocalDateTime;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class TextAppealHandler implements ErrorTextResponse {
    private final long appealNumber = (long) (Math.random() * 100_000_000);
    private AppealStatus appealStatus;
    private final TextAppealButtons textAppealButtons = new TextAppealButtons();
    private final SendingAppealFilesButtons sendingAppealFilesButtons = new SendingAppealFilesButtons();
    private Appeal appeal = new Appeal();

    public static HashMap<Long, Appeal> mapDataForFile = new HashMap<>();

    public static final HashMap<Long, Appeal> appealData = new HashMap<>();

    private final Bot bot;

    @Autowired
    private final AppealDAO appealDAO;

    private void recordAddress(long chatId) {
        SendMessage sendAddressMessage;

        if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
            sendAddressMessage = new SendMessage();
            sendAddressMessage.setChatId(chatId);
            sendAddressMessage.setText("Введите одним сообщением адрес (адреса)," +
                    " к которому относится ваше обращение:");
            appealStatus = new AppealStatus();
            appealStatus.setStatus("Адрес");
            String status = appealStatus.getStatus();
            PersonalDataHandler.STATUS_MAP.put(chatId, status);
        } else {
            sendAddressMessage = sendButtonRequestErrorMsg(chatId);
        }
        try {
            bot.execute(sendAddressMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void endRecordAddress(long chatId, String messageReceived) {
        SendMessage endAddressMessage;

        if (messageReceived.matches("^[а-яА-ЯёЁ0-9.\\-,]+( [а-яА-ЯёЁ0-9_,.)(;:\n-]+)*$")) {

            if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
                endAddressMessage = new SendMessage();
                endAddressMessage.setChatId(chatId);
                endAddressMessage.setText("☑ Подтверждено.\n\nИдём дальше или хотите изменить адрес?");
                endAddressMessage.setReplyMarkup(textAppealButtons.getInlineKeyboardAddressButton());
                appealStatus = new AppealStatus();
                appealStatus.setStatus("Адрес подтвержден");
                String status = appealStatus.getStatus();
                PersonalDataHandler.STATUS_MAP.put(chatId, status);
            } else {
                endAddressMessage = sendButtonRequestErrorMsg(chatId);
            }

            appeal.setAppealNumber(appealNumber);
            appeal.setAddress(messageReceived);
            if (!appealData.containsKey(chatId)) {
                appealData.put(chatId, appeal);
            } else if (PersonalDataHandler.mapDataForAppeal.get(chatId).getChatId() == chatId) {
                appeal.setAppealNumber(appealNumber);
                appeal.setAddress(messageReceived);
                appealData.put(chatId, appeal);
            }
            appeal = new Appeal();
        } else {
            if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
                endAddressMessage = new SendMessage();
                endAddressMessage.setChatId(chatId);
                endAddressMessage.setText(ERROR_TEXT);
                endAddressMessage.setReplyMarkup(textAppealButtons.getInlineKeyboardAddressErrorButton());
            } else {
                endAddressMessage = sendButtonRequestErrorMsg(chatId);
            }
        }
        try {
            bot.execute(endAddressMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendAddressBadRequestMsg(long chatId) {
        SendMessage sendErrorMessage = new SendMessage();
        sendErrorMessage.setChatId(chatId);
        sendErrorMessage.setText(ADDRESS_BAD_REQUEST_TEXT);
        sendErrorMessage.setReplyMarkup(textAppealButtons.getInlineKeyboardAddressButton());
        try {
            bot.execute(sendErrorMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void recordTextAppeal(long chatId) {
        SendMessage sendTextAppealMessage;

        if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
            sendTextAppealMessage = new SendMessage();
            sendTextAppealMessage.setChatId(chatId);
            sendTextAppealMessage.setText("Введите текстовое обращение (не более 1000 символов) " +
                    "и отправьте одним сообщением:");
            appealStatus = new AppealStatus();
            appealStatus.setStatus("Текст");
            String status = appealStatus.getStatus();
            PersonalDataHandler.STATUS_MAP.put(chatId, status);
        } else {
            sendTextAppealMessage = sendButtonRequestErrorMsg(chatId);
        }
        try {
            bot.execute(sendTextAppealMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void endRecordTextAppeal(long chatId, String messageReceived) {
        SendMessage endTextAppealMessage;

        if (messageReceived.length() <= 1000 && messageReceived.matches("[^A-Za-z]+")) {

            if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
                endTextAppealMessage = new SendMessage();
                endTextAppealMessage.setChatId(chatId);
                endTextAppealMessage.setText("☑ Подтверждено.\n\nИдём дальше или хотите изменить текст обращения?");
                endTextAppealMessage.setReplyMarkup(textAppealButtons.getInlineKeyboardAppealTextButton());
                appealStatus = new AppealStatus();
                appealStatus.setStatus("Текст подтвержден");
                String status = appealStatus.getStatus();
                PersonalDataHandler.STATUS_MAP.put(chatId, status);
            } else {
                endTextAppealMessage = sendButtonRequestErrorMsg(chatId);
            }

            if (PersonalDataHandler.mapDataForAppeal.get(chatId).getChatId() == chatId) {
                appeal.setAppealNumber(appealData.get(chatId).getAppealNumber());
                appeal.setAddress(appealData.get(chatId).getAddress());
                appeal.setAppealText(messageReceived);
                appeal.setStatusTime(LocalDateTime.now());
                appeal.setUserAppeal(PersonalDataHandler.mapDataForAppeal.get(chatId));
                appealData.put(chatId, appeal);
                mapDataForFile.put(chatId, appeal);
            }
            appeal = new Appeal();
        } else {
            if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
                endTextAppealMessage = new SendMessage();
                endTextAppealMessage.setChatId(chatId);
                endTextAppealMessage.setText(ERROR_TEXT);
                endTextAppealMessage.setReplyMarkup(textAppealButtons.getInlineKeyboardAppealTextErrorButton());
            } else {
                endTextAppealMessage = sendButtonRequestErrorMsg(chatId);
            }
        }

        try {
            bot.execute(endTextAppealMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendAppealTextBadRequestMsg(long chatId) {
        SendMessage sendErrorMessage = new SendMessage();
        sendErrorMessage.setChatId(chatId);
        sendErrorMessage.setText(TEXT_APPEAL_BAD_REQUEST_TEXT);
        sendErrorMessage.setReplyMarkup(textAppealButtons.getInlineKeyboardAppealTextButton());
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

    public void getRecordAddress(long chatId) {
        recordAddress(chatId);
    }

    public void getEndRecordAddress(long chatId, String messageReceived) {
        endRecordAddress(chatId, messageReceived);
    }

    public void getSendAddressBadRequestMsg(long chatId) {
        sendAddressBadRequestMsg(chatId);
    }

    public void getRecordTextAppeal(long chatId) {
        recordTextAppeal(chatId);
    }

    public void getEndRecordTextAppeal(long chatId, String messageReceived) {
        endRecordTextAppeal(chatId, messageReceived);
    }

    public void getSendAppealTextBadRequestMsg(long chatId) {
        sendAppealTextBadRequestMsg(chatId);
    }
}
