package core;

import org.telegram.telegrambots.meta.api.objects.Chat;

/**
 * класс принимает входящий запрос от пользователя и хранит в унифицированном формате
 */
public class Reqest {
    private final String reqestText;
    private final Long chatId;
    private final Chat chat;

    public Reqest(String text, Long chatId, Chat chat) {
        this.reqestText = text;
        this.chatId = chatId;
        this.chat = chat;
    }

    public String getReqestText() {
        return reqestText;
    }
    public Long getChatId() {
        return chatId;
    }
    public Chat getChat() {
        return chat;
    }
}
