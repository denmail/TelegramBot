package ru.primath.Command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.primath.Manager.PrimatManager;
import ru.primath.Objects.Primat;

import java.util.ArrayList;
import java.util.List;

public class FeedbackCommand extends Command{
    String toWhom;
    public FeedbackCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        System.out.println("FeedbackMessage");
        SendMessage feedbackMessage = new SendMessage();
        feedbackMessage.setText("С кем нужно связаться?");
        feedbackMessage.setChatId(update.getMessage().getChatId());

        InlineKeyboardButton feedbackButton1 = new InlineKeyboardButton();
        feedbackButton1.setText("Староста");
        feedbackButton1.setCallbackData("FeedbackLeader");

        InlineKeyboardButton feedbackButton2 = new InlineKeyboardButton();
        feedbackButton2.setText("Администрация");
        feedbackButton2.setCallbackData("FeedbackAdministration");
        List<InlineKeyboardButton> feedbackRow1 = new ArrayList<>();
        feedbackRow1.add(feedbackButton1);
        feedbackRow1.add(feedbackButton2);

        List<List<InlineKeyboardButton>> feedbackKeyboard = new ArrayList<>();
        feedbackKeyboard.add(feedbackRow1);

        InlineKeyboardMarkup feedbackKeyboardMarkup = new InlineKeyboardMarkup();
        feedbackKeyboardMarkup.setKeyboard(feedbackKeyboard);

        feedbackMessage.setReplyMarkup(feedbackKeyboardMarkup);
        bot.execute(feedbackMessage);
    }

    public static void setupReply(AbsSender bot, Update update) {
        Primat primat = PrimatManager.getPrimat(update.getCallbackQuery().getFrom().getUserName());
        SendMessage feedbackMessage = Menu.sendFeedbackPrompt(primat);
        try {
            bot.execute(feedbackMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
