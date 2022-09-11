package Command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class FeedbackCommand extends Command{

    public FeedbackCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        System.out.println("baza");
    }
}
