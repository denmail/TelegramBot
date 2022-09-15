package ru.primath.Manager;

import ru.primath.Objects.Couple;
import ru.primath.Objects.Primat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private static final String primat_photo = "AgACAgIAAxkBAAIR4GMiUFNKMPCocUw_SNJ1kG-PrLGzAALhwTEbSpURSWcKy9YLiGcFAQADAgADeQADKQQ";
    private static final String menu_photo = "AgACAgIAAxkBAAIR3GMiUEzsI8AduKyttnnV9NwEppbNAALfwTEbSpURSaNKTQeawxVzAQADAgADeQADKQQ";
    private static final String registerAccepted_photo = "AgACAgIAAxkBAAIR3mMiUFARoG6OcB30Khj5H0YbAnpTAALgwTEbSpURSRfk0bXl52J2AQADAgADeAADKQQ";
    private static final String registerAborted_photo = "AgACAgIAAxkBAAIR4mMiUFa5hnMRbhA5ZPFNxFidQrIiAALiwTEbSpURSW8nql5n_cmaAQADAgADeQADKQQ";
    private static final String profile_photo = "AgACAgIAAxkBAAIS22Mi6JlEnW5cFDDDjYow-s65vIkmAAKnvzEbSpUZSVxJOHwuoOZTAQADAgADeQADKQQ";


    private static final String em_monkey = "\uD83D\uDC35";
    private static final String em_party = "\uD83C\uDF89";
    public static SendPhoto startPhoto(Long chatId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(primat_photo));
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
        sendPhoto.setPhoto(new InputFile(menu_photo));
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

            Primat primat = new Primat(user, (byte)(data.contains("1") ? 1 : 2), "Primat");
            DBManager.primatToDB(primat.getSubGroup(), primat.getUsername(), primat.getName(), primat.getChatId(), primat.getRole());
            PrimatManager.addPrimat(primat);
            registerPhoto.setPhoto(new InputFile(registerAccepted_photo));
            registerPhoto.setCaption("Добро пожаловать в отряд приматов");

        } else {
            registerPhoto.setPhoto(new InputFile(registerAborted_photo));
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
        fuckMessageToSender.setText(primatLink + " послан(-а)" + em_party);
        fuckMessageToSender.enableMarkdown(true);
        fuckMessageToSender.disableWebPagePreview();
        return fuckMessageToSender;
    }

    public static SendMessage fuckMessageToReceiver(Primat fromPrimat, Primat toPrimat) {
        SendMessage fuckMessageToReceiver = new SendMessage();
        String primatLink = "[" + fromPrimat.getName() + "](t.me/" + fromPrimat.getUsername()+")";
        fuckMessageToReceiver.setChatId(toPrimat.getChatId());
        fuckMessageToReceiver.setText(primatLink + " послал(-а) тебя нахуй!" + em_party + "\nОтветишь или терпила?");
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

    public static SendMessage nextCoupleMessage(Long chatId, Couple couple) {
        SendMessage nextCoupleMessage = new SendMessage();
        nextCoupleMessage.setChatId(chatId);
        nextCoupleMessage.setText(String.format("%s в %s, не потеряйся!", couple.getTitle(), couple.getAud()));
        return nextCoupleMessage;
    }

    public static SendMessage chooseScheduleMessage(Long chatId) {
        SendMessage chooseScheduleMessage = new SendMessage();
        chooseScheduleMessage.setText("Расписание: ");
        chooseScheduleMessage.setChatId(chatId);
        KeyboardManager km = new KeyboardManager();
        km.chooseSchedule(chooseScheduleMessage);
        return chooseScheduleMessage;
    }

    public static SendMessage scheduleMessage(Primat primat, int day, boolean isWeek) {
        String[] days = {"Понедельник", "Вторник", "Среду", "Четверг", "Пятницу"};
        StringBuilder text = new StringBuilder(String.format("Расписание на %s\n\n", days[day - 1]));

        ArrayList<Couple> couples;
        if (isWeek) {
            couples = ScheduleManager.getCouplesForDay(primat, day);
        } else {
            couples = ScheduleManager.getSmartCouplesForDay(primat, day);
        }
        for (Couple couple: couples) {
            if (couple.getTitle().equals("none")) {
                text.append("--\n\n");
            } else {
                text.append(String.format("%s в %s\n\n", couple.getTitle(), couple.getAud()));
            }
        }

        SendMessage dayScheduleMessage = new SendMessage();
        dayScheduleMessage.setText(text.toString());
        dayScheduleMessage.setChatId(primat.getChatId());
        return dayScheduleMessage;
    }

    public static SendMessage usefulMessage(Long chatId) {
        SendMessage usefulMessage = new SendMessage();
        usefulMessage.setChatId(chatId);
        usefulMessage.setText("Действительно считаешь, что здесь могло что-то быть?");
        return usefulMessage;
    }

    public static SendPhoto profilePhoto(Long chatId) {
        SendPhoto profilePhoto = new SendPhoto();
        profilePhoto.setChatId(chatId);
        profilePhoto.setPhoto(new InputFile(profile_photo));
        return profilePhoto;
    }

    public static SendMessage profileMessage(Primat primat) {
        SendMessage profileMessage = new SendMessage();
        profileMessage.setChatId(primat.getChatId());
        profileMessage.disableWebPagePreview();

        String primatLink = "[" + primat.getName() + "](t.me/" + primat.getUsername()+")";
        profileMessage.enableMarkdown(true);
        profileMessage.setText(em_monkey + "ПРОФИЛЬ" + em_monkey +
                "\n\nИмя: " + primatLink +
                "\nПодгруппа: " + primat.getSubGroup());
        return profileMessage;
    }
}
