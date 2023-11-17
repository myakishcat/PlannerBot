import telegramBot.Bot;
import telegramBot.BotApi;

public class Main {
    public static void main(String[] args) {
        Bot bot = new Bot();
        BotApi.registerBot(bot);
    }
}