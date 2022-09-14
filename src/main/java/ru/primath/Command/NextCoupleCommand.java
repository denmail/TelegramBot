package ru.primath.Command;

import ru.primath.Manager.MessageManager;
import ru.primath.Manager.PrimatManager;
import ru.primath.Manager.ScheduleManager;
import ru.primath.Objects.NewCouple;
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
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        NewCouple couple = ScheduleManager.getNextCouple(primat);
        bot.execute(MessageManager.nextCoupleMessage(primat.getChatId(), couple));
    }
}
