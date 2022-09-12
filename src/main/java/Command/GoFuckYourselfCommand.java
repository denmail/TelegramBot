package Command;

import Manager.MessageManager;
import Manager.PrimatManager;
import Objects.Primat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class GoFuckYourselfCommand extends Command{
    public GoFuckYourselfCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Message message = update.getMessage();
        String[] messageArgs = message.getText().split("\\s+");
        Primat toPrimat = PrimatManager.getPrimat(messageArgs[1]);
        Primat fromPrimat = PrimatManager.getPrimat(message.getFrom().getUserName());

        SendMessage fuckMessageToSender = MessageManager.fuckMessageToSender(fromPrimat, toPrimat);
        bot.execute(fuckMessageToSender);

        SendMessage fuckMessageToReceiver = MessageManager.fuckMessageToReceiver(fromPrimat, toPrimat);
        bot.execute(fuckMessageToReceiver);
    }

    public static void fuckReply(AbsSender bot, Update update) throws TelegramApiException {
        String username = update.getCallbackQuery().getData().split("\\s+")[1];
        Primat toPrimat = PrimatManager.getPrimat(username);
        Primat fromPrimat = PrimatManager.getPrimat(update.getCallbackQuery().getFrom().getUserName());

        EditMessageText editMessage = MessageManager.fuckReplyToSender(fromPrimat, toPrimat);
        editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        bot.execute(editMessage);

        SendMessage fuckReplyToReceiver = MessageManager.fuckReplyToReceiver(fromPrimat, toPrimat);
        bot.execute(fuckReplyToReceiver);
    }
}
