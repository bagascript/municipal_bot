package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class NewsButtons
{
    private static final InlineKeyboardButton IMPROVEMENT_NEWS_BUTTON = new InlineKeyboardButton("Благоустройство");
    private static final InlineKeyboardButton CUSTODY_NEWS_BUTTON = new InlineKeyboardButton("Опека и попечительство");
    private static final InlineKeyboardButton EVENT_NEWS_BUTTON = new InlineKeyboardButton("События округа");
    private static final InlineKeyboardButton LATEST_NEWS_BUTTON = new InlineKeyboardButton("Актуально");
    private static final InlineKeyboardButton PREVIOUS_BUTTON = new InlineKeyboardButton("Назад");

    private InlineKeyboardMarkup inlineKeyboardSectionNewsButton()
    {
        IMPROVEMENT_NEWS_BUTTON.setCallbackData("/impNews");
        CUSTODY_NEWS_BUTTON.setCallbackData("/cusNews");
        EVENT_NEWS_BUTTON.setCallbackData("/evNews");
        LATEST_NEWS_BUTTON.setCallbackData("/latNews");
        PREVIOUS_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowInline1 = List.of(IMPROVEMENT_NEWS_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(CUSTODY_NEWS_BUTTON);
        List<InlineKeyboardButton> rowInline3 = List.of(EVENT_NEWS_BUTTON);
        List<InlineKeyboardButton> rowInline4 = List.of(LATEST_NEWS_BUTTON);
        List<InlineKeyboardButton> rowInline5 = List.of(PREVIOUS_BUTTON);

        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2, rowInline3, rowInline4, rowInline5);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    public InlineKeyboardMarkup getInlineKeyboardSectionNewsButton() {
        return inlineKeyboardSectionNewsButton();
    }
}
