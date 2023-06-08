package org.tgbotusers.bot.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.handler.status.AppealStatus;
import org.tgbotusers.bot.keyboard.AppealConfirmButtons;
import org.tgbotusers.bot.text.BotTextResponse;

import java.io.File;
import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppealConfirmHandler implements BotTextResponse {
    private final Bot bot;
    private static final String MO_PATH = "C:\\Users\\HP\\Downloads\\dep_2014_figurin_195.jpg";
    private static final String MA_PATH = "C:\\Users\\HP\\Downloads\\ivanovd1.jpg";
    private static final String EVENT_ORG_PATH = "C:\\Users\\HP\\Downloads\\orgDepart.jpg";
    private static final String CUSTODY_PATH = "C:\\Users\\HP\\Downloads\\maksimova.jpg";
    private static final String IMPROVEMENT_PATH = "C:\\Users\\HP\\Downloads\\kontrakt_195.jpg";

    public static HashMap<Long, String> appealTypeMap = new HashMap<>();

    private void createAppealForm(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите тематику обращения:");
        message.setReplyMarkup(preparationButton.getInlineKeyboardAppealButtons());

        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void checkOnRightChoiceOfPickingMOReception(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONFIRM_MO_RECEPTION_THEME_CHECK_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardCorrectChoiceCheckForMOReceptionButtons());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendMOContacts(long chatId) {
        File file = new File(MO_PATH);
        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);

        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendPhoto message = new SendPhoto();
        message.setPhoto(inputFile);
        message.setChatId(chatId);
        message.setCaption(MO_CONTACTS_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardGoToCheckMarkConfirmButtons());

        appealTypeMap.put(chatId, "Обращение к главе МО");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void checkOnRightChoiceOfPickingMAReception(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONFIRM_MA_RECEPTION_THEME_CHECK_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardCorrectChoiceCheckForMAReceptionButtons());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendMAContacts(long chatId) {
        File file = new File(MA_PATH);
        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);

        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendPhoto message = new SendPhoto();
        message.setPhoto(inputFile);
        message.setChatId(chatId);
        message.setCaption(MA_CONTACTS_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardGoToCheckMarkConfirmButtons());

        appealTypeMap.put(chatId, "Обращение к главе МА");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void checkOnRightChoiceOfPickingEventOrg(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONFIRM_EVENT_ORG_RECEPTION_THEME_CHECK_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardCorrectChoiceCheckForEventOrgButtons());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendEventOrgContacts(long chatId) {
        File file = new File(EVENT_ORG_PATH);
        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);

        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendPhoto message = new SendPhoto();
        message.setPhoto(inputFile);
        message.setChatId(chatId);
        message.setCaption(EVENT_ORG_CONTACTS_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardGoToCheckMarkConfirmButtons());

        appealTypeMap.put(chatId, "Обращение в организационный отдел");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void checkOnRightChoiceOfPickingCustody(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONFIRM_CUSTODY_THEME_CHECK_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardCorrectChoiceCheckForCustodyButtons());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendCustodyContacts(long chatId) {
        File file = new File(CUSTODY_PATH);
        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);

        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendPhoto message = new SendPhoto();
        message.setPhoto(inputFile);
        message.setChatId(chatId);
        message.setCaption(CUSTODY_CONTACTS_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardGoToCheckMarkConfirmButtons());

        appealTypeMap.put(chatId, "Обращение в отдел опеки и попечительства");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void checkOnRightChoiceOfPickingImprovement(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CONFIRM_IMPROVEMENT_THEME_CHECK_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardCorrectChoiceCheckForImprovementButtons());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendImprovementContacts(long chatId) {
        File file = new File(IMPROVEMENT_PATH);

        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);

        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendPhoto message = new SendPhoto();
        message.setPhoto(inputFile);
        message.setChatId(chatId);
        message.setCaption(IMPROVEMENT_CONTACTS_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardGoToCheckMarkConfirmButtons());

        appealTypeMap.put(chatId, "Обращение в отдел благоустройства");
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendInformationBeforeAppeal(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Перед тем, как направлять обращение, " +
                "ознакомьтесь со следующей информацией:\n" +
                BEFORE_APPEAL_INFORMATION_TEXT);
        message.setReplyMarkup(preparationButton.getInlineKeyboardCheckMarkButton());
        AppealStatus appealStatus = new AppealStatus();
        appealStatus.setStatus("Выбор темы обращения");
        String status = appealStatus.getStatus();
        PersonalDataHandler.STATUS_MAP.put(chatId, status);
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void startAppeal(long chatId) {
        AppealConfirmButtons preparationButton = new AppealConfirmButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Отлично! Тогда переходим к заполнению обращения?\n");
        message.setReplyMarkup(preparationButton.getInlineKeyboardStartFillingInAppealButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void getCreateAppealForm(long chatId) {
        createAppealForm(chatId);
    }

    public void getCheckOnRightChoiceOfPickingMOReception(long chatId) {
        checkOnRightChoiceOfPickingMOReception(chatId);
    }

    public void getCheckOnRightChoiceOfPickingMAReception(long chatId) {
        checkOnRightChoiceOfPickingMAReception(chatId);
    }

    public void getCheckOnRightChoiceOfPickingEventOrg(long chatId) {
        checkOnRightChoiceOfPickingEventOrg(chatId);
    }

    public void getCheckOnRightChoiceOfPickingCustody(long chatId) {
        checkOnRightChoiceOfPickingCustody(chatId);
    }

    public void getCheckOnRightChoiceOfPickingImprovement(long chatId) {
        checkOnRightChoiceOfPickingImprovement(chatId);
    }

    public void getSendInformationBeforeAppeal(long chatId) {
        sendInformationBeforeAppeal(chatId);
    }

    public void getStartAppeal(long chatId) {
        startAppeal(chatId);
    }

    public void getSendMOContacts(long chatId) {
        sendMOContacts(chatId);
    }

    public void getSendMAContacts(long chatId) {
        sendMAContacts(chatId);
    }

    public void getSendEventOrgContacts(long chatId) {
        sendEventOrgContacts(chatId);
    }

    public void getSendCustodyContacts(long chatId) {
        sendCustodyContacts(chatId);
    }

    public void getSendImprovementContacts(long chatId) {
        sendImprovementContacts(chatId);
    }
}
