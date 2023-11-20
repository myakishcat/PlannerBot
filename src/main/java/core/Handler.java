package core;

import model.User;
import model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * класс обрабатывает запрос от пользователя и возвращает ответ на него
 */
public class Handler {

    @Autowired
    private static UserRepository userRepository;
    private static final String COMMAND_LIST =
            """
            <b>Список команд бота:</b>
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

    public static Response handle(Reqest reqest) {

        Response response = null;
        switch (reqest.getReqestText()) {
            case "/start" -> {
                response = startCommand(reqest);
            }
//            case "/help" -> helpCommand();
//            case "/deadlines" -> deadlinesCommand();
//            case "/address" -> addressCommand();
//            case "/schedule" -> Command.schedule();
//            case "/newtask" -> newTaskCommand();
//            case "/deltask" -> Command.delTask();
//            case "/changetask" -> Command.changeTask();
//            case "/newevent" -> Command.newEvent();
//            case "/delevent" -> Command.delEvent();
//            case "/changeevent" -> Command.changeEvent();
//            default -> unknownCommand();
        }

        return response;
    }

    private static Response startCommand(Reqest reqest) {
        registerUser(reqest);
        String startMessage =
                """
                Приветственная строка бота
                """;
        return new Response(startMessage);
    }

    private static void registerUser(Reqest reqest) {
        if(userRepository.findById(reqest.getChatId()).isEmpty()){
            User user = new User(reqest.getChatId(), new Timestamp(System.currentTimeMillis()));
            user.setFirstName(reqest.getChat().getFirstName());
            user.setLastName(reqest.getChat().getLastName());
            user.setUserName(reqest.getChat().getUserName());

            userRepository.save(user);
        }
    }
}
