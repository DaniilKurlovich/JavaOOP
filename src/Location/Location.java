package Location;

import Creatures.Player;
import GameFile.MyStruct;

public abstract class Location {
    private enum commands{};

    public abstract String processCommand(MyStruct infoAboutSession, String message);

    public abstract String getInfoAboutLocation();
}
