package GameFile;

import Creatures.Player;
import Location.Location;

public class MyStruct {
    private Player player;
    private Location Location;
    private NewGameEvent LastGameEvent;

    public MyStruct(Player player, Location Location, NewGameEvent gameEvent){
        this.player = player;
        this.Location = Location;
        this.LastGameEvent = gameEvent;
    }

    public void setLocation(Location Location){
        this.Location = Location;
    }

    public Location getLocation(){
        return this.Location;
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
