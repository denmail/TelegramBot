package ru.primath.Command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.MessageManager;

public class UsefulCommand extends Command {

    public UsefulCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.usefulMessage(update.getMessage().getChatId()));
    }
}
