package Test.Command;

import Test.NotificationKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

public class ScheduleCommand extends Command{

    @Override
    public void vDoCommand(SendMessage msg) {
        NotificationKeyboard nk = new NotificationKeyboard();
        nk.chooseSchedule(msg);
        InputFile schedule = new InputFile(new File("F:\\MyProject\\Java\\tele-bot\\src\\main\\resources\\Schedule.jpg"));
        SendPhoto sendPhoto = new SendPhoto(msg.getChatId(), schedule);
        try {
            execute(sendPhoto);
        } catch (Exception e) {
            System.out.println("loh");
        }
        msg.setText("Расписание, а ты лох");
    }

    public ScheduleCommand(String indentifier, String commandText) {
        super(indentifier, commandText);
    }
}
