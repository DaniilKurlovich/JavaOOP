package sample;

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

    public boolean SetDamage(int damage){
        this.HealthPoint -= damage;
        return this.HealthPoint <= 0;
    }

    public int GetDamage(int factor){
        return this.Power * factor;
    }

    public int GetDamage(){
        return this.Power;
    }

    public int GetReward(Random random){
        return 10 * this.HealthPoint + random.nextInt(10);
    }

    public int GetHealthPoint(){
        return this.HealthPoint;
    }

    public int GetAgility() {
        return this.Agility;
    }
}
