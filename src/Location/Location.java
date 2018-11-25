package Location;

import GameFile.MyStruct;

public abstract class Location {
    private enum commands{};

    public abstract String processComand(MyStruct player, String message);
}
