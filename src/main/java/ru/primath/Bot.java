package ru.primath;

import ru.primath.Manager.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    CommandManager cm = new CommandManager();
    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        System.out.println(botName);
        ScheduleManager sm = new ScheduleManager();
        DBManager.loadScheduleFromDB("firstDenominator");
        DBManager.loadScheduleFromDB("firstNumerator");
        DBManager.loadScheduleFromDB("secondDenominator");
        DBManager.loadScheduleFromDB("secondNumerator");
        DBManager.loadPrimatsFromDB();
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasCallbackQuery()) {
                String queryData = update.getCallbackQuery().getData();
                InlineManager.checkCallbackQueryData(queryData, this, update);
            } else if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                cm.findCommand(inMessage.getText(), this, update);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
