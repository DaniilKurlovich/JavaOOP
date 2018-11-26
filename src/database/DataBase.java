package database;

import Creatures.Player;
import GameFile.MyStruct;
import Location.Location;

public interface DataBase {
    void AddPlayer(String ChatID, MyStruct infoAboutPlayer);

    void ChangeLocation(String ChatID, Location newLocation);

    void ChangeNamePlayer(String ChatID, String newName);

    void ChangeStatusPlayer(String ChatID, Player player);

    boolean HaveThisPlayer(String ChatID);
}
