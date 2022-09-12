package Objects;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class Menu {
    private static final String menu_photo = "src/main/resources/images/Menu.jpg";

    public static SendPhoto sendMenu() {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setCaption("Меню (не придумал пока)");
        sendPhoto.setPhoto(new InputFile(new File(menu_photo)));
        return sendPhoto;
    }

    public static SendMessage sendFeedbackPrompt(Primat primat) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Введите ваше сообщение:");
        sendMessage.setChatId(primat.getChatId());
        primat.setFeedbackMessage(true);
        return  sendMessage;
    }
}
