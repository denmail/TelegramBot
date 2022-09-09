package Command;

import Manager.CommandManager;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Command {
    private String commandText;

    public Command(String commandText) {
        CommandManager.register(commandText, this);
        this.commandText = commandText;
        System.out.println(CommandManager.containsCommand(commandText));
    }
     public Command(){

     }

    public void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        System.out.print("");
    }
}
