package sample;

import java.util.Random;

public class GameEvent {
    // индикатор благоприятности события, то есть плохое если false, иначе хорошее
    private boolean statusEventIsGood;

    private String textEvent;
    private boolean eventIsLive;

    private Enemy enemy;
    private int getMoney;
    private Enemy[] massiveEnemy = new Enemy[] {new Enemy("Василий", 1, 3, 1),
            new Enemy("Петр", 100, 100, 0),
            new Enemy("Роджер", 100, 3, 1),
            new Enemy("Ростислав", 7, 3, 7),
            new Enemy("Евгений", 1, 1000, 0),
            new Enemy("Иван", 25, 1, 10)};

    public GameEvent(boolean statusEventIsGood){
        if (statusEventIsGood) {
            this.statusEventIsGood = true;
            this.eventIsLive = true;
            this.textEvent = "Произошло благоприятное событие";
        }
        else{
            Random random = new Random();
            this.statusEventIsGood = false;
            this.eventIsLive = true;
            this.textEvent = "Произошло чудовищное непоправимое событие";
            this.enemy = massiveEnemy[random.nextInt(massiveEnemy.length)];
        }

    }

    public boolean isStatusEventIsGood(){
        return statusEventIsGood;
    }

    public String getTextEvent(){
        return this.textEvent;
    }

    public void setEnemyDamage(int attack){
        enemy.SetDamage(attack);
    }

    public int getEnemyDamage(){
        return enemy.GetDamage();
    }

    public int getEnemyHealthPoint(){
        return enemy.GetHealthPoint();
    }

    public Enemy GetEnemy() {return this.enemy; }
}
