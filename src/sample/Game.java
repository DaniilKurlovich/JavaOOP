package sample;
import java.lang.reflect.Array;
import java.util.Arrays;
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
        lastGameEvent = new GameEvent(status == 1);
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
                boolean result = SimulateFight(player, lastGameEvent.GetEnemy());
                if (result){
                    System.out.println("В неравном бою " + lastGameEvent.GetEnemy().name + " был убит вашей рукой.");
                    int reaward = lastGameEvent.GetEnemy().GetReward(new Random());
                    System.out.println("Вы заработали " + reaward);
                    player.GetReward(reaward);
                }
                else {
                    System.out.println(lastGameEvent.GetEnemy().name + " убил вас");
                    break;
                }
            }
            int choosePlayer = GetChoosePlayer("Продолжить путешествие: /continue, Вернуться домой: /home. \n" +
                    "Осталось здороья: "+ player.GetHealpoint(), new String[] {"/continue", "/home"}, scanner);
            switch (choosePlayer){
                case (1):{
                    break;
                }
            }
        }
    }

    public boolean SimulateFight(Player player, Enemy enemy) {
        Random random = new Random();
        int playerNeedAttack = (int)Math.ceil(Math.ceil((double)enemy.GetHealthPoint() / player.GetDamage()) * 10.0 / player.GetAgility());
        int enemyNeedAttack = (int)Math.ceil(Math.ceil((double)player.GetHealpoint() / enemy.GetDamage()) * 10.0 / enemy.GetAgility());
        System.out.println(String.format("Player need: {%d}, Enemy need: {%d}", playerNeedAttack, enemyNeedAttack));
        System.out.println(String.format("Enemy: HP={%d},Agility={%d},Power={%d}", enemy.GetHealthPoint(), enemy.GetAgility(), enemy.GetDamage()));
        if (enemyNeedAttack == 0){
            return true;
        }
        double chanceToWin;
        if (playerNeedAttack >= enemyNeedAttack){
            chanceToWin = 0.5 - (double)(playerNeedAttack - enemyNeedAttack) / playerNeedAttack / 2;
        }
        else {
            chanceToWin = 0.5 + (double)(enemyNeedAttack - playerNeedAttack) / enemyNeedAttack / 2;
        }
        System.out.println(chanceToWin);
        return SimulateFight(chanceToWin, random);
    }

    public double GetChance(int countHit, int countAgility){
        return Math.pow((10 - countAgility) / 10.0, countHit);
    }

    public boolean SimulateFight(double playerChanceToWin, Random random){
        boolean result = playerChanceToWin >= random.nextDouble();
        return result;
    }

    public int GetChoosePlayer(String messageForPlayer, String[] arrayCommand, Scanner scanner){
        String command = "";
        while (!Arrays.asList(arrayCommand).contains(command)){
            System.out.println(messageForPlayer);
            command = scanner.next();
        }
        return Arrays.asList(arrayCommand).indexOf(command);
    }
}