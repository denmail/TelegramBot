import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrimatManager {
    private static HashMap<String, Primat> Makara = new HashMap<>();

    public static void addPrimat(Primat primat) {
        Makara.put(primat.getUserName(), primat);
    }

    public static Primat getPrimat(String username) {
        if (Makara.containsKey(username)) {
           return Makara.get(username);
        }
        return null;
    }

    public static void registerPrimat(AbsSender absSender, Update update) {
        //Сообщение после /start
        SendMessage settings = new SendMessage();
        settings.setText("Здарова, приматище!\nИз какой подгруппы будешь?");
        settings.setChatId(update.getMessage().getChatId());
        //Кнопка первой подгруппы
        InlineKeyboardButton subGroupButton1 = new InlineKeyboardButton();
        subGroupButton1.setText("Первая");
        subGroupButton1.setCallbackData("subGroup1");
        //Кнопка второй подгруппы
        InlineKeyboardButton subGroupButton2 = new InlineKeyboardButton();
        subGroupButton2.setText("Вторая");
        subGroupButton2.setCallbackData("subGroup2");
        //Строка с подгруппами
        List<InlineKeyboardButton> subGroupRow = new ArrayList<>();
        subGroupRow.add(subGroupButton1);
        subGroupRow.add(subGroupButton2);
        //Клавиатура с подгуппами (да-да, из одной строки)
        List<List<InlineKeyboardButton>> subGroupKeyboard = new ArrayList<>();
        subGroupKeyboard.add(subGroupRow);
        //Разметка клавиатуры (финалочка короче)
        InlineKeyboardMarkup subGroupKeyboardMarkup = new InlineKeyboardMarkup();
        subGroupKeyboardMarkup.setKeyboard(subGroupKeyboard);
        //Прикрепляем клавиатуру к сообщению
        settings.setReplyMarkup(subGroupKeyboardMarkup);
        try {
            absSender.execute(settings);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
