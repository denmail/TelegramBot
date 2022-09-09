import Manager.CommandManager;
import Command.StartCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public final class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    CommandManager cm = new CommandManager();
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
            //проверяем есть ли сообщение и текстовое ли оно
            if (update.hasCallbackQuery()) {
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                outMessage.setText(update.getCallbackQuery().getMessage().getText());

                //Указываем текст сообщения
                if (update.getCallbackQuery().getData().equals("subGroup1"))
                    outMessage.setText("Ok!");
                if (update.getCallbackQuery().getData().equals("subGroup2"))
                    outMessage.setText("Ok!");
                execute(outMessage);


            } else if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                try {
                    cm.findCommand(inMessage.getText(), this, inMessage.getChatId().toString());
                    System.out.println("1");
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void exeSendPhoto() {

    }

}
