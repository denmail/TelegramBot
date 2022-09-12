package ru.primath.Manager;

import ru.primath.Objects.Primat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private static final String primat_photo = "src/main/resources/images/Primat.jpg";
    private static final String menu_photo = "src/main/resources/images/Menu.jpg";
    private static final String registerAccepted_photo = "src/main/resources/images/registerAccepted.jpg";
    private static final String registerAborted_photo = "src/main/resources/images/registerAborted.jpg";

    public static SendPhoto startPhoto(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(new File(primat_photo)));
        sendPhoto.setChatId(chatId);
        KeyboardManager keyboardManager = new KeyboardManager();
        keyboardManager.setButtons(sendPhoto);
        return sendPhoto;
    }

    public static SendMessage startMessage(Long chatId) {
        //Сообщение после /start
        SendMessage startMessage = new SendMessage();
        startMessage.setText("Здарова, приматище!\nИз какой подгруппы будешь?");
        startMessage.setChatId(chatId);
        //Кнопка первой подгруппы
        InlineKeyboardButton subGroupButton1 = new InlineKeyboardButton();
        subGroupButton1.setText("Первая");
        subGroupButton1.setCallbackData("subGroupJoin1");
        //Кнопка второй подгруппы
        InlineKeyboardButton subGroupButton2 = new InlineKeyboardButton();
        subGroupButton2.setText("Вторая");
        subGroupButton2.setCallbackData("subGroupJoin2");
        //Строка с подгруппами
        List<InlineKeyboardButton> subGroupRow = new ArrayList<>();
        subGroupRow.add(subGroupButton1);
        subGroupRow.add(subGroupButton2);
        //Клавиатура с подгуппами (да-да, из одной строки)
        List<List<InlineKeyboardButton>> subGroupKeyboard = new ArrayList<>();
        subGroupKeyboard.add(subGroupRow);
        //Разметка клавиатуры (финалочка короче)
        InlineKeyboardMarkup subGroupKeyboardMarkup = new InlineKeyboardMarkup();
        subGroupKeyboardMarkup.setKeyboard(subGroupKeyboard);
        //Прикрепляем клавиатуру к сообщению
        startMessage.setReplyMarkup(subGroupKeyboardMarkup);
        return startMessage;
    }

    public static SendPhoto menuMessage(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setCaption("Меню (не придумал пока)");
        sendPhoto.setPhoto(new InputFile(new File(menu_photo)));
        sendPhoto.setChatId(chatId);
        KeyboardManager keyboardManager = new KeyboardManager();
        keyboardManager.setButtons(sendPhoto);
        return sendPhoto;
    }

    public static SendPhoto registerPhoto(CallbackQuery callbackQuery) {
        SendPhoto registerPhoto = new SendPhoto();

        User user = callbackQuery.getFrom();
        Long chatId = callbackQuery.getMessage().getChatId();

        registerPhoto.setChatId(chatId);

        if (PrimatManager.getPrimat(user.getUserName()) == null) {
            String data = callbackQuery.getData();

            Primat primat = new Primat(user, data.contains("1") ? 1 : 2, "Primat");
            PrimatManager.addPrimat(primat);
            registerPhoto.setPhoto(new InputFile(new File(registerAccepted_photo)));
            registerPhoto.setCaption("Добро пожаловать в отряд приматов");

            PrimatManager.saveMakara();

        } else {
            registerPhoto.setPhoto(new InputFile(new File(registerAborted_photo)));
            registerPhoto.setCaption("Без обид, но ты и так уже примат\n(Сменить ориентацию можно в Настройках)");
        }
        return registerPhoto;
    }

    public static SendMessage notCommandMessage(Long chatId) {
        SendMessage notCommandMessage = new SendMessage();
        notCommandMessage.setChatId(chatId);
        notCommandMessage.setText("Неизвестная команда");
        return notCommandMessage;
    }

    public static SendMessage weekMessage(Long chatId, boolean isOdd) {
        SendMessage weekMessage = new SendMessage();
        weekMessage.setChatId(chatId);
        weekMessage.setText(isOdd ? "Числитель" : "Знаменатель");
        return weekMessage;
    }

    public static SendMessage fuckMessageToSender(Primat fromPrimat, Primat toPrimat) {
        SendMessage fuckMessageToSender = new SendMessage();
        String primatLink = "[" + toPrimat.getName() + "](t.me/" + toPrimat.getUsername()+")";
        fuckMessageToSender.setChatId(fromPrimat.getChatId());
        fuckMessageToSender.setText(primatLink + " послан\uD83C\uDF89");
        fuckMessageToSender.enableMarkdown(true);
        fuckMessageToSender.disableWebPagePreview();
        return fuckMessageToSender;
    }

    public static SendMessage fuckMessageToReceiver(Primat fromPrimat, Primat toPrimat) {
        SendMessage fuckMessageToReceiver = new SendMessage();
        String primatLink = "[" + fromPrimat.getName() + "](t.me/" + fromPrimat.getUsername()+")";
        fuckMessageToReceiver.setChatId(toPrimat.getChatId());
        fuckMessageToReceiver.setText(primatLink + " послал(-а) тебя нахуй!\uD83C\uDF89\nОтветишь или терпила?");
        fuckMessageToReceiver.enableMarkdown(true);
        fuckMessageToReceiver.disableWebPagePreview();

        InlineKeyboardMarkup fuckKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton fuckReplyButton = new InlineKeyboardButton();
        fuckReplyButton.setText("Послать в ответ");
        fuckReplyButton.setCallbackData("FuckReply " + fromPrimat.getUsername());

        List<InlineKeyboardButton> fuckRow = new ArrayList<>();
        fuckRow.add(fuckReplyButton);

        List<List<InlineKeyboardButton>> fuckKeyboard = new ArrayList<>();
        fuckKeyboard.add(fuckRow);

        fuckKeyboardMarkup.setKeyboard(fuckKeyboard);

        fuckMessageToReceiver.setReplyMarkup(fuckKeyboardMarkup);
        return fuckMessageToReceiver;
    }

    public static EditMessageText fuckReplyToSender(Primat fromPrimat, Primat toPrimat) {
        EditMessageText editMessage = new EditMessageText();
        String primatLink = "[" + toPrimat.getName() + "](t.me/" + toPrimat.getUsername()+")";
        editMessage.setText("Ответка для "+ primatLink +" полетела");
        editMessage.setChatId(fromPrimat.getChatId());
        editMessage.disableWebPagePreview();
        editMessage.enableMarkdown(true);
        return editMessage;
    }

    public static SendMessage fuckReplyToReceiver(Primat fromPrimat, Primat toPrimat) {
        SendMessage fuckMessageToReceiver = new SendMessage();
        fuckMessageToReceiver.enableMarkdown(true);
        fuckMessageToReceiver.disableWebPagePreview();
        String primatLink = "[" + fromPrimat.getName() + "](t.me/" + fromPrimat.getUsername()+")";
        fuckMessageToReceiver.setText(primatLink + " возвращает тебе билет на пешее эротическое, наслаждайся!");
        fuckMessageToReceiver.setChatId(toPrimat.getChatId());
        return fuckMessageToReceiver;
    }
}
