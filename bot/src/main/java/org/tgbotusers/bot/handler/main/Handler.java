package org.tgbotusers.bot.handler.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.command.BotMenuCommands;
import org.tgbotusers.bot.handler.*;
import org.tgbotusers.bot.handler.files.FileHandler;
import org.tgbotusers.bot.handler.files.OtherFileMsgSender;
import org.tgbotusers.bot.handler.status.model.repository.*;
import org.tgbotusers.bot.handler.textappeal.TextAppealHandler;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.handler.status.model.config.BotConfig;

import java.util.List;

@Slf4j
@Component
public class Handler implements BotMenuCommands
{
    private final Bot bot;

    private final BotConfig config;

    @Autowired
    private final UserDAO userDAO;
    @Autowired
    private final AppealDAO appealDAO;

    @Autowired
    private final FileDAO fileDAO;

    public Handler(BotConfig config, UserDAO userDAO, AppealDAO appealDAO, FileDAO fileDAO){
        this.userDAO = userDAO;
        this.appealDAO = appealDAO;
        this.fileDAO = fileDAO;
        bot = new Bot(config);
        this.config = config;
        try {
            bot.execute(new SetMyCommands(LIST_OF_MENU_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e){
            log.error(e.getMessage());
        }
    }

    private void updateMessage(Update update){
        long chatId;
        String receivedMessage;
        String userName;
        PhotoSize photo;
        Document document;

        if (update.hasMessage()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();

            if (update.getMessage().hasText()) {
                receivedMessage = update.getMessage().getText();
                PhotoSize photoSizeForText = new PhotoSize();
                photoSizeForText.setFileId("noPhotoText");
                Document documentForText = new Document();
                documentForText.setFileId("noDocText");
                botAnswerUtils(chatId, receivedMessage, userName, photoSizeForText, documentForText);
            }

            if (update.getMessage().hasPhoto()) {
                Message message = update.getMessage();
                List<PhotoSize> photos = message.getPhoto();
                photo = photos.get(photos.size() - 1);
                String imageId = photo.getFileId();
                Document emptyDoc = new Document();
                emptyDoc.setFileId("emptyDoc");
                botAnswerUtils(chatId, imageId, null, photo, emptyDoc);
            }

            if(update.getMessage().hasDocument()) {
                Message message = update.getMessage();
                document = message.getDocument();
                String docId = document.getFileId();
                PhotoSize emptyPhoto = new PhotoSize();
                emptyPhoto.setFileId("emptyPhoto");
                botAnswerUtils(chatId, docId, null, emptyPhoto, document);
            }

        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            receivedMessage = update.getCallbackQuery().getData();
            userName = update.getCallbackQuery().getFrom().getFirstName();
            PhotoSize photoSizeForText = new PhotoSize();
            photoSizeForText.setFileId("noPhotoText");
            Document documentForText = new Document();
            documentForText.setFileId("noDocText");
            botAnswerUtils(chatId, receivedMessage, userName, photoSizeForText, documentForText);
        }
    }

    private void botAnswerUtils(long chatId, String receivedMessage, String userName, PhotoSize photo, Document document){
        StartAndHelpHandler startAndHelpHandler = new StartAndHelpHandler(bot);
        AppealConfirmHandler appealConfirmHandler = new AppealConfirmHandler(bot);
        PersonalDataHandler personalDataHandler = new PersonalDataHandler(bot, appealDAO);
        TextAppealHandler textAppealHandler = new TextAppealHandler(bot, appealDAO);
        OtherFileMsgSender otherFileMsgSender = new OtherFileMsgSender(bot, userDAO, appealDAO, fileDAO);
        FileHandler fileHandler = new FileHandler(bot, config, appealDAO);

        switch (receivedMessage) {
            case "/start" -> startAndHelpHandler.getStartBotCommand(chatId, userName);
            case "/help" -> startAndHelpHandler.getHelpBotCommand(chatId);
            case "/apply" -> appealConfirmHandler.getCreateAppealForm(chatId);
            case "/headMoReceptionCheckConfirm" -> appealConfirmHandler.getCheckOnRightChoiceOfPickingMOReception(chatId);
            case "/headMaReceptionCheckConfirm" -> appealConfirmHandler.getCheckOnRightChoiceOfPickingMAReception(chatId);
            case "/eventOrganizationCheckConfirm" -> appealConfirmHandler.getCheckOnRightChoiceOfPickingEventOrg(chatId);
            case "/custodyCheckConfirm" -> appealConfirmHandler.getCheckOnRightChoiceOfPickingCustody(chatId);
            case "/sendMOReceptionContacts" -> appealConfirmHandler.getSendMOContacts(chatId);
            case "/sendMAReceptionContacts" -> appealConfirmHandler.getSendMAContacts(chatId);
            case "/sendEventOrgContacts" -> appealConfirmHandler.getSendEventOrgContacts(chatId);
            case "/sendCustodyContacts" -> appealConfirmHandler.getSendCustodyContacts(chatId);
            case "/sendImprovementContacts" -> appealConfirmHandler.getSendImprovementContacts(chatId);
            case "/improvementCheckConfirm" -> appealConfirmHandler.getCheckOnRightChoiceOfPickingImprovement(chatId);
            case "/improvement" -> appealConfirmHandler.getSendInformationBeforeAppeal(chatId);
            case "/data_entry" -> appealConfirmHandler.getStartAppeal(chatId);
            case "/recordFIO" -> personalDataHandler.getRecordFullName(chatId);
            case "/skipOrRecordPhone" -> personalDataHandler.getAskSkipOrRecordPhoneData(chatId);
            case "/recordPhone" -> personalDataHandler.getRecordPhoneNumber(chatId);
            case "/recordEmail" -> personalDataHandler.getRecordEmail(chatId);
            case "/recordAddress" -> textAppealHandler.getRecordAddress(chatId);
            case "/recordTextAppeal" -> textAppealHandler.getRecordTextAppeal(chatId);
            case "/askDocOrImage" -> otherFileMsgSender.getAskForRecordImageOrDocument(chatId);
            case "/recordFile" -> fileHandler.getRecordFile(chatId);
            case "/end_appeal" -> otherFileMsgSender.getEndSessionAppeal(chatId);
            default -> new DefaultHandler(bot, config, userDAO, appealDAO, fileDAO).
                    getDefaultMessage(chatId, receivedMessage, photo, document);
        }
    }

    public void getUpdateMessage(Update update) {
        updateMessage(update);
    }
}
