package core;

/**
 * класс принимает входящий запрос от пользователя и хранит в унифицированном формате
 */
public class Reqest {
    private final String reqestText;

    public Reqest(String text) {
        this.reqestText = text;
    }

    public String getReqest() {
        return reqestText;
    }
}
