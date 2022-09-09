package Command;

import Manager.PrimatManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand extends Command{
    public StartCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        bot.execute(new SendMessage(chat_id,"EBALA"));
    }
}
