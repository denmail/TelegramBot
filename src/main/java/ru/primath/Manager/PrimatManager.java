package ru.primath.Manager;

import ru.primath.Objects.Primat;
import java.util.HashMap;

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
}
