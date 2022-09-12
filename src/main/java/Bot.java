import Command.FeedbackCommand;
import Command.GoFuckYourselfCommand;
import Manager.*;
import Objects.Primat;
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
                String queryData = update.getCallbackQuery().getData();
                InlineManager.checkCallbackQueryData(queryData, this, update);


            } else if (update.hasMessage() && update.getMessage().hasText()) {
                //Извлекаем объект входящего сообщения
                Message inMessage = update.getMessage();
                Primat checkPrimat = PrimatManager.getPrimat(update.getMessage().getFrom().getUserName());
                if (checkPrimat != null) {
                    if (checkPrimat.isFeedbackMessage()) {
                        checkPrimat.setFeedbackMessage(false);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(checkPrimat.getChatId());
                        sendMessage.setText("Дублирую сообщение:\n\n" + inMessage.getText());
                        KeyboardManager nk = new KeyboardManager();
                        nk.setButtons(sendMessage);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
                try {
                    cm.findCommand(inMessage.getText(), this, update);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            /*else if (update.hasMessage() && update.getMessage().hasPhoto()) {
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
            } */
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
