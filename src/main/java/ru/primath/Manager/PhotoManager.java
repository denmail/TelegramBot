package ru.primath.Manager;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;

public class PhotoManager {
    public static void returnPhoto(AbsSender bot, Update update) throws TelegramApiException {
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
        bot.execute(msg); // Call method to send the photo with caption
    }
}
