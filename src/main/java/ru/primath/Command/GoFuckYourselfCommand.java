package ru.primath.Command;

import ru.primath.Manager.MessageManager;
import ru.primath.Manager.PrimatManager;
import ru.primath.Objects.Primat;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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

        bot.execute(MessageManager.fuckMessageToSender(fromPrimat, toPrimat));

        bot.execute(MessageManager.fuckMessageToReceiver(fromPrimat, toPrimat));
    }

    public static void fuckReply(AbsSender bot, Update update) throws TelegramApiException {
        String username = update.getCallbackQuery().getData().split("\\s+")[1];
        Primat toPrimat = PrimatManager.getPrimat(username);
        Primat fromPrimat = PrimatManager.getPrimat(update.getCallbackQuery().getFrom().getUserName());

        EditMessageText editMessage = MessageManager.fuckReplyToSender(fromPrimat, toPrimat);
        editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        bot.execute(editMessage);

        bot.execute(MessageManager.fuckReplyToReceiver(fromPrimat, toPrimat));
    }
}
