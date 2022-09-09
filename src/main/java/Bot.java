<<<<<<< HEAD
=======
import Test.Test;
>>>>>>> ce4e0e1633618a3d3e3f9932e39c7da9520ec018
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

<<<<<<< HEAD
=======
import java.util.HashMap;

>>>>>>> ce4e0e1633618a3d3e3f9932e39c7da9520ec018
public final class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            NotificationKeyboard notificationKeyboard = new NotificationKeyboard();
            if (update.hasMessage() && update.getMessage().hasText()) {
<<<<<<< HEAD

=======
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                //Создаем исходящее сообщение
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение 
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(inMessage.getChatId());
                //Указываем текст сообщения
                outMessage.setText(inMessage.getText());

                outMessage.setReplyMarkup(Test.getInlineKeyboard());
                //Отправляем сообщение
                execute(outMessage);
            } else if (update.hasCallbackQuery()) {
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                outMessage.setText(update.getCallbackQuery().getMessage().getText());

                //Указываем текст сообщения
                if (update.getCallbackQuery().getData().equals("Button1"))
                    outMessage.setText("1");
                if (update.getCallbackQuery().getData().equals("Button2"))
                    outMessage.setText("2");
                execute(outMessage);
>>>>>>> ce4e0e1633618a3d3e3f9932e39c7da9520ec018
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exeSendPhoto() {

    }

}
