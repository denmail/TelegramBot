import java.util.HashMap;

public class PrimatManager {
    private HashMap<String, Primat> Makara = new HashMap<>();

    public void addPrimat(Primat primat) {
        Makara.put(primat.getUserName(), primat);
    }

    public Primat getPrimat(String username) {
        if (Makara.containsKey(username)) {
           return Makara.get(username);
        }
        return null;
    }
}
