package ru.primath.Command;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.primath.Manager.MessageManager;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.MessageManager;

public class NotCommand extends Command{
    public NotCommand() {
        super("Команды не будет");
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        bot.execute(MessageManager.notCommandMessage(update.getMessage().getChatId()));
    }
}
