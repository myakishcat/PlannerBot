package telegramBot;

import core.Handler;
import core.Reqest;
import core.Response;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }

        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        Reqest reqest = new Reqest(text);
        Response response = Handler.handle(reqest);

        SendMessage sendMessage = new SendMessage(chatId, response.getResponse());
        try{
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    //"<a href=\\'https://ya.ru/\\'>Яндекс</a>"
//    public void addressCommand(String chatID){
//        SendMessage sendMessage = new SendMessage(chatID,"<a href='https://yandex.ru/maps/?text=Тургенева, 4'>Нету места лучше в мире</a>");
//        sendMessage.enableHtml(true);
//        try{
//            this.execute(sendMessage);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public String getBotUsername() {
        return "${bot.name}";
    }

    @Override
    public String getBotToken(){
        return "${bot.token}";
    }
}

