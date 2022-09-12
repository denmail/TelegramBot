package ru.primath.Objects;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class Menu {

    public static SendMessage sendFeedbackPrompt(Primat primat) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Введите ваше сообщение:");
        sendMessage.setChatId(primat.getChatId());
        primat.setFeedbackMessage(true);
        return  sendMessage;
    }
}
