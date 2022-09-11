package Command;

import Manager.PrimatManager;
import NotificationKeyboard.NotificationKeyboard;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class StartCommand extends Command{
    private static final String primat_photo = "src/main/resources/images/Primat.jpg";
    public StartCommand(String commandText) {
        super(commandText);
    }

    @Override
    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setPhoto(new InputFile(new File(primat_photo)));
        sendPhoto.setChatId(update.getMessage().getChatId());
        NotificationKeyboard nc = new NotificationKeyboard();
        nc.setButtons(sendPhoto);
        try {
            bot.execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        PrimatManager.startMessage(bot, update.getMessage().getChatId());
    }
}
