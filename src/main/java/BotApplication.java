import model.User;
import model.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//public class Main {
//    public static void main(String[] args) {
//        Bot bot = new Bot();
//        BotApi.registerBot(bot);
//    }
//}

@SpringBootApplication
public class BotApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BotApplication.class, args);
        UserRepository userRepository = context.getBean(UserRepository.class);
    }
}