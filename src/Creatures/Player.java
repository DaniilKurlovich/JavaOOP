package Creatures;

public class Player {
    public String name;
    private int healthPoint;
    private int power;
    private int agility;
    private int gold = 0;
    private int DefaultHP;
    private int winStreak = 0;

    public Player(String name, int Power, int agility)
    {
        this.name = name;
        this.healthPoint = this.DefaultHP = 5 + Power;
        this.power = Power;
        this.agility = agility;
    }

    public void SetDamage(int damage)
    {
        this.healthPoint -= damage;
    }

    public boolean IsAlive(){return this.healthPoint > 0;}

    public int[] GetInfo()
    {
        return new int[] {this.healthPoint, this.power, this.agility};
    }

    public int GetAgility(){return this.agility;}

    public int GetHealpoint() {return this.healthPoint;}

    public int GetDamage(int factor)
    {
        return this.power * factor;
    }

    public void restoreHP() { this.healthPoint = this.DefaultHP; }

    public int GetDamage(){
        return this.power;
    }

    public void SetDefaultHP(){this.healthPoint = this.DefaultHP;}

    public int getMoneyInfo(){
        return this.gold;
    }

    public void setReward(int gold){
        this.gold += gold;
    }

    public void GetHealing(int heal){
        this.healthPoint += heal;
    }

    public void SetCountStreak(){ this.winStreak++; }
}
