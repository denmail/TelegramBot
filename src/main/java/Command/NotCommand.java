package Command;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class NotCommand extends Command{
    public NotCommand() {
        super("Команды не будет");
    }

    @Override
    public void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText("Неизвестная команда");
        bot.execute(sendMessage);
    }
}
