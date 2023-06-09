package ru.primath.Manager;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardManager extends KeyboardButton {

    public synchronized void setButtons(SendMessage sendMessage) {
        sendMessage.setReplyMarkup(setMenuKeyboard());
    }

    public synchronized void setButtons(SendPhoto sendPhoto) {
        sendPhoto.setReplyMarkup(setMenuKeyboard());
    }

    public synchronized ReplyKeyboardMarkup setMenuKeyboard() {

        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardButton kb1 = new KeyboardButton();
        kb1.setText("Числитель/Знаменатель");
        KeyboardButton kb2 = new KeyboardButton();
        kb2.setText("Расписание");
        KeyboardButton kb3 = new KeyboardButton();
        kb3.setText("Пара");
        KeyboardButton kb4 = new KeyboardButton();
        kb4.setText("Профиль");
        KeyboardButton kb5 = new KeyboardButton();
        kb5.setText("Полезное");

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки во первую строчку клавиатуры
        keyboardFirstRow.add(kb1);

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(kb2);
        keyboardSecondRow.add(kb3);

        // Третья строчка клавиатуры
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        // Добавляем кнопки в третью строчку клавиатуры
        keyboardThirdRow.add(kb4);
        keyboardThirdRow.add(kb5);

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }

    public synchronized void chooseSchedule(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Неделя"));
        keyboardFirstRow.add(new KeyboardButton("День"));
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Меню"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

}
