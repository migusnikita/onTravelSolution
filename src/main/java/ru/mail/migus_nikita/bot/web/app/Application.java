package ru.mail.migus_nikita.bot.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication(
        scanBasePackages = "ru.mail.migus_nikita.bot"
)
@EntityScan(basePackages = "ru.mail.migus_nikita.bot.repository.model")
public class Application {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Application.class, args);
    }

}
