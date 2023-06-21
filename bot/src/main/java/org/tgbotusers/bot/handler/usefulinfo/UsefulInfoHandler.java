package org.tgbotusers.bot.handler.usefulinfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.keyboard.UsefulInfoButtons;
import org.tgbotusers.bot.text.usefulinfo.UsefulInfoTextResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsefulInfoHandler implements UsefulInfoTextResponse
{
    private final Bot bot;

    private void sendUsefulInfoMenu(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите тематику:");
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardMenuButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendCustodySections(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Какой подраздел опеки и попечительства вас интересует?");
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardCustodySectionsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendServiceRegulations(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(SERVICE_REGULATION_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardServiceRegulationsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendDocPreparation(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите тип документов по оформлению:");
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardDocPreparationButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendCustodyDocs(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(CUSTODY_DOC_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardCustodyAndAdoptionDocsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendAdoptionDocs(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(ADOPTION_DOC_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardCustodyAndAdoptionDocsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendGuardianshipCustody(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(GUARDIAN_SHIP_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardServiceRegulationsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendImprovementSections(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Какой раздел благоустройства вас интересует?");
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardImprovementSectionsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendSubsidies(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(SUBSIDIES_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardSubsidiesAndLandImprovementButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendLandImprovement(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(LAND_IMPROVEMENT_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardSubsidiesAndLandImprovementButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendEvent(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(EVENT_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardEventButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendBudget(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(BUDGET_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardBudgetButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendEmployment(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(EMPLOYMENT_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardEmploymentButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendUsefulPhoneSections(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите раздел телефонов:");
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardUsefulPhoneSectionsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendMedFacPhones(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(MEDICAL_FACILITIES_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardForPhonesButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendVetFacPhones(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(VETERINARY_FACILITIES_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardForPhonesButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendInternalAffPhones(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(INTERNAL_AUTHORITIES_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardForPhonesButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendHousingPhones(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(HOUSING_UTILITIES_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardForPhonesButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendKindergartenAndSchoolPhones(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(KINDERGARTEN_AND_SCHOOL_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardForPhonesButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendImportantLinks(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(IMPORTANT_LINKS_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardImportantLinksButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendLocalQuestions(long chatId) {
        UsefulInfoButtons usefulInfoButtons = new UsefulInfoButtons();
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(LOCAL_QUESTIONS_TEXT);
        message.setReplyMarkup(usefulInfoButtons.getInlineKeyboardLocalQuestionsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void getSendUsefulInfoMenu(long chatId) {
        sendUsefulInfoMenu(chatId);
    }

    public void getSendCustodySections(long chatId) {
        sendCustodySections(chatId);
    }

    public void getSendServiceRegulations(long chatId) {
        sendServiceRegulations(chatId);
    }

    public void getDocPreparation(long chatId) {
        sendDocPreparation(chatId);
    }

    public void getCustodyDocs(long chatId) {
        sendCustodyDocs(chatId);
    }

    public void getAdoptionDocs(long chatId) {
        sendAdoptionDocs(chatId);
    }

    public void getGuardianshipCustody(long chatId) {
        sendGuardianshipCustody(chatId);
    }

    public void getSendImprovementSections(long chatId) {
        sendImprovementSections(chatId);
    }

    public void getSendSubsidies(long chatId) {
        sendSubsidies(chatId);
    }

    public void getSendLandImprovement(long chatId) {
        sendLandImprovement(chatId);
    }

    public void getSendEvent(long chatId) {
        sendEvent(chatId);
    }

    public void getSendBudget(long chatId) {
        sendBudget(chatId);
    }

    public void getSendEmployment(long chatId) {
        sendEmployment(chatId);
    }

    public void getSendUsefulPhoneSections(long chatId) {
        sendUsefulPhoneSections(chatId);
    }

    public void getSendMedPhones(long chatId) {
        sendMedFacPhones(chatId);
    }

    public void getSendVetPhones(long chatId) {
        sendVetFacPhones(chatId);
    }

    public void getSendInternalAffPhones(long chatId) {
        sendInternalAffPhones(chatId);
    }

    public void getSendHousingPhones(long chatId) {
        sendHousingPhones(chatId);
    }

    public void getSendKindergartenAndSchoolPhones(long chatId) {
        sendKindergartenAndSchoolPhones(chatId);
    }

    public void getSendImportantLinks(long chatId) {
        sendImportantLinks(chatId);
    }

    public void getSendLocalQuestions(long chatId) {
        sendLocalQuestions(chatId);
    }
}
