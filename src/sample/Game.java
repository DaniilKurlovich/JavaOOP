package sample;
import java.awt.*;
import java.util.Random;


public class Game {
    private enum Location {Adventure, Camp}

    private Player player;
    private Location location;
    private GameEvent lastGameEvent;

    public Game(Player player){
        this.player = player;
        this.location = Location.Camp;
    }

    public GameEvent throwEvent(){
        final Random random = new Random();
        //Генерируется число, которое будет определять саму вероятность
        int status = random.nextInt(5);
        if (status == 0){
            lastGameEvent.generateGoodEvent();
        }
        else
            lastGameEvent.generateBadEvent();
        return lastGameEvent;
    }
}
