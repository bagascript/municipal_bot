package org.tgbotusers.bot.text.file;

public interface FileTextResponse
{
    String FILE_SAVING_TEXT =
            """
            \uD83D\uDCBE Файл сохранен.

            Для отправки очередного файла или завершения обращения
            нажмите на соответствующую кнопку:
            """;

    String DUPLICATE_FILE_ERROR_TEXT =
            """
            ⚠ Ошибка! Данный файл вы уже отправляли раннее.

            Проверьте уникальность выбранного файла.
            """;

    String FILES_SAVING_TEXT =
            """
            \uD83D\uDDC3 Все возможные файлы сохранены!
            """;

    String FILE_LIMIT_ERROR_TEXT =
            """
            ⚠ Ошибка! Количество отправлений файлов имеет ограничение от 1 до 5.
            """;

    String FILE_FORMAT_TYPE_ERROR_TEXT =
            """
            ⚠ Ошибка! Проверьте правильность отправленного сообщения
            или типа файла.
            """;
}
