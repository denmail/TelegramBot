package Command;

import Manager.MessageManager;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WeekCommand extends Command{

    public WeekCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendMessage weekMessage = MessageManager.weekMessage(update.getMessage().getChatId(), isOdd());
        bot.execute(weekMessage);
    }
    private Boolean isOdd(){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_MONTH) % 2 != 0;
    }
}
