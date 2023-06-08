package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class SendingAppealFilesButtons
{
    private static final InlineKeyboardButton ATTACH_FILE_BUTTON = new InlineKeyboardButton("Прикрепить файл");
    private static final InlineKeyboardButton END_APPEAL_BUTTON = new InlineKeyboardButton("Завершить обращение");
    private static final InlineKeyboardButton SEND_FILE_BUTTON = new InlineKeyboardButton("Отправить файл");

    private static final InlineKeyboardButton MENU_BUTTON = new InlineKeyboardButton("Назад к меню");
    private static final InlineKeyboardButton NEW_APPEAL_BUTTON = new InlineKeyboardButton("Новое обращение");

    private InlineKeyboardMarkup inlineKeyboardAttachFileOrEndSessionButton()
    {
        ATTACH_FILE_BUTTON.setCallbackData("/recordFile");
        END_APPEAL_BUTTON.setText("Отправить без файлов");
        END_APPEAL_BUTTON.setCallbackData("/end_appeal");

        List<InlineKeyboardButton> rowInline1 = List.of(ATTACH_FILE_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(END_APPEAL_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }


    private InlineKeyboardMarkup inlineKeyboardAppealImageDocOrContinueButton()
    {
        END_APPEAL_BUTTON.setText("Завершить обращение");
        END_APPEAL_BUTTON.setCallbackData("/end_appeal");
        SEND_FILE_BUTTON.setCallbackData("/recordFile");

        List<InlineKeyboardButton> rowLine2 = List.of(END_APPEAL_BUTTON);
        List<InlineKeyboardButton> rowLine3 = List.of(SEND_FILE_BUTTON);

        List<List<InlineKeyboardButton>> listRowLineCheckmarkButton = List.of(rowLine3, rowLine2);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckmarkButton);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardAppealAdditionImageOrContinueButton()
    {
        END_APPEAL_BUTTON.setCallbackData("/end_appeal");
        List<InlineKeyboardButton> rowLine1 = List.of(END_APPEAL_BUTTON);

        List<List<InlineKeyboardButton>> listRowLineCheckmarkButton = List.of(rowLine1);
        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckmarkButton);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardCloseApplicationButton()
    {
        MENU_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowLine = List.of(MENU_BUTTON);

        List<List<InlineKeyboardButton>> listRowLineCheckmarkButton = List.of(rowLine);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckmarkButton);
        return inlineKeyboardButtonsSection;
    }

    private InlineKeyboardMarkup inlineKeyboardOfferMenuOrNewAppealButton()
    {
        MENU_BUTTON.setCallbackData("/help");
        NEW_APPEAL_BUTTON.setCallbackData("/apply");

        List<InlineKeyboardButton> rowLine1 = List.of(MENU_BUTTON);
        List<InlineKeyboardButton> rowLine2 = List.of(NEW_APPEAL_BUTTON);

        List<List<InlineKeyboardButton>> listRowLineCheckmarkButton = List.of(rowLine1, rowLine2);

        InlineKeyboardMarkup inlineKeyboardButtonsSection = new InlineKeyboardMarkup();
        inlineKeyboardButtonsSection.setKeyboard(listRowLineCheckmarkButton);
        return inlineKeyboardButtonsSection;
    }

    public InlineKeyboardMarkup getInlineKeyboardAttachFileOrEndSessionButton() {
        return inlineKeyboardAttachFileOrEndSessionButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardAppealImageDocOrContinueButton(){
        return inlineKeyboardAppealImageDocOrContinueButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardAppealAdditionImageOrContinueButton(){
        return inlineKeyboardAppealAdditionImageOrContinueButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardEndSession()
    {
        return inlineKeyboardCloseApplicationButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardOfferMenuOrNewAppeal()
    {
        return inlineKeyboardOfferMenuOrNewAppealButton();
    }
}
