package Command;

import Manager.CommandManager;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Command {
    private final String commandText;

    public Command(String commandText) {
        CommandManager.register(commandText, this);
        this.commandText = commandText;
        System.out.println(CommandManager.containsCommand(commandText));
    }

    public static void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        System.out.print("");
    }
}
