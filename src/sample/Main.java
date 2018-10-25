package sample;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramHandler(new NewGame()));
        } catch (TelegramApiException e) {
            System.out.println("error");
        }

    }

    public static int GetChoosePlayer(int min, int max, Scanner reader, String Question)
    {
        System.out.println(Question + "(min: " + min + ", max: "+ max + ")");
        int choose = reader.nextInt();
        if (choose < min || choose > max)
        {
            System.out.println("Неверный выбор. Попробуйте еще раз");
            return GetChoosePlayer(min, max, reader, Question);
        }
        return choose;
    }
}
