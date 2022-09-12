package ru.primath.Manager;

import ru.primath.Objects.Primat;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimatManager {
    private static HashMap<String, Primat> Makara = new HashMap<>();

    public static void addPrimat(Primat primat) {
        Makara.put(primat.getUsername(), primat);
    }

    public static Primat getPrimat(String username) {
        if (Makara.containsKey(username)) {
            Primat primat = Makara.get(username);
           return primat;
        }
        return null;
    }

    public static void saveMakara() {
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

            reader.close();
            System.out.println("LOADED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
