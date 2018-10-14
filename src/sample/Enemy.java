package sample;

import java.util.Random;

public class Enemy {
    public String name;
    private int HealthPoint;
    private int Damage;
    private double Aggressiveness = 1.0;

    public Enemy(String name, int HP, int Damage){
        this.name = name;
        this.HealthPoint = HP;
        this.Damage = Damage;
    }

    public boolean SetDamage(int damage){
        this.HealthPoint -= damage;
        return this.HealthPoint <= 0;
    }

    public int GetDamage(int factor){
        return this.Damage * factor;
    }

    public int GetReward(Random random){
        return 10 * this.HealthPoint + random.nextInt(10);
    }
}
