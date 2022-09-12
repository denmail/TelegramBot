package ru.primath.Command;

import ru.primath.Manager.MessageManager;
import ru.primath.Manager.PrimatManager;
import ru.primath.Manager.ScheduleManager;
import ru.primath.Objects.Primat;
import ru.primath.Objects.Schedule.Couple;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DScheduleCommand extends Command{
    public DScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Date date = new Date();
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        String msg = "Расписание на день:\n\n";
        for (int i = 1; i <= 4; i++) {
            Couple couple = ScheduleManager.getCouple(primat.getSubGroup(), isOdd(date), getDay(date), i);
            if(couple.name.contains("none")){
                msg += String.format("%d: --\n", i);
            } else {
                msg += String.format("%d: %s в %s\n", i, couple.name, couple.office);
            }
        }

        bot.execute(MessageManager.scheduleMessage(primat.getChatId(), msg));
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
