package org.tgbotusers.bot.handler.files;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.handler.AppealConfirmHandler;
import org.tgbotusers.bot.handler.status.model.entity.*;
import org.tgbotusers.bot.handler.status.model.repository.*;
import org.tgbotusers.bot.handler.textappeal.TextAppealHandler;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.keyboard.SendingAppealFilesButtons;
import org.tgbotusers.bot.handler.status.AppealStatus;
import org.tgbotusers.bot.text.otherfiletext.OtherTextResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class OtherFileMsgSender implements OtherTextResponse {
    private static final Set<File> uniqueFiles = new HashSet<>();
    private static final HashMap<Long, User> recheckUserDataMap = new HashMap<>();
    private static final HashMap<Long, Appeal> finalCheckAppealMap = new HashMap<>();
    private final Bot bot;

    @Autowired
    private final UserDAO userDAO;

    @Autowired
    private final AppealDAO appealDAO;

    @Autowired
    private final FileDAO fileDAO;

    private final SendingAppealFilesButtons sendingAppealFilesButtons = new SendingAppealFilesButtons();

    private void askForRecordFileOrEndSession(long chatId) {
        SendMessage sendAddressMessage;

        if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
            sendAddressMessage = new SendMessage();
            sendAddressMessage.setChatId(chatId);
            sendAddressMessage.setText(ATTACH_FILE_TEXT);
            sendAddressMessage.setReplyMarkup(sendingAppealFilesButtons.getInlineKeyboardAttachFileOrEndSessionButton());
        } else {
            sendAddressMessage = sendButtonRequestErrorMsg(chatId);
        }

        try {
            bot.execute(sendAddressMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void checkOnEmptyFileMap(long chatId) {
        FileHandler.fileLinksMap.get(chatId).clear();
        FileHandler.filesMap.get(chatId).clear();
    }

    private void endSessionAppeal(long chatId) {
        if (!PersonalDataHandler.userDataMap.get(chatId).getEmail().isEmpty() && chatId
                == PersonalDataHandler.userDataMap.get(chatId).getChatId()) {
            HashMap<Long, User> newMap = new HashMap<>();
            newMap.put(chatId, PersonalDataHandler.userDataMap.get(chatId));
            savePersonalUserData(newMap);
        } else {
            log.error(new NullPointerException().getMessage());
        }
        if (TextAppealHandler.appealData.get(chatId).getAppealNumber() != 0 && chatId ==
                TextAppealHandler.appealData.get(chatId).getUserAppeal().getChatId()) {
            HashMap<Long, Appeal> newAppealMap = new HashMap<>();
            newAppealMap.put(chatId, TextAppealHandler.appealData.get(chatId));
            saveAppealData(newAppealMap);
        } else {
            log.error(new NullPointerException().getMessage());
        }

        if (FileHandler.filesMap.containsKey(chatId)) {
            HashMap<Long, Set<File>> newFileMap = new HashMap<>();
            newFileMap.put(chatId, FileHandler.filesMap.get(chatId));
            saveFileData(newFileMap);
        } else {
            log.error(new NullPointerException().getMessage());
        }


        String textResponse = "Тема вопроса:\n" + AppealConfirmHandler.appealTypeMap.get(chatId) + "\n\n" +
                "Ваше обращение принято, номер вашего обращения:\n"
                + "#" + appealDAO.getUserAppealNumber(TextAppealHandler.mapDataForFile.get(chatId).getId())
                + "\n\n" + END_APPEAL_TEXT;
        SendMessage message;
        if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
            message = new SendMessage();
            message.setChatId(chatId);
            message.setText(textResponse);
            message.setReplyMarkup(sendingAppealFilesButtons.getInlineKeyboardEndSession());
            AppealStatus appealStatus = new AppealStatus();
            appealStatus.setStatus("Конец");
            String status = appealStatus.getStatus();
            PersonalDataHandler.STATUS_MAP.put(chatId, status);
            try {
                if (FileHandler.fileLinksMap.get(chatId).size() > 0) {
                    checkOnEmptyFileMap(chatId);
                }
            } catch(NullPointerException e) {
                log.warn(e.getMessage());
            }
        } else {
            message = sendButtonRequestErrorMsg(chatId);
        }
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void setFinalAppealStatus(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(INCORRECT_REQUEST_ERROR);
        try {
            bot.execute(message);
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

    private void savePersonalUserData(HashMap<Long, User> newMap) {
        for (Map.Entry<Long, User> entry : newMap.entrySet()) {
            int incrementId;
            if (!recheckUserDataMap.containsKey(entry.getKey())) {
                if (userDAO.checkUserChatId(entry.getKey()) == 0) {
                    incrementId = 1 + userDAO.getLastUserId();
                } else {
                    incrementId = userDAO.getUserId(entry.getKey());
                }
                entry.getValue().setId(incrementId);
                recheckUserDataMap.put(entry.getKey(), entry.getValue());
            } else if (recheckUserDataMap.get(entry.getKey()).getChatId().equals(entry.getValue().getChatId())) {
                incrementId = userDAO.getUserId(entry.getKey());
                entry.getValue().setId(incrementId);
                recheckUserDataMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<Long, User> entryDBElements : recheckUserDataMap.entrySet()) {
            if (userDAO.checkUserChatId(entryDBElements.getKey()) == 0) {
                userDAO.save(entryDBElements.getValue());
            } else if (userDAO.checkUserChatId(entryDBElements.getKey()) > 0) {
                userDAO.updateData(entryDBElements.getValue().getId(), entryDBElements.getValue().getEmail(),
                        entryDBElements.getValue().getFullName(), entryDBElements.getValue().getPhoneNumber());
            }
        }
    }

    private void saveAppealData(HashMap<Long, Appeal> newAppealMap) {
        for (Map.Entry<Long, Appeal> entry : newAppealMap.entrySet()) {
            int incrementId;
            if (!finalCheckAppealMap.containsKey(entry.getKey()) ||
                    finalCheckAppealMap.get(entry.getKey()).getAppealNumber() != entry.getValue().getAppealNumber()) {
                if (appealDAO.checkAppealUniqueNumber(entry.getValue().getAppealNumber()) == 0) {
                    incrementId = 1 + appealDAO.getLastAppealId();
                } else {
                    incrementId = appealDAO.getAppealId(entry.getValue().getAppealNumber());
                }
                entry.getValue().setStatusTime(LocalDateTime.now());
                entry.getValue().setAppealType(AppealConfirmHandler.appealTypeMap.get(entry.getKey()));
                entry.getValue().setId(incrementId);
                finalCheckAppealMap.put(entry.getKey(), entry.getValue());
            } else if (finalCheckAppealMap.get(entry.getKey()).getUserAppeal().getChatId().equals(entry.getKey())) {
                incrementId = appealDAO.getAppealId(entry.getValue().getAppealNumber());
                entry.getValue().setId(incrementId);
                entry.getValue().setStatusTime(LocalDateTime.now());
                entry.getValue().setAppealType(AppealConfirmHandler.appealTypeMap.get(entry.getKey()));
                finalCheckAppealMap.put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<Long, Appeal> entry : finalCheckAppealMap.entrySet()) {
            if (appealDAO.checkAppealUniqueNumber(entry.getValue().getAppealNumber()) == 0) {
                appealDAO.save(entry.getValue());
            } else if (appealDAO.checkAppealUniqueNumber(entry.getValue().getAppealNumber()) > 0) {
                appealDAO.updateAppealData(entry.getValue().getId(), entry.getValue().getAppealText(),
                        entry.getValue().getAddress());
            }
        }
    }

    private void saveFileData(HashMap<Long, Set<File>> newFileMap) {
        for (Map.Entry<Long, Set<File>> entry : newFileMap.entrySet()) {
            uniqueFiles.addAll(entry.getValue());
        }

        for (File file : uniqueFiles) {
            file.setStatusTime(LocalDateTime.now());
            fileDAO.save(file);
        }
    }

    public void getAskForRecordImageOrDocument(long chatId) {
        askForRecordFileOrEndSession(chatId);
    }

    public void getEndSessionAppeal(long chatId) {
        endSessionAppeal(chatId);
    }

    public void getSetFinalAppealStatus(long chatId) {
        setFinalAppealStatus(chatId);
    }
}
