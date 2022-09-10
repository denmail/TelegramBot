import Manager.CommandManager;
import Command.StartCommand;
import Manager.PrimatManager;
import Manager.ScheduleManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

import java.util.Comparator;
import java.util.List;

public final class Bot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    CommandManager cm = new CommandManager();
    NotificationKeyboard nk = new NotificationKeyboard();
    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        PrimatManager.loadMakara();
        ScheduleManager sm = new ScheduleManager();
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
                    PrimatManager.registerPrimat(this, update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData(), update.getCallbackQuery().getFrom());
                    return;
                }

                /*if (update.getCallbackQuery().getData().contains("FuckReply")) {
                    try {
                        cm.findCommand(inMessage.getText(), this, inMessage.getChatId().toString());
                        System.out.println("1");
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }*/
                SendMessage outMessage = new SendMessage();
                //Указываем в какой чат будем отправлять сообщение
                //(в тот же чат, откуда пришло входящее сообщение)
                outMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                outMessage.setText("Ok!");

                //Указываем текст сообщения
                execute(outMessage);


            } else if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                try {
                    cm.findCommand(inMessage.getText(), this, update);
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
            else if (update.hasMessage() && update.getMessage().hasPhoto()) {
                // Message contains photo
                // Set variables
                long chat_id = update.getMessage().getChatId();

                // Array with photo objects with different sizes
                // We will get the biggest photo from that array
                List<PhotoSize> photos = update.getMessage().getPhoto();
                // Know file_id
                String f_id = photos.stream()
                        .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).getFileId();
                // Know photo width
                int f_width = photos.stream()
                        .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).getWidth();
                // Know photo height
                int f_height = photos.stream()
                        .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                        .findFirst()
                        .orElse(null).getHeight();
                // Set photo caption
                String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
                SendPhoto msg = new SendPhoto();
                msg.setChatId(chat_id);
                msg.setPhoto(new InputFile().setMedia(f_id));
                msg.setCaption(caption);
                try {
                    execute(msg); // Call method to send the photo with caption
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
