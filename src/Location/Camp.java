package Location;

import java.util.Random;
import Creatures.Player;
import GameFile.MyStruct;
import GameFile.NewGameEvent;

public class Camp extends Location {
    public enum commands {Adventure("/adventure"), Info("/location");
        private String command;
        commands(String s) {
            this.command = s;
        }
        public String getCommand(){return command;}
    }

    @Override
    public String[] processCommand(MyStruct infoAboutSession, String message) {
        commands parsedCommand = null;
        for (commands currentCommand : commands.values()) {
            if (currentCommand.command.equals(message))
                parsedCommand = currentCommand;
        }
        if (parsedCommand == null){
            return new String[]{"0", "Неверная команда"};
        }
        else {
            switch (parsedCommand){
                case Adventure: {
                    return new String[]{"Adventure", "Вы отправляетесь в приключение, если вы готовы то напишите команду"
                            + " /continue"};
                }
                case Info:
                    return new String[]{"0", getInfoAboutLocation()};
                default: {
                    return new String[]{"0", "Неверная команда"};
                }
            }
        }
    }

    @Override
    public String getInfoAboutLocation() {
        return "CAMP";
    }

    @Override
    public String getNameLocation() {
        return "Camp";
    }

}