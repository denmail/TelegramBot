package Command;

import Manager.MessageManager;
import Manager.PrimatManager;
import Manager.KeyboardManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class StartCommand extends Command{

    public StartCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendPhoto startPhoto = MessageManager.startPhoto(update.getMessage().getChatId());
        bot.execute(startPhoto);

        SendMessage startMessage = MessageManager.startMessage(update.getMessage().getChatId());
        bot.execute(startMessage);
    }
}
