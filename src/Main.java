import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramHandler.TelegramHandler;

import GameFile.NewGame;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        TelegramHandler telegramHandler = new TelegramHandler();
//        NewGame game = new NewGame(telegramHandler);
//        telegramHandler.setGame(game);
//        try {
//            telegramBotsApi.registerBot(telegramHandler);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
        Scanner reader = new Scanner(System.in);
        NewGame game = new NewGame();
        while (true){
            String input = reader.next();
           System.out.println(game.setRequestFromHandler("1", input));
        }
    }
}