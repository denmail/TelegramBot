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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DScheduleCommand extends Command {

    public DScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        bot.execute(MessageManager.dayScheduleMessage(primat, ScheduleManager.getCurrentDay()));
    }
}
