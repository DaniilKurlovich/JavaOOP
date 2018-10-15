package sample;

public class GameEvent {
    // индикатор благоприятности события, то есть плохое если false, иначе хорошее
    private boolean statusEventIsGood;

    private String textEvent;
    private boolean eventIsLive;

    private Enemy enemy;
    private int getMoney;

    public GameEvent(boolean statusEventIsGood){
        if (statusEventIsGood) {
            this.statusEventIsGood = true;
            this.eventIsLive = true;
            this.textEvent = "Произошло благоприятное событие";
        }
        else{
            this.statusEventIsGood = false;
            this.eventIsLive = true;
            this.textEvent = "Произошло чудовищное непоправимое событие";
            this.enemy = new Enemy("Андрей - босс", 100, 100, 100);
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
}
