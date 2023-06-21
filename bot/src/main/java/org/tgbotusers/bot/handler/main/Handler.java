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
import org.tgbotusers.bot.handler.news.*;
import org.tgbotusers.bot.handler.status.model.repository.*;
import org.tgbotusers.bot.handler.textappeal.TextAppealHandler;
import org.tgbotusers.bot.handler.personaldata.PersonalDataHandler;
import org.tgbotusers.bot.handler.status.model.config.BotConfig;
import org.tgbotusers.bot.handler.usefulinfo.UsefulInfoHandler;

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
        UsefulInfoHandler usefulInfoHandler = new UsefulInfoHandler(bot);
        AppealConfirmHandler appealConfirmHandler = new AppealConfirmHandler(bot);
        NewsHandler newsHandler = new NewsHandler(bot);
        PersonalDataHandler personalDataHandler = new PersonalDataHandler(bot, appealDAO);
        TextAppealHandler textAppealHandler = new TextAppealHandler(bot, appealDAO);
        OtherFileMsgSender otherFileMsgSender = new OtherFileMsgSender(bot, userDAO, appealDAO, fileDAO);
        FileHandler fileHandler = new FileHandler(bot, config, appealDAO);
        Blago blago = new Blago(bot);
        Opeka opeka = new Opeka(bot);
        Sobytiya sobytiya = new Sobytiya(bot);
        Actualno actualno = new Actualno(bot);

        switch (receivedMessage) {
            case "/start" -> startAndHelpHandler.getStartBotCommand(chatId, userName);
            case "/help" -> startAndHelpHandler.getHelpBotCommand(chatId);
            case "/schedule" -> startAndHelpHandler.getSendBotSchedule(chatId);
            case "/utility" -> usefulInfoHandler.getSendUsefulInfoMenu(chatId);
            case "/custodyForUsefulInfo" -> usefulInfoHandler.getSendCustodySections(chatId);
            case "/provisionServiceForCustody" -> usefulInfoHandler.getSendServiceRegulations(chatId);
            case "/proceedingsForCustody" -> usefulInfoHandler.getDocPreparation(chatId);
            case "/custodyDocs" -> usefulInfoHandler.getCustodyDocs(chatId);
            case "/adoptionDocs" -> usefulInfoHandler.getAdoptionDocs(chatId);
            case "/guardianshipForCustody" -> usefulInfoHandler.getGuardianshipCustody(chatId);
            case "/improvementForUsefulInfo" ->usefulInfoHandler.getSendImprovementSections(chatId);
            case "/subsidies" -> usefulInfoHandler.getSendSubsidies(chatId);
            case "/landImprovement" -> usefulInfoHandler.getSendLandImprovement(chatId);
            case "/eventForUsefulInfo" -> usefulInfoHandler.getSendEvent(chatId);
            case "/budgetForUsefulInfo" -> usefulInfoHandler.getSendBudget(chatId);
            case "/employmentForUsefulInfo" -> usefulInfoHandler.getSendEmployment(chatId);
            case "/phoneForUsefulInfo" -> usefulInfoHandler.getSendUsefulPhoneSections(chatId);
            case "/medFac" -> usefulInfoHandler.getSendMedPhones(chatId);
            case "/vetFac" -> usefulInfoHandler.getSendVetPhones(chatId);
            case "/internalAff" -> usefulInfoHandler.getSendInternalAffPhones(chatId);
            case "/housing" -> usefulInfoHandler.getSendHousingPhones(chatId);
            case "/kindergartenAndSchool" -> usefulInfoHandler.getSendKindergartenAndSchoolPhones(chatId);
            case "/importantLinksForUsefulInfo" -> usefulInfoHandler.getSendImportantLinks(chatId);
            case "/localQs" -> usefulInfoHandler.getSendLocalQuestions(chatId);
            case "/news" -> newsHandler.getSendSelectionNewsMenu(chatId);
            case "/impNews" -> blago.getSendSelectionBlagoNewsMenu(chatId);
            case "/blagoNews1" -> blago.sendBlagoNews1(chatId);
            case "/blagoNews2" -> blago.sendBlagoNews2(chatId);
            case "/blagoNews3" -> blago.sendBlagoNews3(chatId);
            case "/blagoNews4" -> blago.sendBlagoNews4(chatId);
            case "/blagoNews5" -> blago.sendBlagoNews5(chatId);
            case "/cusNews" -> opeka.getSendSelectionOpekaNewsMenu(chatId);
            case "/opekaNews1" -> opeka.sendOpekaNews1(chatId);
            case "/opekaNews2" -> opeka.sendOpekaNews2(chatId);
            case "/opekaNews3" -> opeka.sendOpekaNews3(chatId);
            case "/opekaNews4" -> opeka.sendOpekaNews4(chatId);
            case "/opekaNews5" -> opeka.sendOpekaNews5(chatId);
            case "/evNews" -> sobytiya.getSendSelectionSobytiyaNewsMenu(chatId);
            case "/sobytiyaNews1" -> sobytiya.sendSobytiyaNews1(chatId);
            case "/sobytiyaNews2" -> sobytiya.sendSobytiyaNews2(chatId);
            case "/sobytiyaNews3" -> sobytiya.sendSobytiyaNews3(chatId);
            case "/sobytiyaNews4" -> sobytiya.sendSobytiyaNews4(chatId);
            case "/sobytiyaNews5" -> sobytiya.sendSobytiyaNews5(chatId);
            case "/latNews" -> actualno.getSendSelectionActualnoNewsMenu(chatId);
            case "/actualnoNews1" -> actualno.sendActualnoNews1(chatId);
            case "/actualnoNews2" -> actualno.sendActualnoNews2(chatId);
            case "/actualnoNews3" -> actualno.sendActualnoNews3(chatId);
            case "/actualnoNews4" -> actualno.sendActualnoNews4(chatId);
            case "/actualnoNews5" -> actualno.sendActualnoNews5(chatId);
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
