package Location;

import Creatures.Player;
import GameFile.MyStruct;

public class Adventure extends Location {
    public enum commands {Continue("/continue"), Home("/home"), Info("/location");
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
        switch (parsedCommand){
            case Continue: {
                return "Continue";
            }
            case Home:{
                return "";
            }
            case Info:
                return getInfoAboutLocation();
            default:{
                return "Неверная комаанда";
            }
        }
    }

    @Override
    public String getInfoAboutLocation() {
        return "ПРЕКЛЮЧЕНЕЕ";
    }
}
