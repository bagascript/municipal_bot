package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class UsefulInfoButtons
{
    private static final InlineKeyboardButton PREVIOUS_BUTTON = new InlineKeyboardButton("Назад");

    private final InlineKeyboardButton BUDGET_BUTTON = new InlineKeyboardButton("Бюджет округа");
    private final InlineKeyboardButton CUSTODY_BUTTON = new InlineKeyboardButton("Опека и попечительство");
    private final InlineKeyboardButton IMPROVEMENT_BUTTON = new InlineKeyboardButton("Благоустройство");
    private final InlineKeyboardButton EVENT_BUTTON = new InlineKeyboardButton("Мероприятия для жителей");
    private final InlineKeyboardButton EMPLOYMENT_BUTTON = new InlineKeyboardButton("Временное трудоустройство");
    private final InlineKeyboardButton PHONE_BUTTON = new InlineKeyboardButton("Полезные телефоны");
    private final InlineKeyboardButton IMPORTANT_LINKS_BUTTON = new InlineKeyboardButton("Важные ссылки");
    private final InlineKeyboardButton LOCAL_QUESTIONS_BUTTON = new InlineKeyboardButton("Вопросы местного значения");

    private final InlineKeyboardButton PROVISION_SERVICE_BUTTON = new InlineKeyboardButton("Регламент услуг");
    private final InlineKeyboardButton CUSTODY_PROCEEDINGS_BUTTON = new InlineKeyboardButton("Оформление документов");
    private final InlineKeyboardButton GUARDIANSHIP_BUTTON = new InlineKeyboardButton("Опека недееспособных людей");

    private final InlineKeyboardButton CUSTODY_DOCS_BUTTON = new InlineKeyboardButton("Опека и попечительство");
    private final InlineKeyboardButton ADOPTION_DOCS_BUTTON = new InlineKeyboardButton("Усыновление");

    private final InlineKeyboardButton SUBSIDIES_BUTTON = new InlineKeyboardButton("Субсидии");
    private final InlineKeyboardButton LAND_IMPROVEMENT_BUTTON = new InlineKeyboardButton("Благоустройство территорий");

    private final InlineKeyboardButton MEDICAL_FACILITIES_BUTTON = new InlineKeyboardButton("Медицинские учреждения");
    private final InlineKeyboardButton VET_FACILITIES_BUTTON = new InlineKeyboardButton("Ветеринарные учреждения");
    private final InlineKeyboardButton INTERNAL_AFFAIRS_BUTTON = new InlineKeyboardButton("Органы внутренних дел");
    private final InlineKeyboardButton HOUSING_BUTTON = new InlineKeyboardButton("Жилищно-коммунальное хозяйство");
    private final InlineKeyboardButton KINDERGARTEN_AND_SCHOOL_BUTTON = new InlineKeyboardButton("Детские сады и школы");

    private InlineKeyboardMarkup inlineKeyboardMenuButton()
    {
        BUDGET_BUTTON.setCallbackData("/budgetForUsefulInfo");
        CUSTODY_BUTTON.setCallbackData("/custodyForUsefulInfo");
        IMPROVEMENT_BUTTON.setCallbackData("/improvementForUsefulInfo");
        EVENT_BUTTON.setCallbackData("/eventForUsefulInfo");
        EMPLOYMENT_BUTTON.setCallbackData("/employmentForUsefulInfo");
        PHONE_BUTTON.setCallbackData("/phoneForUsefulInfo");
        IMPORTANT_LINKS_BUTTON.setCallbackData("/importantLinksForUsefulInfo");
        LOCAL_QUESTIONS_BUTTON.setCallbackData("/localQs");
        PREVIOUS_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowInline1 = List.of(BUDGET_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(CUSTODY_BUTTON);
        List<InlineKeyboardButton> rowInline3 = List.of(IMPROVEMENT_BUTTON);
        List<InlineKeyboardButton> rowInline4 = List.of(EVENT_BUTTON);
        List<InlineKeyboardButton> rowInline5 = List.of(EMPLOYMENT_BUTTON);
        List<InlineKeyboardButton> rowInline6 = List.of(PHONE_BUTTON);
        List<InlineKeyboardButton> rowInline7 = List.of(IMPORTANT_LINKS_BUTTON);
        List<InlineKeyboardButton> rowInline8 = List.of(LOCAL_QUESTIONS_BUTTON);
        List<InlineKeyboardButton> rowInline9 = List.of(PREVIOUS_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2, rowInline3, rowInline4,
                rowInline5, rowInline6, rowInline7, rowInline8, rowInline9);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardCustodySectionsButton()
    {
        PROVISION_SERVICE_BUTTON.setCallbackData("/provisionServiceForCustody");
        CUSTODY_PROCEEDINGS_BUTTON.setCallbackData("/proceedingsForCustody");
        GUARDIANSHIP_BUTTON.setCallbackData("/guardianshipForCustody");
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline1 = List.of(PROVISION_SERVICE_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(CUSTODY_PROCEEDINGS_BUTTON);
        List<InlineKeyboardButton> rowInline3 = List.of(GUARDIANSHIP_BUTTON);
        List<InlineKeyboardButton> rowInline4 = List.of(PREVIOUS_BUTTON);


        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2, rowInline3, rowInline4);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardServiceRegulationsButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/custodyForUsefulInfo");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardDocPreparationButton()
    {
        CUSTODY_DOCS_BUTTON.setCallbackData("/custodyDocs");
        ADOPTION_DOCS_BUTTON.setCallbackData("/adoptionDocs");
        PREVIOUS_BUTTON.setCallbackData("/custodyForUsefulInfo");

        List<InlineKeyboardButton> rowInline1 = List.of(CUSTODY_DOCS_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(ADOPTION_DOCS_BUTTON);
        List<InlineKeyboardButton> rowInline3 = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2, rowInline3);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardCustodyAndAdoptionDocsButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/proceedingsForCustody");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardImprovementSectionsButton()
    {
        SUBSIDIES_BUTTON.setCallbackData("/subsidies");
        LAND_IMPROVEMENT_BUTTON.setCallbackData("/landImprovement");
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline1 = List.of(SUBSIDIES_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(LAND_IMPROVEMENT_BUTTON);
        List<InlineKeyboardButton> rowInline3 = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2, rowInline3);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardSubsidiesAndLandImprovementButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/improvementForUsefulInfo");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardEventButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardBudgetButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardEmploymentButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardUsefulPhoneSectionsButton()
    {
        MEDICAL_FACILITIES_BUTTON.setCallbackData("/medFac");
        VET_FACILITIES_BUTTON.setCallbackData("/vetFac");
        INTERNAL_AFFAIRS_BUTTON.setCallbackData("/internalAff");
        HOUSING_BUTTON.setCallbackData("/housing");
        KINDERGARTEN_AND_SCHOOL_BUTTON.setCallbackData("/kindergartenAndSchool");
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline1 = List.of(MEDICAL_FACILITIES_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(VET_FACILITIES_BUTTON);
        List<InlineKeyboardButton> rowInline3 = List.of(INTERNAL_AFFAIRS_BUTTON);
        List<InlineKeyboardButton> rowInline4 = List.of(HOUSING_BUTTON);
        List<InlineKeyboardButton> rowInline5 = List.of(KINDERGARTEN_AND_SCHOOL_BUTTON);
        List<InlineKeyboardButton> rowInline6 = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2,rowInline3, rowInline4, rowInline5, rowInline6);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardForPhonesButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/phoneForUsefulInfo");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardImportantLinksButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardLocalQuestionsButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/utility");

        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    public InlineKeyboardMarkup getInlineKeyboardMenuButton() {
        return inlineKeyboardMenuButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardServiceRegulationsButton() {
        return inlineKeyboardServiceRegulationsButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardCustodySectionsButton() {
        return inlineKeyboardCustodySectionsButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardDocPreparationButton() {
        return inlineKeyboardDocPreparationButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardCustodyAndAdoptionDocsButton() {
        return inlineKeyboardCustodyAndAdoptionDocsButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardImprovementSectionsButton() {
        return inlineKeyboardImprovementSectionsButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardSubsidiesAndLandImprovementButton() {
        return inlineKeyboardSubsidiesAndLandImprovementButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardEventButton() {
        return inlineKeyboardEventButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardBudgetButton() {
        return inlineKeyboardBudgetButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardEmploymentButton() {
        return inlineKeyboardEmploymentButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardUsefulPhoneSectionsButton() {
        return inlineKeyboardUsefulPhoneSectionsButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardForPhonesButton() {
        return inlineKeyboardForPhonesButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardImportantLinksButton() {
        return inlineKeyboardImportantLinksButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardLocalQuestionsButton() {
        return inlineKeyboardLocalQuestionsButton();
    }
}
