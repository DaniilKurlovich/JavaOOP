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
            telegramBotsApi.registerBot(new TelegramHandler());
        } catch (TelegramApiException e) {
            System.out.println("error");
        }
        System.out.println("Здравствуй, игрок! " +
                "Для того чтобы начать увлекательное путешествие тебе необходимо распределить очки навыков.\n" +
                "Тебе доступно 10 очков для улучшения своего персонажа. Параметров доступных для улучшения 2.\n" +
                "Сила влияет на количество нанесенного урона и кол-во здоровья, а ловкость на скорость и маневренность!\n");
        int Power = GetChoosePlayer(0, 10, reader, "Введите значение силы");
        int Agility = GetChoosePlayer(0, 10-Power, reader, "Введите желаемое значение ловкости");
        Player player = new Player("test", Power, Agility);

//        Game game = new Game(player);
//        game.startGame();
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
