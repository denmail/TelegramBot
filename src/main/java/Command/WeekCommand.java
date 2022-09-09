package Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
    public void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        Date date = new Date();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chat_id);
        sendMessage.setText(isOdd(date)?"Числитель":"Знаменатель");
        bot.execute(sendMessage);
    }
    private Boolean isOdd(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar.get(Calendar.WEEK_OF_MONTH) % 2 != 0;
    }
}
