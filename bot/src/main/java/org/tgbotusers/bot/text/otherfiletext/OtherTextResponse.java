package org.tgbotusers.bot.text.otherfiletext;

public interface OtherTextResponse {
    String ATTACH_FILE_TEXT =
            """
            При необходимости прикрепите к обращению файл (файлы), подтверждающие проблему:
            """;

    String INCORRECT_REQUEST_ERROR =
            """
            Что-то пошло не так! Проверьте правильность введённого запроса и попробуйте ещё раз.
            """;

    String END_APPEAL_TEXT =
            """
            Телефон секретаря: 328-58-31, 323-32-61

            Пожалуйста ожидайте обратной связи в течении трёх следующих дней.
            Спасибо за оставленную заявку!
            """;
}
