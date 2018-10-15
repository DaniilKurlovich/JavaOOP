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
        int status = random.nextInt(10);
        if (status <= 5) {
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
            if (player.GetInfo()[0] <= 5)
                player.GetHealing(5 + player.GetInfo()[1]);
            adventure(player, scanner);
        }
    }

    public void adventure(Player player, Scanner scanner){
        while (true){
            throwEvent();
            if (lastGameEvent.isStatusEventIsGood()){
                player.GetHealing(5);
                System.out.println("Святые силы благославили тебя. Здоровье увеличенно на 5!");
            }
            else {
                System.out.println(CalculateChanceWinPlayer(player, lastGameEvent.GetEnemy()));
                System.out.println("Введите /attack чтобы вступить в бой" +
                        " ,иначе /leave");
                String command = scanner.next();
                switch (command){
                    case ("/attack"): {
                        int resultFight = SimulateFight(player, lastGameEvent.GetEnemy());
                        switch (resultFight) {
                            case (0): {
                                System.out.println("Ты оказался сильнее, но повезет ли тебе в следующий раз?");
                                player.GetReward(lastGameEvent.GetEnemy().GetReward(new Random()));
                                break;
                            }
                            case (1):
                                System.out.println("You died stupid cunt!");
                                return;
                        }
                    }
                    case ("/leave"): {
                        break;
                    }
                }
                System.out.println("Продолжить путешествие: /continue, Вернуться домой: /home. \n" +
                        "Осталось здороья: "+ player.GetInfo()[0]);
                command = scanner.next();
                switch (command) {
                    case ("/home"): {
                        break;
                    }
                    case ("/continue"): {
                        continue;
                    }
                }
            }
        }
    }

    public String CalculateChanceWinPlayer(Player player, Enemy enemy){
        int playerNeedAttack = enemy.GetHealthPoint() / player.GetDamage() - enemy.GetHealthPoint() % player.GetDamage();
        int enemyNeedAttack = player.GetInfo()[0] / enemy.GetDamage() - player.GetInfo()[0] % enemy.GetDamage();
        if (playerNeedAttack <= enemyNeedAttack){
            return "Впереди ты видишь какого-то дрищавого лоха: " + enemy.name;
        }
        else {
            return "Внутреннее чувство подсказывает, что домой тебя явно не вернуться: " + enemy.name;
        }
    }

    public int SimulateFight(Player player, Enemy enemy){
        Random random = new Random();
        int AttackFrom = random.nextInt(1);
        while (true) {
            switch (AttackFrom){
                case (0):{
                    double CanAttack = random.nextDouble();
                    if (CanAttack <= player.GetInfo()[2] / 10.0) {
                        if (enemy.SetDamage(player.GetDamage()))
                            return 0;
                    }
                    AttackFrom = (AttackFrom + 1) % 2;
                    break;
                }
                case (1):{
                    double canAttack = random.nextDouble();
                    if (canAttack <= enemy.getAgility() / 10.0) {
                        if (player.SetDamage(enemy.GetDamage()))
                            return 1;
                    }
                    AttackFrom = (AttackFrom + 1) % 2;
                    break;
                }
            }
        }
    }
}

