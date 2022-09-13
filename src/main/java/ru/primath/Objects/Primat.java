package ru.primath.Objects;

import org.telegram.telegrambots.meta.api.objects.User;

public class Primat {
    private byte subGroup;
    private String username;
    private String name;
    private Long chatId;
    private String role;

    public Primat(User user, byte subGroup, String role) {
        this.username = user.getUserName();
        this.name = user.getFirstName();
        this.chatId = user.getId();
        this.subGroup = subGroup;
        this.role = role;
    }

    public Primat(byte subGroup, String username, String name, Long chatId, String role) {
        this.subGroup = subGroup;
        this.username = username;
        this.name = name;
        this.chatId = chatId;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public byte getSubGroup() {
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
