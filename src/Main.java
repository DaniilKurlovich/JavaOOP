import Creatures.Player;
import GameFile.NewGame;
import Location.Location;
import Location.Camp;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import telegramHandler.TelegramHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        ApiContextInitializer.init();
//        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
//        try {
//            telegramBotsApi.registerBot(new TelegramHandler(new GameFile.NewGame()));
//        } catch (TelegramApiException e) {
//            System.out.println(e);
//        }
        Scanner reader = new Scanner(System.in);
        NewGame game = new NewGame();
        while (true){
            String input = reader.next();
            System.out.println(game.setRequestFromHandler("1", input));
        }
    }
}