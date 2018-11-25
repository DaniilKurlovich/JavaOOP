package Creatures;

import java.util.Random;

public class Enemy {
    public String name;
    private int HealthPoint;
    private int Power;
    private int Agility;
    private double Aggressiveness = 1.0;

    public Enemy(String name, int HP, int Power, int Agility){
        this.name = name;
        this.HealthPoint = HP;
        this.Power = Power;
        this.Agility = Agility;
    }

    public void setDamage(int damage){
        this.HealthPoint -= damage;
    }

    public boolean isAlive(){return this.HealthPoint > 0;}

    public int getDamage(int factor){
        return this.Power * factor;
    }

    public int getDamage(){
        return this.Power;
    }

    public int getReward(Random random){
        return 10 * this.HealthPoint + random.nextInt(10);
    }

    public int getHealthPoint(){
        return this.HealthPoint;
    }

    public int getAgility() {
        return this.Agility;
    }

    public String getName(){return this.name;}
}
