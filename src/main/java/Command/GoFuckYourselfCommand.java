package Command;

import Manager.PrimatManager;
import Objects.Primat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
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
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {

        SendMessage reply = new SendMessage();
        reply.setText("Послан");
        reply.setChatId(update.getMessage().getChatId());
        try {
            bot.execute(reply);
            System.out.println("NAHUI1");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("allo");
        System.out.println(update.getMessage().getFrom().getUserName());
        Primat primat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
        System.out.println(primat.getUsername());
        System.out.println("0");
        SendMessage fuckMessage = new SendMessage();
        fuckMessage.setText(primat.getName() + " послал(а) тебя нахуй!\nОтветишь или терпила?");
        System.out.println("1");
        InlineKeyboardMarkup fuckKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton fuckReplyButton = new InlineKeyboardButton();
        fuckReplyButton.setText("Послать в ответ");
        fuckReplyButton.setCallbackData("FuckReply_" + primat.getUsername());
        System.out.println("2");
        List<InlineKeyboardButton> fuckRow = new ArrayList<>();
        fuckRow.add(fuckReplyButton);
        List<List<InlineKeyboardButton>> fuckKeyboard = new ArrayList<>();
        System.out.println("3");
        fuckKeyboardMarkup.setKeyboard(fuckKeyboard);
        System.out.println("4");
        fuckMessage.setReplyMarkup(fuckKeyboardMarkup);
        String[] message = update.getMessage().getText().split(" ");
        fuckMessage.setChatId(PrimatManager.getPrimat(message[1]).getChatId());
        System.out.println("5");
        try {
            bot.execute(fuckMessage);
            System.out.println("NAHUI2");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
