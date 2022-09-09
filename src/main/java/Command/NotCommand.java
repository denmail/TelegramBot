package Command;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class NotCommand extends Command{
    public NotCommand(String commandText) {
        super(commandText);
    }


    public static void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText("Неизвестная команда");
        bot.execute(sendMessage);
    }
}
