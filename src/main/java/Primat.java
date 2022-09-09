import org.telegram.telegrambots.meta.api.objects.User;

public class Primat {
    private int subGroup;
    private String username;
    private Long chatId;
    private String role;
    public Primat(User user, int subGroup, String role) {
        this.username = user.getUserName();
        this.chatId = user.getId();
        this.subGroup = subGroup;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
}
