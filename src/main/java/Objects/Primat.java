package Objects;

import org.telegram.telegrambots.meta.api.objects.User;

public class Primat extends User {
    private int subGroup;
    private String role;
    public Primat(int subGroup, String role) {
        super();
        this.subGroup = subGroup;
        this.role = role;
    }
}
