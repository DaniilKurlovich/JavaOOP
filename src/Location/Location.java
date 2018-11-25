package Location;

public abstract class Location {
    private enum commands{};

    public abstract String processComand(String chatID, String message);
}
