package ru.primath.Command;

import ru.primath.Manager.MessageManager;
import ru.primath.Manager.PrimatManager;
import ru.primath.Manager.ScheduleManager;
import ru.primath.Objects.Primat;
import ru.primath.Objects.Schedule.Couple;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class NextCoupleCommand extends Command{
    private LocalTime c1b = LocalTime.parse("09:30:00");
    private LocalTime c2b = LocalTime.parse("11:15:00");
    private LocalTime c3b = LocalTime.parse("13:35:00");
    private LocalTime c4b = LocalTime.parse("15:20:00");
    private LocalTime c1e = LocalTime.parse("11:05:00");
    private LocalTime c2e = LocalTime.parse("12:50:00");
    private LocalTime c3e = LocalTime.parse("15:10:00");
    private LocalTime c4e = LocalTime.parse("16:55:00");

    public NextCoupleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Date date = new Date();
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        Couple couple = ScheduleManager.getCouple(primat.getSubGroup(), isOdd(date), getDay(date), getNextCouple());
        if(couple.name.contains("none")) {
            couple = ScheduleManager.getCouple(primat.getSubGroup(), isOdd(date), getDay(date), getNextCouple()+1);
        }
        bot.execute(MessageManager.nextCoupleMessage(update.getMessage().getChatId(), couple));
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
    private int getNextCouple() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(c1b) || now.isAfter(c4e)) {
            System.out.println("r1");
            return 1;
        } else if (now.isBefore(c2b)) {
            System.out.println("r2");
            return 2;
        } else if (now.isBefore(c3b)) {
            System.out.println("r3");
            return 3;
        } else if (now.isBefore(c4e)) {
            System.out.println("r4");
            return 4;
        } else{
            System.out.println("r4");
            return 5;
        }
    }
}
