package Command;

import Manager.PrimatManager;
import Objects.Primat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
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
    public void doCommand(AbsSender bot, String chat_id) throws TelegramApiException {
        SendMessage fuckMessage = new SendMessage();
        fuckMessage.setText(PrimatManager.getPrimat(chat_id).getName() + " послал(а) тебя нахуй!\nОтветишь или терпила?");

        InlineKeyboardMarkup fuckKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton fuckReplyButton = new InlineKeyboardButton();
        fuckReplyButton.setText("Послать в ответ");
        fuckReplyButton.setCallbackData("FuckReply_" + PrimatManager.getPrimat(chat_id).getUsername());

        List<InlineKeyboardButton> fuckRow = new ArrayList<>();
        fuckRow.add(fuckReplyButton);
        List<List<InlineKeyboardButton>> fuckKeyboard = new ArrayList<>();

        fuckKeyboardMarkup.setKeyboard(fuckKeyboard);

        fuckMessage.setReplyMarkup(fuckKeyboardMarkup);

        try {
            bot.execute(fuckMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
