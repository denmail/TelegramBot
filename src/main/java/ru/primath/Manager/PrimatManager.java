package ru.primath.Manager;

import ru.primath.Objects.Primat;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
