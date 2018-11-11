package sample;

public class ShopItem {
    private int Cost;
    private int Hp;
    private int Damage;
    private int Agility;
    private String Description;

    public ShopItem(int cost, int hp, int damage, int agility, String description)
    {
        this.Cost = cost;
        this.Hp = hp;
        this.Agility = agility;
        this.Description = description;
    }

    public int GetCost() { return this.Cost; }
    public String GetDescription() {return this.Description; }
    public String ShowSpecificationsItem() {
        return "Cost: " + Integer.toString(this.Cost) + "\n" +
                "Hp: " + Integer.toString(this.Hp) + "\n" +
                "Damage: " + Integer.toString(this.Damage) + "\n" +
                "Agility: " + Integer.toString((this.Agility));
    }
}
