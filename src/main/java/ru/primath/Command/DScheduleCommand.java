package ru.primath.Command;

import ru.primath.Manager.MessageManager;
import ru.primath.Manager.PrimatManager;
import ru.primath.Manager.ScheduleManager;
import ru.primath.Objects.Primat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DScheduleCommand extends Command {

    public DScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        bot.execute(MessageManager.scheduleMessage(primat, ScheduleManager.getCurrentDay(), false));
    }
}
