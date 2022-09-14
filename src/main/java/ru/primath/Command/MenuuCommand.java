package ru.primath.Command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.MessageManager;

public class MenuuCommand extends Command {
    public MenuuCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.menuuMessage(update.getMessage().getChatId()));
    }
}
