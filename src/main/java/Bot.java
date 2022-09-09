import Manager.CommandManager;
import Command.StartCommand;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public final class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    CommandManager cm = new CommandManager();
    NotificationKeyboard nk = new NotificationKeyboard();
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
                if (update.getCallbackQuery().getData().contains("subGroupJoin")) {
                    execute(new SendMessage("" + update.getCallbackQuery().getMessage().getChatId(),"EBALA"));
                    PrimatManager.registerPrimat(this, update.getCallbackQuery().getMessage().getChatId(),update.getCallbackQuery().getData(), update.getMessage().getForwardFrom());
                    return;
                }
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                outMessage.setText(update.getCallbackQuery().getMessage().getText());

                //Указываем текст сообщения
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
                ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(inMessage.getChatId());
                sendMessage.enableMarkdown(true);
                sendMessage.setText("Заебись");
                nk.setButtons(sendMessage);
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
