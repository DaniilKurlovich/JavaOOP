package Creatures;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Player {
    public String nickname;
    private int hp;
    private int power;
    private int agility;
    private int gold = 0;
    private int defaultHP;

    public Player(String nickname, int Power, int agility)
    {
        this.nickname = nickname;
        this.hp = this.defaultHP = 5 + Power;
        this.power = Power;
        this.agility = agility;
    }

    public Player(Map<String, String> infoFromDB) {
        this.nickname = infoFromDB.get("nickname");
        hp = Integer.parseInt(infoFromDB.get("hp"));
        power = Integer.parseInt(infoFromDB.get("power"));
        agility = Integer.parseInt(infoFromDB.get("agility"));
        gold = Integer.parseInt(infoFromDB.get("gold"));
        defaultHP = Integer.parseInt(infoFromDB.get("defaultHP"));
    }
    public void SetDamage(int damage)
    {
        this.hp -= damage;
    }

    public boolean IsAlive(){return this.hp > 0;}

    public int getAgility(){return this.agility;}

    public int GetHealpoint() {return this.hp;}

    public int getDefaultHP() {return this.defaultHP;}

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower(){ return this.power; }

    public void setDefaultHP(int hp){
        this.hp = hp;
    }

    public int GetDamage(int factor)
    {
        return this.power * factor;
    }

    public void restoreHP() { this.hp = this.defaultHP; }

    public int GetDamage(){
        return this.power;
    }

    public void SetDefaultHP(){this.hp = this.defaultHP;}

    public int getMoneyInfo(){
        return this.gold;
    }

    public void setReward(int gold){
        this.gold += gold;
    }

    public void GetHealing(int heal){
        this.hp += heal;
    }

    public void setMoney(int money) { this.gold = money; }

    public Map<String, String> surrializedPlayer(){
        Map<String, String> resultOfSurrealization = new HashMap<>();
        resultOfSurrealization.put("nickname", nickname);
        resultOfSurrealization.put("hp", Integer.toString(GetHealpoint()));
        resultOfSurrealization.put("agility", Integer.toString(getAgility()));
        resultOfSurrealization.put("power", Integer.toString(GetDamage()));
        resultOfSurrealization.put("gold", Integer.toString(getMoneyInfo()));
        resultOfSurrealization.put("defaultHP", Integer.toString(getDefaultHP()));
        return resultOfSurrealization;
    }
}
