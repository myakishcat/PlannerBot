package core;

import model.H2Server;
import model.H2Create;


import java.sql.SQLException;

/**
 * класс обрабатывает запрос от пользователя и возвращает ответ на него
 */
public class Handler {

    public static Response handle(Request request) throws SQLException {

        Response response = null;
        switch (request.getRequestText()) {
            case "/start" -> {
                response = startCommand(request);
            }
            case "/help" -> {
                response = helpCommand(request);
            }
//            case "/deadlines" -> deadlinesCommand();
//            case "/address" -> addressCommand();
//            case "/schedule" -> Command.schedule();
//            case "/newtask" -> newTaskCommand();
//            case "/deltask" -> Command.delTask();
//            case "/changetask" -> Command.changeTask();
//            case "/newevent" -> Command.newEvent();
//            case "/delevent" -> Command.delEvent();
//            case "/changeevent" -> Command.changeEvent();
            default -> {
                response = unknownCommand(request);
            }
        }

        return response;
    }

    private static Response startCommand (Request request) throws SQLException {
        H2Server.main();
        H2Create.main(SqlQueries.createTableSQL);
        String startMessage =
                """
                <b>Привет! Я - бот Планер.</b>
                Я могу записывать ваши задачи и мероприятия, а потом напоминать о них.
                Список всех доступных команд перечислен в Меню (слева от поля ввода) и по команде /help.
                """;
        return new Response(startMessage, false);
    }

    private static Response helpCommand(Request request) throws SQLException {
        H2Create.main(SqlQueries.createTableSQL);
        String startMessage =
                """
                <b>Бот-планер предназначен для планирования имеющихся у вас задач и мероприятий</b>
                Все доступные команды появились на кнопках под клавиатурой.
                <i>По имеющимся багам писать авторам @Manon_Danon, @dme7L</i>
                """;
        return new Response(startMessage, true);
    }

    private static Response unknownCommand(Request request) {
        String defaultMessage =
                """
                <b>Я не знаю такой команды.</b> Доступные команды перечислены в Меню или по команде /help
                """;
        return new Response(defaultMessage, false);
    }

//    private static void registerUser(Request request) {
//
//        if(userRepository.findById(request.getChatId()).isEmpty()){
//            User user = new User(request.getChatId(), new Timestamp(System.currentTimeMillis()));
//            user.setFirstName(request.getChat().getFirstName());
//            user.setLastName(request.getChat().getLastName());
//            user.setUserName(request.getChat().getUserName());
//
//            userRepository.save(user);
//        }
//    }
}
