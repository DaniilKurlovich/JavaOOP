package sample;

import java.io.BufferedReader;
import java.util.Scanner;

public class Player {
    public String name;
    private int HealthPoint;
    private int Power;
    private int Agility;
    private int gold = 0;
    private int DefaultHP;
    private int winStreak = 0;

    public Player(String name, int Power, int agility)
    {
        this.name = name;
        this.HealthPoint = this.DefaultHP = 5 + Power;
        this.Power = Power;
        this.Agility = agility;
    }

    public void SetDamage(int damage)
    {
        this.HealthPoint -= damage;
    }

    public boolean IsAlive(){return this.HealthPoint > 0;}

    public int[] GetInfo()
    {
        return new int[] {this.HealthPoint, this.Power, this.Agility};
    }

    public int GetAgility(){return this.Agility;}

    public int GetHealpoint() {return this.HealthPoint;}

    public int GetDamage(int factor)
    {
        return this.Power * factor;
    }

    public int GetDamage(){
        return this.Power;
    }

    public void SetDefaultHP(){this.HealthPoint = this.DefaultHP;}

    public void SetReward(int gold){
        this.gold += gold;
    }

    public void GetHealing(int heal){
        this.HealthPoint += heal;
    }

    public void SetCountStreak(){ this.winStreak++; }
}
