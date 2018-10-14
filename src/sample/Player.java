package sample;

import java.io.BufferedReader;
import java.util.Scanner;

public class Player {
    public String name;
    private int HealthPoint;
    private int Power;
    private int Agility;
    private int money = 0;

    public Player(String name, int HP, int Power, int agility)
    {
        this.name = name;
        this.HealthPoint = HP;
        this.Power = Power;
        this.Agility = agility;
    }

    public boolean SetDamage(int damage)
    {
        this.HealthPoint -= damage;
        return this.HealthPoint <= 0;
    }

    public int[] GetInfo()
    {
        return new int[] {this.HealthPoint, this.Power, this.Agility};
    }

    public int GetDamage(int factor)
    {
        return this.Power * factor;
    }

    public int GetChosePlayer(int min, int max, Scanner reader)
    {
        int choose = reader.nextInt();
        if (choose < min || choose > max)
        {
            System.out.print("Неверный выбор. Попробуйте еще раз");
            return GetChosePlayer(min, max, reader);
        }
        return choose;
    }

    public void GetReward(int money){
        this.money += money;
    }

    public void GetHealing(int heal){
        this.HealthPoint += heal;
    }
}
