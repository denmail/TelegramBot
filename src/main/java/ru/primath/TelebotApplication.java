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
            botsApi.registerBot(new Bot("Primath", "5558671797:AAGa0kdU4umOOUbgV4j25iNpfRmmp-pwFqM"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
