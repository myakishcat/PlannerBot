package telegramBot;

import core.Handler;
import core.Reqest;
import core.Response;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
@EnableScheduling
@Data
@PropertySource("application.properties")
public class Bot extends TelegramLongPollingBot {

//    @Value("${bot.name}")
//    String botName;
//    @Value("${bot.token}")
//    String botToken;

    @Override
    public void onUpdateReceived(Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        Chat chat = update.getMessage().getChat();

        Reqest reqest = new Reqest(text, chatId, chat);
        Response response = Handler.handle(reqest);

        SendMessage sendMessage = new SendMessage(chatId.toString(), response.getResponse());
        try{
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "individual_planner_bot";
    }

    @Override
    public String getBotToken(){
        return "6564881301:AAHgSDGBgRoLdrnt7Bl-1LZWmBfW5F9hZzE";
    }
}

