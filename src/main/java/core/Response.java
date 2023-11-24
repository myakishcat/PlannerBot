package core;

/**
 * класс хранит обработанный ответ на запрос пользователя
 */
public class Response {

    private final String responseText;
    private final boolean isButtonsShown;

    public Response(String text, Boolean flag) {
        this.responseText = text;
        this.isButtonsShown = flag;
    }

    public String getResponse() {
        return responseText;
    }

    public Boolean getButtonsShown() {
        return isButtonsShown;
    }
}
