package Command;

import Manager.PrimatManager;
import Manager.ScheduleManager;
import Objects.Primat;
import Objects.Schedule.Couple;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
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
        System.out.println("--"+primat.getSubGroup());
        System.out.println("--"+isOdd(date));
        System.out.println("--"+getDay(date));
        System.out.println("--"+getNextCouple());
        Couple couple = ScheduleManager.getCouple(primat.getSubGroup(), isOdd(date)+1, getDay(date), getNextCouple());
        SendMessage sendMessage = new SendMessage();
        System.out.println(update.getMessage().getChatId()+"-----");
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(String.format("%s в %s кабинете не потеряйся", couple.name, couple.office));
        System.out.println("c3");
        bot.execute(sendMessage);
    }
    private int isOdd(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH) % 2;
    }
    private int getDay(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK)-1;
    }
    private int getNextCouple() {
        System.out.println("c1");
        LocalTime now = LocalTime.now();
        if (now.isBefore(c1b) || now.isAfter(c4e)) {
            System.out.println("t1");
            return 1;
        } else if (now.isBefore(c2b)) {
            System.out.println("t2");
            return 2;
        } else if (now.isBefore(c3b)) {
            System.out.println("t3");
            return 3;
        } else if (now.isBefore(c4b)) {
            System.out.println("t4");
            return 4;
        } else{
            return 1;
        }
    }
}
