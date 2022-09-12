package Command;

import Manager.PrimatManager;
import Manager.ScheduleManager;
import Objects.Primat;
import Objects.Schedule.Couple;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WScheduleCommand extends Command{
    public WScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Date date = new Date();
        String[] days = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница"};
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        String msg = "Расписание на неделю:\n\n";
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(primat.getChatId());
        sendMessage.setText(msg);
        bot.execute(sendMessage);
        for (int i = 0; i < 5; i++) {
            msg = days[i]+":\n\n";
            for (int j = 1; j <= 4; j++) {
                Couple couple = ScheduleManager.getCouple(primat.getSubGroup(), isOdd(date), i, j);
                if(couple.name.contains("none")){
                    msg += String.format("%d: --\n", j);
                } else {
                    msg += String.format("%d: %s в %s\n", j, couple.name, couple.office);
                }
            }
            sendMessage.setText(msg);
            bot.execute(sendMessage);
        }
    }
    private int isOdd(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH) % 2;
    }
    private int getDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.DAY_OF_WEEK) + 5)%7;
    }
}
