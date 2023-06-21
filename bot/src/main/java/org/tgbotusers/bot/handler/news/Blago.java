package org.tgbotusers.bot.handler.news;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.tgbotusers.bot.Bot;
import org.tgbotusers.bot.text.blagoustrojstvo.BlagoText;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Blago implements BlagoText
{
    static InlineKeyboardButton news1 = new InlineKeyboardButton
    //<--------------------------------------------------------------------------------------->
            ( "Новость 1" ); //TODO: <-- менять название кнопки в скобках внутри кавычек
    //<--------------------------------------------------------------------------------------->
    static InlineKeyboardButton news2 = new InlineKeyboardButton
    //<--------------------------------------------------------------------------------------->
            ( "Новость 2" ); //TODO: <-- менять название кнопки в скобках внутри кавычек
    //<--------------------------------------------------------------------------------------->
    static InlineKeyboardButton news3 = new InlineKeyboardButton
    //<--------------------------------------------------------------------------------------->
            ( "Новость 3" ); //TODO: <-- менять название кнопки в скобках внутри кавычек
    //<--------------------------------------------------------------------------------------->
    static InlineKeyboardButton news4 = new InlineKeyboardButton
    //<--------------------------------------------------------------------------------------->
            ( "Новость 4" ); //TODO: <-- менять название кнопки в скобках внутри кавычек
    //<--------------------------------------------------------------------------------------->
    static InlineKeyboardButton news5 = new InlineKeyboardButton
    //<--------------------------------------------------------------------------------------->
            ( "Новость 5" ); //TODO: <-- менять название кнопки в скобках внутри кавычек
    //<--------------------------------------------------------------------------------------->











    static InlineKeyboardButton PREVIOUS_BUTTON = new InlineKeyboardButton("Назад");

    private final Bot bot;

    private void sendSelectionNewsMenu(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("О чем желаете знать?");
        message.setReplyMarkup(inlineKeyboardSectionNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private InlineKeyboardMarkup inlineKeyboardSectionNewsButton()
    {
        news1.setCallbackData("/blagoNews1");
        news2.setCallbackData("/blagoNews2");
        news3.setCallbackData("/blagoNews3");
        news4.setCallbackData("/blagoNews4");
        news5.setCallbackData("/blagoNews5");
        PREVIOUS_BUTTON.setCallbackData("/news");

        List<InlineKeyboardButton> rowInline1 = List.of(news1);
        List<InlineKeyboardButton> rowInline2 = List.of(news2);
        List<InlineKeyboardButton> rowInline3 = List.of(news3);
        List<InlineKeyboardButton> rowInline4 = List.of(news4);
        List<InlineKeyboardButton> rowInline5 = List.of(news5);
        List<InlineKeyboardButton> rowInline6 = List.of(PREVIOUS_BUTTON);


        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline1, rowInline2, rowInline3, rowInline4, rowInline5, rowInline6);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    private InlineKeyboardMarkup inlineKeyboardBlagoNewsButton()
    {
        PREVIOUS_BUTTON.setCallbackData("/impNews");
        List<InlineKeyboardButton> rowInline = List.of(PREVIOUS_BUTTON);


        List<List<InlineKeyboardButton>> rowsInLine = List.of(rowInline);
        InlineKeyboardMarkup block = new InlineKeyboardMarkup();
        block.setKeyboard(rowsInLine);

        return block;
    }

    public void sendBlagoNews1(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(NEWS1);
        message.setReplyMarkup(inlineKeyboardBlagoNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendBlagoNews2(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(NEWS2);
        message.setReplyMarkup(inlineKeyboardBlagoNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendBlagoNews3(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(NEWS3);
        message.setReplyMarkup(inlineKeyboardBlagoNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendBlagoNews4(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(NEWS4);
        message.setReplyMarkup(inlineKeyboardBlagoNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void sendBlagoNews5(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(NEWS5);
        message.setReplyMarkup(inlineKeyboardBlagoNewsButton());
        try {
            bot.execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    public void getSendSelectionBlagoNewsMenu(long chatId) {
        sendSelectionNewsMenu(chatId);
    }
}
