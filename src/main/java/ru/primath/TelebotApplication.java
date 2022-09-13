package ru.primath;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class TelebotApplication {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot("Primath", "5659523562:AAHCkmVHYxbThlY-UrjmdCAoP0JKApm90kM"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
