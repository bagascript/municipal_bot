package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class TextAppealButtons
{
    private final InlineKeyboardButton BACK_BUTTON = new InlineKeyboardButton("Изменить адрес");
    private final InlineKeyboardButton NEXT_BUTTON = new InlineKeyboardButton("Далее");

    private final InlineKeyboardButton ADDRESS_BUTTON = new InlineKeyboardButton("Отправьте адрес ещё раз");
    private final InlineKeyboardButton APPEAL_TEXT_BUTTON = new InlineKeyboardButton("Отправьте текст ещё раз");

    private InlineKeyboardMarkup inlineKeyboardAddressButton()
    {
        BACK_BUTTON.setCallbackData("/recordAddress");
        NEXT_BUTTON.setCallbackData("/recordTextAppeal");

        List<InlineKeyboardButton> rowInline1 = List.of(NEXT_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(BACK_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardAddressErrorButton()
    {
        ADDRESS_BUTTON.setCallbackData("/recordAddress");

        List<InlineKeyboardButton> rowInline = List.of(ADDRESS_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardAppealTextButton()
    {
        BACK_BUTTON.setText("Изменить текст");
        BACK_BUTTON.setCallbackData("/recordTextAppeal");
        NEXT_BUTTON.setCallbackData("/askDocOrImage");

        List<InlineKeyboardButton> rowInline1 = List.of(NEXT_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(BACK_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardAppealTextErrorButton()
    {
        APPEAL_TEXT_BUTTON.setCallbackData("/recordTextAppeal");

        List<InlineKeyboardButton> rowInline = List.of(APPEAL_TEXT_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    public InlineKeyboardMarkup getInlineKeyboardAddressButton(){
        return inlineKeyboardAddressButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardAddressErrorButton(){
        return inlineKeyboardAddressErrorButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardAppealTextButton(){
        return inlineKeyboardAppealTextButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardAppealTextErrorButton(){
        return inlineKeyboardAppealTextErrorButton();
    }
}
