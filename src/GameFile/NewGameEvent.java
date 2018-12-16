package GameFile;

import Creatures.Enemy;
import Creatures.Player;

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
            int HP= random.nextInt(7) + 2;
            int power = random.nextInt(6) + 1;
            int agility = 10 - power;
            this.enemy = new Enemy(HP, power, agility);
            this.gold = random.nextInt(40) + 10;
        }
        this.eventIsProcessing = false;
    }

    public int getResultEvent(){
        if (this.healthBoost == 0) {
            this.eventIsProcessing = true;
            fight();
            this.eventIsProcessing = false;
        }
        return this.healthBoost;
    }

    private void fight(){
        Random random = new Random();
        int numberOfAttacking = random.nextInt(1);
        while (this.player.GetHealpoint() >= -this.healthBoost && this.enemy.isAlive()){
            switch (numberOfAttacking){
                case(0):{
                    if (random.nextInt(10) <= player.getAgility()){
                        enemy.setDamage(player.GetDamage());
                    }
                    numberOfAttacking = (numberOfAttacking + 1) % 2;
                }
                case (1):{
                    if (enemy.isAlive()){
                        if (random.nextInt(10) <= enemy.getAgility()) {
                            this.healthBoost -= enemy.getDamage();
                        }
                        numberOfAttacking = (numberOfAttacking + 1) % 2;
                    }
                }
            }
        }
    }

    public boolean eventIsProcessing(){
        return this.eventIsProcessing;
    }

    public int getGold() { return this.gold; }

    public String getNameEnemy(){
        return this.enemy.getName();
    }

}
