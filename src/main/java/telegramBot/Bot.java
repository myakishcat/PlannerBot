package telegramBot;

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

        String chatID = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        switch (text){
            case "/help" -> helpCommand(chatID);
            case "/deadlines" -> deadlinesCommand(chatID);
//                case "/schedule" -> Command.schedule();
//                case "/newtask" -> Command.newTask();
//                case "/deltask" -> Command.delTask();
//                case "/changetask" -> Command.changeTask();
//                case "/newevent" -> Command.newEvent();
//                case "/delevent" -> Command.delEvent();
//                case "/changeevent" -> Command.changeEvent();
            default -> unknownCommand(chatID);
        }
    }

    public void helpCommand(String chatID){
        SendMessage sendMessage = new SendMessage(chatID, "Справка о командах");
        try{
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void deadlinesCommand(String chatID){
        SendMessage sendMessage = new SendMessage(chatID, "Все задачи: ...");
        try{
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void unknownCommand(String chatID){
        SendMessage sendMessage = new SendMessage(chatID, "Я не знаю такой команды");
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

