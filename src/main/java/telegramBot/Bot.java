package telegramBot;


import core.Handler;
import core.Request;
import core.Response;
import lombok.Data;
import model.H2JDBCUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        Request request = new Request(text, chatId, chat);
        Response response = null;
        try {
            response = Handler.handle(request);
        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }

        SendMessage sendMessage = new SendMessage(chatId.toString(), response.getResponse());
        sendMessage.enableHtml(true);


        if (response.getButtonsFlag()){
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

    @Override
    public String getBotUsername() {
        return "individual_planner_bot";
    }

    @Override
    public String getBotToken(){
        return "6564881301:AAHgSDGBgRoLdrnt7Bl-1LZWmBfW5F9hZzE";
    }

    public Bot(){
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

