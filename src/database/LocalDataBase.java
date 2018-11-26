package database;

import Creatures.Player;
import GameFile.MyStruct;
import Location.Location;
import java.util.HashMap;
import java.util.Map;

public class LocalDataBase implements DataBase {
    private Map<String, MyStruct> DataBase  = new HashMap<String, MyStruct>();

    @Override
    public void AddPlayer(String ChatID, MyStruct infoAboutPlayer) {
        if (!HaveThisPlayer(ChatID)){
            DataBase.put(ChatID, infoAboutPlayer);
        }
    }

    @Override
    public void ChangeLocation(String ChatID, Location newLocation) {
        if (!HaveThisPlayer(ChatID)){
            DataBase.get(ChatID).setLocation(newLocation);
        }
    }

    @Override
    public void ChangeNamePlayer(String ChatID, String newName) {
        if (!HaveThisPlayer(ChatID)){
            DataBase.get(ChatID).getPlayer().name = newName;
        }
    }

    @Override
    public void ChangeStatusPlayer(String ChatID, Player player) {
        if (!HaveThisPlayer(ChatID)){
            Player currentPlayer = DataBase.get(ChatID).getPlayer();

        }
    }

    @Override
    public boolean HaveThisPlayer(String ChatID) {
        return !(DataBase.get(ChatID) ==  null);
    }
}
