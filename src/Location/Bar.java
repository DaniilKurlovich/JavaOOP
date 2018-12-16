package Location;

import GameFile.MyStruct;

public class Bar extends Location {
    public enum commands {PlayerSay("/say"), Info("/location"), Exit("/exit");
        private String command;
        commands(String s) {
            this.command = s;
        }
        public String getCommand(){return command;}
    }

    @Override
    public String[] processCommand(MyStruct infoAboutSession, String message) {
        if (!message.startsWith("/")){
            return new String[]{"1", infoAboutSession.getPlayer().nickname + ": " + message};
        }
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
                case Exit:{
                    return new String[]{"Camp", "ВЫ покинули бар"};
                }
                case Info:
                    return new String[]{"0", getInfoAboutLocation()};
                default: {
                    return new String[]{"1", infoAboutSession.getPlayer().nickname + ": " + "жопа"};
                }
            }
        }
    }

    @Override
    public String getInfoAboutLocation() {
        return "BAR";
    }

    @Override
    public String getNameLocation() {
        return "Bar";
    }
}