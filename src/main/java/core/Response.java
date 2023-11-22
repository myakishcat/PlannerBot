package core;

/**
 * класс хранит обработанный ответ на запрос пользователя
 */
public class Response {

    private final String responseText;
    private final Boolean buttonsFlag;

    public Response(String text, Boolean flag) {
        this.responseText = text;
        this.buttonsFlag = flag;
    }

    public String getResponse() {
        return responseText;
    }

    public Boolean getButtonsFlag() {
        return buttonsFlag;
    }
}
