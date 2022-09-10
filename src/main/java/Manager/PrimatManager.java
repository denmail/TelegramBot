package Manager;

import Objects.Primat;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimatManager {
    private static HashMap<String, Primat> Makara = new HashMap<>();
    private static String turip_photo = "AgACAgIAAxkBAAIBZWMblFUnu1a6pe1v0WD9__iug73hAAINwDEb6PeQSKbEairiIDAmAQADAgADeAADKQQ";

    public static void addPrimat(Primat primat) {
        Makara.put(primat.getUsername(), primat);
    }

    public static Primat getPrimat(String username) {
        if (Makara.containsKey(username)) {
            System.out.println("Makara <3 " + username);
            Primat primat = Makara.get(username);
           return primat;
        }
        return null;
    }

    public static void startMessage(AbsSender absSender, Long chatId) {
        //Сообщение после /start
        SendMessage settings = new SendMessage();
        settings.setText("Здарова, приматище!\nИз какой подгруппы будешь?");
        settings.setChatId(chatId);
        //Кнопка первой подгруппы
        InlineKeyboardButton subGroupButton1 = new InlineKeyboardButton();
        subGroupButton1.setText("Первая");
        subGroupButton1.setCallbackData("subGroupJoin1");
        //Кнопка второй подгруппы
        InlineKeyboardButton subGroupButton2 = new InlineKeyboardButton();
        subGroupButton2.setText("Вторая");
        subGroupButton2.setCallbackData("subGroupJoin2");
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

    public static void registerPrimat(AbsSender absSender, Long chatId, String data, User user) {
        if (!Makara.containsKey(user.getUserName())) {
            Primat primat = new Primat(user, data.contains("1") ? 1 : 2, "Primat");
            addPrimat(primat);
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(new InputFile(turip_photo));
            sendPhoto.setChatId(chatId);
            sendPhoto.setCaption("Добро пожаловать в отряд приматов");

            try {
                absSender.execute(sendPhoto);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            saveMakara();

        } else {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText("Без обид, но ты и так уже примат\n(Сменить ориентацию можно в Настройках)");
            try {
                absSender.execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveMakara() {
        try {
            BufferedWriter writer = Files.newBufferedWriter( Paths.get("src/main/resources/Makara.json"));

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(Makara);

            writer.write(json);
            writer.close();
            System.out.println("SAVED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadMakara() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/Makara.json"));

            Gson gson = new Gson();

            Type mapType = new TypeToken<HashMap<String,Primat>>(){}.getType();

            HashMap<String, Primat> makara = gson.fromJson(reader, mapType);
            Makara = makara;
            System.out.println("ahuet'");

            reader.close();
            System.out.println("LOADED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
