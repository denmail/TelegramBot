package ru.primath.Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.KeyboardManager;

public class CScheduleCommand extends Command{
    public CScheduleCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Расписание: ");
        sendMessage.setChatId(update.getMessage().getChatId());
        KeyboardManager nc = new KeyboardManager();
        nc.chooseSchedule(sendMessage);
        bot.execute(sendMessage);
    }
}
