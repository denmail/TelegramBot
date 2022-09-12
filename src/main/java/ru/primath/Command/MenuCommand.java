package ru.primath.Command;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.MessageManager;

public class MenuCommand extends Command {
    public MenuCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendPhoto menuMessage = MessageManager.menuMessage(update.getMessage().getChatId());
        bot.execute(menuMessage);
    }
}