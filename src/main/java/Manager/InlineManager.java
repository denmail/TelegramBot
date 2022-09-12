package Manager;

import Command.FeedbackCommand;
import Command.GoFuckYourselfCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InlineManager {
    public static void checkCallbackQueryData(String queryData, AbsSender bot, Update update) throws TelegramApiException {
        if (queryData.contains("subGroupJoin")) {
            SendPhoto registerPhoto = MessageManager.registerPhoto(update.getCallbackQuery());
            bot.execute(registerPhoto);
            return;
        }

        if (queryData.contains("FuckReply")) {
            GoFuckYourselfCommand.fuckReply(bot, update);
            return;

        }

        if (queryData.contains("Feedback")) {
            FeedbackCommand.setupReply(bot, update);
            return;
        }

        System.out.println("Ты не должен был здесь оказаться...");
    }
}
