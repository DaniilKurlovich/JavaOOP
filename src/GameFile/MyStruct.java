package GameFile;

import Creatures.Player;
import GameFile.NewGameEvent;

public class MyStruct {
    private Player player;
    private String nameLocation;
    private NewGameEvent LastGameEvent;

    public MyStruct(Player player, String nameLocation, NewGameEvent gameEvent){
        this.player = player;
        this.nameLocation = nameLocation;
        this.LastGameEvent = gameEvent;
    }

    public void setNameLocation(String nameLocation){
        this.nameLocation = nameLocation;
    }

    public String getNameLocation(){
        return this.nameLocation;
    }

    public Player getPlayer(){
        return this.player;
    }

    public NewGameEvent getLastGameEvent()
    {
        return this.LastGameEvent;
    }

    public void setGameEvent(NewGameEvent gameEvent){
        this.LastGameEvent = gameEvent;
    }
}
