import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//public class Main {
//    public static void main(String[] args) {
//        Bot bot = new Bot();
//        BotApi.registerBot(bot);
//    }
//}

@SpringBootApplication
public class BotApplication {
    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
    }
}