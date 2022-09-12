package Command;

import NotificationKeyboard.NotificationKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CScheduleCommand extends Command{
    public CScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        NotificationKeyboard nc = new NotificationKeyboard();
        nc.chooseSchedule(sendMessage);
    }
}
