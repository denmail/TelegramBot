package ru.primath.Objects;

import org.telegram.telegrambots.meta.api.objects.User;

public class Primat {
    private int subGroup;
    private String username;
    private String name;
    private Long chatId;
    private String role;

    public Primat(User user, int subGroup, String role) {
        this.username = user.getUserName();
        this.name = user.getFirstName();
        this.chatId = user.getId();
        this.subGroup = subGroup;
        this.role = role;
    }

    public Primat(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public int getSubGroup() {
        return subGroup;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

}
