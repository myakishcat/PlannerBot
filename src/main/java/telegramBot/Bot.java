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
//                case "/address" -> addressCommand(chatID);
//                case "/schedule" -> Command.schedule();
//                case "/newtask" -> newTaskCommand(chatID);
//                case "/deltask" -> Command.delTask();
//                case "/changetask" -> Command.changeTask();
//                case "/newevent" -> Command.newEvent();
//                case "/delevent" -> Command.delEvent();
//                case "/changeevent" -> Command.changeEvent();
            default -> unknownCommand(chatID);
        }
    }


    private static final String COMMAND_LIST = """
            <b>Список команд бота:</b>\s
            /help - <i>вывод справки по командам</i>
            /deadlines - <i>вывод имеющихся заданий</i>
            /schedule - <i>вывод запланированных мероприятий</i>
            /newtask - <i>добавить новую задачу</i>
            /deltask - <i>удалить задачу</i>
            /changetask - <i>изменить детали задания</i>
            /newevent - <i>запланировать новое мероприятие</i>
            /delevent - <i>удалить мероприятие</i>
            /changeevent - <i>изменить детали мероприятия</i>
            """;

    public void helpCommand(String chatID){
        SendMessage sendMessage = new SendMessage(chatID, COMMAND_LIST);
        sendMessage.enableHtml(true);
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

