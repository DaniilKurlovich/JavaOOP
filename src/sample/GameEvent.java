package sample;

import java.util.Random;

public class GameEvent {
    // индикатор благоприятности события, то есть плохое если false, иначе хорошее
    private boolean statusEventIsGood;

    private String textEvent;
    private boolean eventIsLive;

    private Enemy enemy;
    private int getMoney;
    private String[] arrayGrades = new String[] {"Супермутант", "Таракан", "Алкоголик", "Гопник", "Просто человек"};
    private String[] arrayNames = new String[] {"Володя", "Геннадий", "Семен", "Иван", "Демид"};

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
            int power = random.nextInt(7) + 1;
            int agility = 10 - power;
            this.enemy = new Enemy(arrayGrades[random.nextInt(this.arrayGrades.length - 1)] + " " + arrayNames[random.nextInt(this.arrayNames.length - 1)],
                    random.nextInt(15) + 2, power, agility);
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
