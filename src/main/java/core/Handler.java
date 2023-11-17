package core;

/**
 * класс обрабатывает запрос от пользователя и возвращает ответ на него
 */
public class Handler {

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

    public static Response handle(Reqest reqest){

        switch (reqest.getReqest()){
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

        return new Response("");
    }
}
