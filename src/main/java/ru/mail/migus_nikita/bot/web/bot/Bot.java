package ru.mail.migus_nikita.bot.web.bot;

import ru.mail.migus_nikita.bot.service.CityService;
import ru.mail.migus_nikita.bot.web.exception.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(Bot.class);
    private final CityService cityService;

    @Autowired
    public Bot(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        String info = cityService.getInfo(text);
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText(info);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage(), e);
            throw new Exception(String.format(
                    "Problems on sending a message: \"%s\" to bot with id: %d.", info, chatId), e);
        }
    }

    @Override
    public String getBotUsername() {
        return "OnTravelSolutionBot";
    }

    @Override
    public String getBotToken() {
        return "810497201:AAEMKpeX8xWg2ALkFsAIFnVlpFbRZeernfc";
    }

}
