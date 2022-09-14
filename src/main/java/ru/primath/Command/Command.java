package ru.primath.Command;

import ru.primath.Manager.CommandManager;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Command {
    private String commandText;

    public Command(String commandText) {
        CommandManager.register(commandText, this);
        this.commandText = commandText;
        System.out.println("Зарегистрирована: " + commandText);
    }

    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        System.out.println("baza");
    }
}
