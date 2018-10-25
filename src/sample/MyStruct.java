package sample;

public class MyStruct {
    private Player player;
    private String nameLocation;
    private NewGameEvent LastGameEvent;

    public MyStruct(Player player, String nameLocation, NewGameEvent gameEvent){
        this.player = player;
        this.nameLocation = nameLocation;
        this.LastGameEvent = gameEvent;
    }

    public void SetNameLocation(String nameLocation){
        this.nameLocation = nameLocation;
    }

    public String GetNameLocation(){
        return this.nameLocation;
    }

    public Player GetPlayer(){
        return this.player;
    }

    public NewGameEvent GetLastGameEvent()
    {
        return this.LastGameEvent;
    }

    public void SetGameEvent(NewGameEvent gameEvent){
        this.LastGameEvent = gameEvent;
    }
}
