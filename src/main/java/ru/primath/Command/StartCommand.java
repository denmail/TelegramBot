package ru.primath.Command;

import ru.primath.Manager.MessageManager;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand extends Command{

    public StartCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.startPhoto(update.getMessage().getChatId()));

        bot.execute(MessageManager.startMessage(update.getMessage().getChatId()));
    }
}
