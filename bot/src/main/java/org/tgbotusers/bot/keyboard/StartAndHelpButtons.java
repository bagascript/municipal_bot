package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class StartAndHelpButtons
{
    private final InlineKeyboardButton HELP_BUTTON = new InlineKeyboardButton("/help");
    private final InlineKeyboardButton SCHEDULE_BUTTON = new InlineKeyboardButton("/schedule");

    private InlineKeyboardMarkup inlineKeyboardHelpButton()
    {
        HELP_BUTTON.setCallbackData("/help");
        SCHEDULE_BUTTON.setCallbackData("/schedule");

        List<InlineKeyboardButton> rowInline = List.of(HELP_BUTTON, SCHEDULE_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardBotScheduleButton()
    {
        HELP_BUTTON.setText("Меню");
        HELP_BUTTON.setCallbackData("/help");

        List<InlineKeyboardButton> rowInline = List.of(HELP_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    public InlineKeyboardMarkup getInlineKeyboardHelpButton(){
        return inlineKeyboardHelpButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardBotScheduleButton(){
        return inlineKeyboardBotScheduleButton();
    }
}
