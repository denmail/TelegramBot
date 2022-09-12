package Command;

import NotificationKeyboard.NotificationKeyboard;
import Objects.Menu;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MenuCommand extends Command {
    public MenuCommand(String commandText) {
        super(commandText);
    }


    public void doCommand(AbsSender bot, Update update) throws TelegramApiException {
        SendPhoto sendMenu = Menu.sendMenu();
        sendMenu.setChatId(update.getMessage().getChatId());
        NotificationKeyboard nc = new NotificationKeyboard();
        nc.setButtons(sendMenu);
        bot.execute(sendMenu);
    }
}
