package telegramBot;


import core.Handler;
import core.Request;
import core.Response;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Bot extends TelegramLongPollingBot {

    final BotConfig config;

    @Override
    public void onUpdateReceived(Update update) {
        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }

        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        Chat chat = update.getMessage().getChat();

        Request request = new Request(text, chatId, chat);
        Response response = Handler.handle(request);

        SendMessage sendMessage = new SendMessage(chatId.toString(), response.getResponse());
        sendMessage.enableHtml(true);


        if (response.getButtonsShown()){
            sendMessage.setReplyMarkup(keyboardButtons());
        }
        try{
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public ReplyKeyboardMarkup keyboardButtons(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("/start");
        row.add("/help");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

//    @Override
//    public String getBotUsername() {
//        return "individual_planner_bot";
//    }
//    @Override
//    public String getBotToken(){
//        return "6564881301:AAHgSDGBgRoLdrnt7Bl-1LZWmBfW5F9hZzE";
//    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken(){
        return config.getBotToken();
    }

    public Bot(BotConfig config){
        this.config = config;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start","начать работу с ботом"));
        listofCommands.add(new BotCommand("/help","справка по работе с ботом"));
        listofCommands.add(new BotCommand("/deadlines","вывод имеющихся заданий [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/schedule","вывод запланированных мероприятий [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/newtask","добавить новую задачу [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/deltask","удалить задачу [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/changetask","изменить детали задания [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/newevent","запланировать новое мероприятие [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/delevent","удалить мероприятие [В РАЗРАБОТКЕ]"));
        listofCommands.add(new BotCommand("/changeevent","изменить детали мероприятия [В РАЗРАБОТКЕ]"));
        try{
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e){
            throw new RuntimeException(e);
        }
    }

}

