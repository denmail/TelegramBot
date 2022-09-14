package ru.primath.Manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.primath.Command.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CommandManager {
    static HashMap<String, Command> commands = new HashMap<>();

    public CommandManager(){
        NotCommand nc = new NotCommand();
        StartCommand startCommand = new StartCommand("/start");
        GoFuckYourselfCommand goFuckYourselfCommand = new GoFuckYourselfCommand("послать");
        WeekCommand weekCommand = new WeekCommand("числитель/знаменатель");
        NextCoupleCommand nextCoupleCommand = new NextCoupleCommand("пара");
        ChooseScheduleCommand chooseScheduleCommand = new ChooseScheduleCommand("расписание");
        MenuCommand menuCommand = new MenuCommand("меню");
        //DScheduleCommand dScheduleCommand = new DScheduleCommand("день");
        //WScheduleCommand wScheduleCommand = new WScheduleCommand("неделя");
        MenuuCommand menuuCommand = new MenuuCommand("менюю");

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
        String checkCommand = inMsg.split(" ")[0].toLowerCase().replaceAll("\\s+", " ");
        if(commands.containsKey(checkCommand)) {
            System.out.println("Команда распознана: " + checkCommand);
            commands.get(checkCommand).doCommand(bot, update);
            return true;
        } else {

            commands.get("Команды не будет").doCommand(bot, update);
            return false;
        }
    }
    public static boolean containsCommand(String txt) {
        return commands.containsKey(txt);
    }
}
