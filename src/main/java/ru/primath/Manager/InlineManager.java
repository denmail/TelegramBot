package ru.primath.Manager;

import ru.primath.Command.GoFuckYourselfCommand;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InlineManager {
    public static void checkCallbackQueryData(String queryData, AbsSender bot, Update update) throws TelegramApiException {
        if (queryData.contains("subGroupJoin")) {
            bot.execute(MessageManager.registerPhoto(update.getCallbackQuery()));
            return;
        }

        if (queryData.contains("FuckReply")) {
            GoFuckYourselfCommand.fuckReply(bot, update);
            return;

        }
        System.out.println("Ты не должен был здесь оказаться...");
    }
}
