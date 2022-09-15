package ru.primath.Command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.MessageManager;
import ru.primath.Manager.PrimatManager;
import ru.primath.Objects.Primat;

public class ProfileCommand extends Command {

    public ProfileCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        bot.execute(MessageManager.profilePhoto(primat.getChatId()));
        bot.execute(MessageManager.profileMessage(primat));
    }
}
