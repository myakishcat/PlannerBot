package core;

/**
 * класс хранит обработанный ответ на запрос пользователя
 */
public class Response {

    private final String responseText;

    public Response(String text) {
        this.responseText = text;
    }

    public String getResponse() {
        return responseText;
    }
}
