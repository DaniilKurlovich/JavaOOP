package sample;

import java.util.Random;

public class NewGameEvent {
    private Player player;
    private boolean eventIsProcessing = false;
    private int healthBoost;
    private Enemy enemy;
    private int gold;

    public NewGameEvent(Player player, String nameEnemy, boolean statusEventIsGood){
        this.player = player;
        this.eventIsProcessing = false;
        if (statusEventIsGood){
            this.healthBoost = 5;
            this.gold = 5;
        }
        else {
            Random random = new Random();
            int HP= random.nextInt(15) + 2;
            int power = random.nextInt(7) + 1;
            int agility = 10 - power;
            this.enemy = new Enemy(nameEnemy, HP, power, agility);
            this.gold = random.nextInt(40) + 10;
        }
        this.eventIsProcessing = false;
    }

    public int GetResultEvent(){
        if (this.healthBoost == 0) {
            this.eventIsProcessing = true;
            Fight();
            this.eventIsProcessing = false;
        }
        return this.healthBoost;
    }

    private void Fight(){
        Random random = new Random();
//        double chanceToWin = CalculateChanceForWin(player, enemy);
        int numberOfAttacking = random.nextInt(1);
        while (this.player.GetHealpoint() > -this.healthBoost && this.enemy.IsAlive()){
            switch (numberOfAttacking){
                case(0):{
                    if (random.nextInt(10) <= player.GetAgility()){
                        enemy.SetDamage(player.GetDamage());
                    }
                    numberOfAttacking = (numberOfAttacking + 1) % 2;
                }
                case (1):{
                    if (random.nextInt(10) <= enemy.GetAgility()) {
                        this.healthBoost -= enemy.GetDamage();
                    }
                    numberOfAttacking = (numberOfAttacking + 1) % 2;
                }
            }
        }
    }

    public boolean EventIsProcessing(){
        return this.eventIsProcessing;
    }

    public String GetNameEnemy(){
        return this.enemy.GetName();
    }
}
