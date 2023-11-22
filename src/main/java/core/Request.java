package core;

import org.telegram.telegrambots.meta.api.objects.Chat;

/**
 * класс принимает входящий запрос от пользователя и хранит в унифицированном формате
 */
public class Request {
    private final String requestText;
    private final Long chatId;
    private final Chat chat;

    public Request(String text, Long chatId, Chat chat) {
        this.requestText = text;
        this.chatId = chatId;
        this.chat = chat;
    }

    public String getRequestText() {
        return requestText;
    }
    public Long getChatId() {
        return chatId;
    }
    public Chat getChat() {
        return chat;
    }
}
