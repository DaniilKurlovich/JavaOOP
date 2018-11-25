package Location;

import Creatures.Player;
import GameFile.MyStruct;

public class Camp extends Location {
    public enum commands {Adventure("/adventure"), Info("/location");
        private String command;
        commands(String s) {
            this.command = s;
        }
        public String getCommand(){return command;}
    }

    @Override
    public String processCommand(MyStruct infoAboutSession, String message) {
        commands parsedCommand = null;
        for (commands currentCommand : commands.values()) {
            if (currentCommand.command.equals(message))
                parsedCommand = currentCommand;
        }
        if (parsedCommand == null){
            return "Неверная команда";
        }
        else {
            switch (parsedCommand){
                case Adventure: {
                    return "Adventure";
                }
                case Info:
                    return getInfoAboutLocation();
                default:{
                    return "Неверная комаанда";
                }
            }
        }
    }

    @Override
    public String getInfoAboutLocation() {
        return "CAMP";
    }
}
