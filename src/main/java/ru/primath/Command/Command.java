package ru.primath.Command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.CommandManager;

public class Command {
    private String commandText;

    public Command(String commandText) {
        CommandManager.register(commandText, this);
        this.commandText = commandText;
        System.out.println(CommandManager.containsCommand(commandText));
    }

    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        System.out.println("baza");
    }
}
