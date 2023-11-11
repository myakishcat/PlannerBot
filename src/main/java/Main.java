import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import telegramBot.Bot;

public class Main {


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Bot());

        while (true){
            System.out.println("Чем могу помочь?");
            String answer = Utils.getString();

            switch (answer){
                case "/help" -> System.out.println("Справка о командах");
                case "/deadlines" -> System.out.println("Все задачи: ...");
//                case "/schedule" -> Command.schedule();
//                case "/newtask" -> Command.newTask();
//                case "/deltask" -> Command.delTask();
//                case "/changetask" -> Command.changeTask();
//                case "/newevent" -> Command.newEvent();
//                case "/delevent" -> Command.delEvent();
//                case "/changeevent" -> Command.changeEvent();
                default -> System.out.println("Я не знаю такой команды");
            }

        }
    }
}