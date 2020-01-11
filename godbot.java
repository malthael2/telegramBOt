import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;

public class godbot extends TelegramLongPollingBot {

    public void onUpdateReceived(final Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) { // si el mensaje es de tipo texto
            SendMessage answer = new SendMessage();
            final Long chatId = update.getMessage().getChatId();
            final String messageReceived = update.getMessage().getText();
            if (messageReceived.equals("/start")) {
                String aux = "Me despertaste";
                answer.setChatId(chatId).setText(aux);
            } else if (messageReceived.equals("/ban")) {
                String ans = " a quien queres banear?";
                answer.setChatId(chatId).setText(ans);
                 }
            else {
                answer.setChatId((chatId)).setText(update.getMessage().getText());
            }
            try {
                execute(answer);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }



        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
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
            SendPhoto msg = new SendPhoto()
                    .setChatId(chat_id)
                    .setPhoto(f_id)
                    .setCaption(caption);
            try {
                execute(msg); // Call method to send the photo with caption
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }



    public String getBotUsername() {
        return "GoddessOfChaos";
    }

    public String getBotToken() {
        return "1050532622:AAFyw7jHVr9AssoacqRoA5397sqpIRUhG6c";
    }


}
