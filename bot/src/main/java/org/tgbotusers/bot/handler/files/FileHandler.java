package org.tgbotusers.bot.handler.files;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.handler.status.AppealStatus;
import org.tgbotusers.bot.handler.status.model.config.BotConfig;
import org.tgbotusers.bot.handler.status.model.entity.Appeal;
import org.tgbotusers.bot.handler.status.model.entity.File;

import org.tgbotusers.bot.handler.status.model.repository.AppealDAO;
import org.tgbotusers.bot.handler.textappeal.TextAppealHandler;
import org.tgbotusers.bot.keyboard.SendingAppealFilesButtons;
import org.tgbotusers.bot.text.file.FileTextResponse;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Component
public class FileHandler implements FileTextResponse {
    private final SendingAppealFilesButtons sendingAppealFilesButtons = new SendingAppealFilesButtons();
    private static final String LINK_START = "https://api.telegram.org/file/bot";
    private final HashMap<Long, File> fileData = new HashMap<>();

    private static Set<String> filesList = new HashSet<>();
    private static Set<File> filesSet = new HashSet<>();

    private static File file = new File();

    public static HashMap<Long, Set<String>> fileLinksMap = new HashMap<>();
    public static HashMap<Long, Set<File>> filesMap = new HashMap<>();

    private final Bot bot;
    private final BotConfig config;

    @Autowired
    private final AppealDAO appealDAO;

    private void recordFile(long chatId) {
        SendMessage sendFileMessage;
        if (!PersonalDataHandler.STATUS_MAP.get(chatId).equals("Конец")) {
            sendFileMessage = new SendMessage();
            sendFileMessage.setChatId(chatId);
            sendFileMessage.setText("Отправьте файл (не более 5-х уникальных):");
            AppealStatus appealStatus = new AppealStatus();
            appealStatus.setStatus("Файл");
            String status = appealStatus.getStatus();
            PersonalDataHandler.STATUS_MAP.put(chatId, status);
        } else {
            sendFileMessage = sendButtonRequestErrorMsg(chatId);
        }
        try {
            bot.execute(sendFileMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void endRecordFile(long chatId, String receivedMsg, PhotoSize photo, Document document) {
        SendMessage sendFileMsg;
        boolean isImageEmpty = photo.getFileId().equals("emptyPhoto");
        boolean isDocumentEmpty = document.getFileId().equals("emptyDoc");

        if(isImageEmpty || isDocumentEmpty) {
            String fileLink = convertFileToLink(receivedMsg);
            putFileLinkInMap(chatId, fileLink);
            if (fileLinksMap.get(chatId).size() < 5) {
                setDataInFileEntityColumns(chatId, fileLink);
                sendFileMsg = checkFileLink(chatId,
                        sendingAppealFilesButtons.getInlineKeyboardAppealImageDocOrContinueButton());
                file = new File();
            } else if (fileLinksMap.get(chatId).size() == 5) {
                setDataInFileEntityColumns(chatId, fileLink);
                sendFileMsg = checkLastFileLink(chatId,
                        sendingAppealFilesButtons.getInlineKeyboardAppealAdditionImageOrContinueButton());
                file = new File();
            } else {
                sendFileMsg = sendMsgWithLimitError(chatId,
                        sendingAppealFilesButtons.getInlineKeyboardAppealAdditionImageOrContinueButton());
            }
        } else {
            sendFileMsg = sendMsgWithFormatError(chatId);
        }

        try {
            bot.execute(sendFileMsg);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private SendMessage checkFileLink(long chatId, ReplyKeyboard replyKeyboard) {
        SendMessage checkDocMessage = new SendMessage();
        checkDocMessage.setChatId(chatId);

        if (fileData.get(chatId).getLink().equals(file.getLink())) {
            if (!filesMap.containsKey(chatId)) {
                checkDocMessage.setText(FILE_SAVING_TEXT);
                checkDocMessage.setReplyMarkup(replyKeyboard);
                filesSet.add(file);
                filesMap.put(chatId, filesSet);
                filesSet = new HashSet<>();
            } else if (filesMap.get(chatId).contains(file)) {
                checkDocMessage.setText(DUPLICATE_FILE_ERROR_TEXT);
                checkDocMessage.setReplyMarkup(null);
            } else {
                filesMap.get(chatId).add(file);
                checkDocMessage.setText(FILE_SAVING_TEXT);
                checkDocMessage.setReplyMarkup(replyKeyboard);
            }

        } else {
            log.error(new Throwable().getMessage());
        }

        String resultMessage = checkDocMessage.getText();
        ReplyKeyboard resultReplyKeyboard = checkDocMessage.getReplyMarkup();
        checkDocMessage.setText(resultMessage);
        checkDocMessage.setReplyMarkup(resultReplyKeyboard);
        return checkDocMessage;
    }

    private SendMessage checkLastFileLink(long chatId, ReplyKeyboard replyKeyboard) {
        SendMessage checkDocMessage = new SendMessage();
        checkDocMessage.setChatId(chatId);

        if (fileData.get(chatId).getLink().equals(file.getLink())) {

            if (!filesMap.get(chatId).contains(file)) {
                checkDocMessage.setText(FILES_SAVING_TEXT);
                checkDocMessage.setReplyMarkup(replyKeyboard);
                filesMap.get(chatId).add(file);
            } else if (filesMap.get(chatId).contains(file)) {
                checkDocMessage.setText(FILE_LIMIT_ERROR_TEXT);
                checkDocMessage.setReplyMarkup(replyKeyboard);
            }

        } else {
            log.error(new Throwable().getMessage());
        }

        String resultMessage = checkDocMessage.getText();
        ReplyKeyboard resultReplyKeyboard = checkDocMessage.getReplyMarkup();
        checkDocMessage.setText(resultMessage);
        checkDocMessage.setReplyMarkup(resultReplyKeyboard);
        return checkDocMessage;
    }

    private void setDataInFileEntityColumns(long chatId, String fileLink) {
        file.setAppeal(TextAppealHandler.mapDataForFile.get(chatId));
        file.setLink(fileLink);
        fileData.put(chatId, file);
    }

    private String convertFileToLink(String receivedMessage) {
        GetFile file = new GetFile();
        file.setFileId(receivedMessage);
        String filePath;
        try {
            filePath = bot.execute(file).getFilePath();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        return LINK_START + config.getToken() + "/" + filePath;
    }

    private void putFileLinkInMap(long chatId, String fileLink) {
        HashMap<Long, Appeal> mapDocData = TextAppealHandler.mapDataForFile;
        boolean isChatIdTheSame = mapDocData.get(chatId).getUserAppeal().getChatId() == chatId;
        if (isChatIdTheSame) {
            if (!fileLinksMap.containsKey(chatId)) {
                filesList.add(fileLink);
                fileLinksMap.put(chatId, filesList);
                filesList = new HashSet<>();

            } else fileLinksMap.get(chatId).add(fileLink);
        } else {
            log.error(new NullPointerException().getMessage());
        }
    }

    private SendMessage sendMsgWithLimitError(long chatId, ReplyKeyboard replyKeyboard) {
        SendMessage checkFileMessage = new SendMessage();
        checkFileMessage.setChatId(chatId);
        checkFileMessage.setText(FILE_LIMIT_ERROR_TEXT);
        checkFileMessage.setReplyMarkup(replyKeyboard);
        return checkFileMessage;
    }

    private SendMessage sendMsgWithFormatError(long chatId) {
        SendMessage checkDocMessage = new SendMessage();
        checkDocMessage.setChatId(chatId);
        checkDocMessage.setText(FILE_FORMAT_TYPE_ERROR_TEXT);
        checkDocMessage.setReplyMarkup(null);
        return checkDocMessage;
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

    public void getRecordFile(long chatId) {
        recordFile(chatId);
    }

    public void getEndRecordFile(long chatId, String receiveMsg, PhotoSize photo, Document document) {
        endRecordFile(chatId, receiveMsg, photo, document);
    }
}
