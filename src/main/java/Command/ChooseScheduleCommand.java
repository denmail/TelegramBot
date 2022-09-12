package Command;

import Manager.MessageManager;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChooseScheduleCommand extends Command{
    public ChooseScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.chooseScheduleMessage(update.getMessage().getChatId()));
    }
}
