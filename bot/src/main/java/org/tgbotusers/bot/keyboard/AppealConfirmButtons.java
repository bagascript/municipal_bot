package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class AppealConfirmButtons
{
    private final InlineKeyboardButton HEAD_MO_RECEPTION_BUTTON
            = new InlineKeyboardButton("Личный приём главы МО");
    private final InlineKeyboardButton HEAD_MA_RECEPTION_BUTTON
            = new InlineKeyboardButton("Личный приём главы МА");
    private final InlineKeyboardButton EVENT_ORGANIZATION_BUTTON
            = new InlineKeyboardButton("Организация мероприятий");
    private final InlineKeyboardButton CUSTODY_BUTTON
            = new InlineKeyboardButton("Опека и попечительство");
    private final InlineKeyboardButton IMPROVEMENT_BUTTON
            = new InlineKeyboardButton("Благоустройство");

    private final InlineKeyboardButton REFUSE_BUTTON
            = new InlineKeyboardButton("Назад");
    private final InlineKeyboardButton ACCEPT_BUTTON
            = new InlineKeyboardButton("Да");

    private final InlineKeyboardButton MO_CONTACTS_BUTTON
            = new InlineKeyboardButton("Да");
    private final InlineKeyboardButton MO_CHECK_CONFIRM_BUTTON
            = new InlineKeyboardButton("Отправить обращение");

    private final InlineKeyboardButton MA_CONTACTS_BUTTON
            = new InlineKeyboardButton("Да");

    private final InlineKeyboardButton EVENT_ORG_CONTACTS_BUTTON
            = new InlineKeyboardButton("Да");

    private final InlineKeyboardButton CUSTODY_CONTACTS_BUTTON
            = new InlineKeyboardButton("Да");

    private final InlineKeyboardButton CHECK_MARK
            = new InlineKeyboardButton("✅ (Ознакомились)");

    private final InlineKeyboardButton MENU_BUTTON
            = new InlineKeyboardButton("Назад к меню");
    private final InlineKeyboardButton START_BUTTON
            = new InlineKeyboardButton("Приступим");

    private InlineKeyboardMarkup inlineKeyboardCorrectChoiceCheckForMOReceptionButtons()
    {
        MO_CONTACTS_BUTTON.setCallbackData("/sendMOReceptionContacts");
        REFUSE_BUTTON.setCallbackData("/apply");

        List<InlineKeyboardButton> rowLine = List.of(REFUSE_BUTTON, MO_CONTACTS_BUTTON);
        List<List<InlineKeyboardButton>> listRowLineCheckButtons = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardCorrectChoiceCheckForMAReceptionButtons()
    {
        MA_CONTACTS_BUTTON.setCallbackData("/sendMAReceptionContacts");
        REFUSE_BUTTON.setCallbackData("/apply");

        List<InlineKeyboardButton> rowLine = List.of(REFUSE_BUTTON, MA_CONTACTS_BUTTON);
        List<List<InlineKeyboardButton>> listRowLineCheckButtons = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardCorrectChoiceCheckForEventOrgButtons()
    {
        EVENT_ORG_CONTACTS_BUTTON.setCallbackData("/sendEventOrgContacts");
        REFUSE_BUTTON.setCallbackData("/apply");

        List<InlineKeyboardButton> rowLine = List.of(REFUSE_BUTTON, EVENT_ORG_CONTACTS_BUTTON);
        List<List<InlineKeyboardButton>> listRowLineCheckButtons = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardCorrectChoiceCheckForCustodyButtons()
    {
        CUSTODY_CONTACTS_BUTTON.setCallbackData("/sendCustodyContacts");
        REFUSE_BUTTON.setCallbackData("/apply");

        List<InlineKeyboardButton> rowLine = List.of(REFUSE_BUTTON, CUSTODY_CONTACTS_BUTTON);
        List<List<InlineKeyboardButton>> listRowLineCheckButtons = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardCorrectChoiceCheckForImprovementButtons()
    {
        MO_CONTACTS_BUTTON.setCallbackData("/sendImprovementContacts");
        REFUSE_BUTTON.setCallbackData("/apply");

        List<InlineKeyboardButton> rowLine = List.of(REFUSE_BUTTON, MO_CONTACTS_BUTTON);
        List<List<InlineKeyboardButton>> listRowLineCheckButtons = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardGoToCheckMarkConfirmButtons()
    {
        MO_CHECK_CONFIRM_BUTTON.setCallbackData("/improvement");
        MENU_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowLine1 = List.of(MO_CHECK_CONFIRM_BUTTON);
        List<InlineKeyboardButton> rowLine2 = List.of(MENU_BUTTON);
        List<List<InlineKeyboardButton>> listRowLineCheckButtons = List.of(rowLine1, rowLine2);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardAppealButtons()
    {
        HEAD_MO_RECEPTION_BUTTON.setCallbackData("/headMoReceptionCheckConfirm");
        HEAD_MA_RECEPTION_BUTTON.setCallbackData("/headMaReceptionCheckConfirm");
        EVENT_ORGANIZATION_BUTTON.setCallbackData("/eventOrganizationCheckConfirm");
        CUSTODY_BUTTON.setCallbackData("/custodyCheckConfirm");
        IMPROVEMENT_BUTTON.setCallbackData("/improvementCheckConfirm");

        List<InlineKeyboardButton> firstRowLine = List.of(HEAD_MO_RECEPTION_BUTTON);
        List<InlineKeyboardButton> secondRowLine = List.of(HEAD_MA_RECEPTION_BUTTON);
        List<InlineKeyboardButton> thirdRowLine = List.of(EVENT_ORGANIZATION_BUTTON);
        List<InlineKeyboardButton> fourthRowLine = List.of(CUSTODY_BUTTON);
        List<InlineKeyboardButton> fifthRowLine = List.of(IMPROVEMENT_BUTTON);

        List<List<InlineKeyboardButton>> listRowLineAppealButtons = List.of(firstRowLine, secondRowLine,
                thirdRowLine, fourthRowLine, fifthRowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineAppealButtons);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardCheckMarkButton()
    {
        CHECK_MARK.setCallbackData("/data_entry");

        List<InlineKeyboardButton> rowLine = List.of(CHECK_MARK);

        List<List<InlineKeyboardButton>> listRowLineCheckmarkButton = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckmarkButton);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardStartFillingInAppealButton()
    {
        MENU_BUTTON.setCallbackData("/help");
        START_BUTTON.setCallbackData("/recordFIO");

        List<InlineKeyboardButton> rowLine = List.of(MENU_BUTTON, START_BUTTON);

        List<List<InlineKeyboardButton>> listRowLineCheckmarkButton = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckmarkButton);
        return inlineKeyboardButtonsSection;
    }

    public InlineKeyboardMarkup getInlineKeyboardCorrectChoiceCheckForMOReceptionButtons(){
        return inlineKeyboardCorrectChoiceCheckForMOReceptionButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardCorrectChoiceCheckForMAReceptionButtons(){
        return inlineKeyboardCorrectChoiceCheckForMAReceptionButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardCorrectChoiceCheckForEventOrgButtons(){
        return inlineKeyboardCorrectChoiceCheckForEventOrgButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardCorrectChoiceCheckForCustodyButtons(){
        return inlineKeyboardCorrectChoiceCheckForCustodyButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardCorrectChoiceCheckForImprovementButtons() {
        return inlineKeyboardCorrectChoiceCheckForImprovementButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardGoToCheckMarkConfirmButtons() {
        return inlineKeyboardGoToCheckMarkConfirmButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardAppealButtons(){
        return inlineKeyboardAppealButtons();
    }

    public InlineKeyboardMarkup getInlineKeyboardCheckMarkButton(){
        return inlineKeyboardCheckMarkButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardStartFillingInAppealButton(){
        return inlineKeyboardStartFillingInAppealButton();
    }
}
