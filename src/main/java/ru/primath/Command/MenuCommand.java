package ru.primath.Command;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.primath.Manager.MessageManager;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MenuCommand extends Command {
    public MenuCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.menuMessage(update.getMessage().getChatId()));
    }
}
