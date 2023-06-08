package org.tgbotusers.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class PersonalDataButtons
{
    private final InlineKeyboardButton BACK_BUTTON = new InlineKeyboardButton("Изменить ФИО");
    private final InlineKeyboardButton NEXT_BUTTON = new InlineKeyboardButton("Далее");

    private final InlineKeyboardButton SKIP_BUTTON = new InlineKeyboardButton("Пропустить");
    private final InlineKeyboardButton CONTINUE_TO_PHONE_BUTTON = new InlineKeyboardButton("Ввести номер телефона");

    private final InlineKeyboardButton FIO_BUTTON = new InlineKeyboardButton("Отправьте ФИО ещё раз");
    private final InlineKeyboardButton PHONE_BUTTON = new InlineKeyboardButton("Отправьте телефон ещё раз");
    private final InlineKeyboardButton EMAIL_BUTTON = new InlineKeyboardButton("Отправьте почту ещё раз");

    private InlineKeyboardMarkup inlineKeyboardFIOButton()
    {
        BACK_BUTTON.setCallbackData("/recordFIO");
        NEXT_BUTTON.setCallbackData("/skipOrRecordPhone");

        List<InlineKeyboardButton> rowInline1 = List.of(NEXT_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(BACK_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardSkipOrRecordPhoneButton()
    {
        SKIP_BUTTON.setCallbackData("/recordEmail");
        CONTINUE_TO_PHONE_BUTTON.setCallbackData("/recordPhone");

        List<InlineKeyboardButton> rowInline1 = List.of(CONTINUE_TO_PHONE_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(SKIP_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardPhoneButton()
    {
        BACK_BUTTON.setText("Изменить номер телефона");
        BACK_BUTTON.setCallbackData("/recordPhone");
        NEXT_BUTTON.setCallbackData("/recordEmail");

        List<InlineKeyboardButton> rowInline1 = List.of(NEXT_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(BACK_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardEmailButton()
    {
        BACK_BUTTON.setText("Изменить адрес эл. почты");
        BACK_BUTTON.setCallbackData("/recordEmail");
        NEXT_BUTTON.setCallbackData("/recordAddress");

        List<InlineKeyboardButton> rowInline1 = List.of(NEXT_BUTTON);
        List<InlineKeyboardButton> rowInline2 = List.of(BACK_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardFIOErrorButton()
    {
        FIO_BUTTON.setCallbackData("/recordFIO");

        List<InlineKeyboardButton> rowInline = List.of(FIO_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardPhoneErrorButton()
    {
        PHONE_BUTTON.setCallbackData("/recordPhone");

        List<InlineKeyboardButton> rowInline = List.of(PHONE_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardEmailErrorButton()
    {
        EMAIL_BUTTON.setCallbackData("/recordEmail");

        List<InlineKeyboardButton> rowInline = List.of(EMAIL_BUTTON);
        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    public InlineKeyboardMarkup getInlineKeyboardFIOButton(){
        return inlineKeyboardFIOButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardSkipOrRecordPhoneButton() {
        return inlineKeyboardSkipOrRecordPhoneButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardPhoneButton(){
        return inlineKeyboardPhoneButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardEmailButton(){
        return inlineKeyboardEmailButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardFIOErrorButton(){
        return inlineKeyboardFIOErrorButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardPhoneErrorButton(){
        return inlineKeyboardPhoneErrorButton();
    }

    public InlineKeyboardMarkup getInlineKeyboardEmailErrorButton(){
        return inlineKeyboardEmailErrorButton();
    }

}
