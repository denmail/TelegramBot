package Command;

import Manager.MessageManager;
import Manager.PrimatManager;
import Objects.Primat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class FeedbackCommand extends Command {
    public FeedbackCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.feedbackMessage(update.getMessage().getChatId()));
    }

    public static void setupReply(AbsSender bot, Update update) throws TelegramApiException {
        Primat primat = PrimatManager.getPrimat(update.getCallbackQuery().getFrom().getUserName());
        bot.execute(MessageManager.sendFeedbackMessage(primat));
    }
}
