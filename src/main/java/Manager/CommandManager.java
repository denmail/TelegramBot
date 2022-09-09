package Manager;

import Command.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class CommandManager {
    static HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(){
        StartCommand startCommand = new StartCommand("/start");
        WeekCommand weekCommand = new WeekCommand("Неделя");
    }

    public static boolean register(String commandName, Command command) {
        if(commands.containsKey(commandName)) {
            return false;
        } else {
            commands.put(commandName, command);
            return true;
        }
    }

    public static boolean findCommand(String inMsg, AbsSender bot, String chat_id) throws TelegramApiException {
        if(commands.containsKey(inMsg)) {
            commands.get(inMsg).doCommand(bot, chat_id);
            return true;
        } else {
            NotCommand nc = new NotCommand();
            nc.doCommand(bot, chat_id);
            return false;
        }
    }
    public static boolean containsCommand(String txt) {
        return commands.containsKey(txt);
    }
}
