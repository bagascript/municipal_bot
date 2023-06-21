package org.tgbotusers.bot.command;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;


import java.util.List;

public interface BotMenuCommands
{
    List<BotCommand> LIST_OF_MENU_COMMANDS = List.of(
            new BotCommand("/help", "\uD83D\uDCDA Список услуг"),
            new BotCommand("/news", "\uD83D\uDCF0 Новости округа"),
            new BotCommand("/utility", "\uD83D\uDDC4 Полезная информация"),
            new BotCommand("/apply", "\uD83D\uDCCC Обращение в МО Васильевский"),
            new BotCommand("/schedule", "\uD83D\uDD0B График работы бота")
    );
}
