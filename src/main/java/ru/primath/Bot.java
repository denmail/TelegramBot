package ru.primath;

import ru.primath.Manager.*;
import ru.primath.Objects.Primat;
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
        PrimatManager.loadMakara();
        ScheduleManager sm = new ScheduleManager();
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
    //я ЕБАЛ
    //мать гита
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasCallbackQuery()) {
                String queryData = update.getCallbackQuery().getData();
                InlineManager.checkCallbackQueryData(queryData, this, update);
            } else if (update.hasMessage() && update.getMessage().hasText()) {
                Message inMessage = update.getMessage();
                Primat checkPrimat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
                if (checkPrimat != null) {
                    if (checkPrimat.isFeedbackMessage()) {
                        checkPrimat.setFeedbackMessage(false);
                        execute(MessageManager.feedbackReplyMessage(checkPrimat.getChatId(),inMessage.getText()));
                        return;
                    }
                }
                cm.findCommand(inMessage.getText(), this, update);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
