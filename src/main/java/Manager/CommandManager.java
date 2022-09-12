package Manager;

import Command.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class CommandManager {
    static HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(){
        StartCommand startCommand = new StartCommand("/start");
        GoFuckYourselfCommand goFuckYourselfCommand = new GoFuckYourselfCommand("послать");
        WeekCommand weekCommand = new WeekCommand("неделя");
        NextCoupleCommand nextCoupleCommand = new NextCoupleCommand("пара");
        FeedbackCommand feedbackCommand = new FeedbackCommand("связь");
        CScheduleCommand cScheduleCommand = new CScheduleCommand("расписание");
    }

    public static boolean register(String commandName, Command command) {
        if(commands.containsKey(commandName)) {
            return false;
        } else {
            commands.put(commandName, command);
            return true;
        }
    }

    public static boolean findCommand(String inMsg, AbsSender bot, Update update) throws TelegramApiException {
        if(commands.containsKey(inMsg.split(" ")[0].toLowerCase().replaceAll("\\s+", " "))) {
            System.out.println("Команда распознана");
            commands.get(inMsg.split(" ")[0].toLowerCase().replaceAll("\\s+", " ")).doCommand(bot, update);
            return true;
        } else {
            NotCommand nc = new NotCommand();
            nc.doCommand(bot, update);
            return false;
        }
    }
    public static boolean containsCommand(String txt) {
        return commands.containsKey(txt);
    }
}
