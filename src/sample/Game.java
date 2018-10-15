package sample;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;


public class Game {
    private enum Location {Adventure, Camp}

    private Player player;
    private Location location;
    private GameEvent lastGameEvent;

    public Game(Player player) {
        this.player = player;
        this.location = Location.Camp;
    }

    public void throwEvent() {
        Random random = new Random();
        //Генерируется число, которое будет определять саму вероятность
        int status = random.nextInt(5);
        System.out.println(status);
        if (status == 0) {
            lastGameEvent = new GameEvent(true);
        } else {
            this.lastGameEvent = new GameEvent(false);
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Началась игра, введите код-слово /adventure чтобы оптправиться в незабываемое " +
                    " удивительное приключение под названием жизнь");
            location = Location.Adventure;
            String stop_word = scanner.next();
            throwEvent();
            if (lastGameEvent.isStatusEventIsGood()) {
                System.out.println("Тебе повезло, вали отсюда");
            } else {
                System.out.println(lastGameEvent.getTextEvent() + "/n" + "Введите /attack чтобы вступить в бой" +
                        " ,иначе /leave");
                String command = scanner.next();
                if (command.equals("/attack")) {
                    System.out.println("Вы нанесли " + player.GetDamage());
                    this.lastGameEvent.setEnemyDamage(player.GetDamage());
                    if (this.lastGameEvent.getEnemyHealthPoint() < 0) {
                        System.out.println("Враг повержен");
                    }
                    player.SetDamage(this.lastGameEvent.getEnemyDamage());
                    if (player.GetInfo()[0] < 0) {
                        System.out.println("You died, Bithes");
                    }
                    //break;
                }
            }
        }


    }
}

