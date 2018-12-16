package Location;

import java.util.Random;
import Creatures.Player;
import GameFile.MyStruct;
import GameFile.NewGameEvent;

public class Camp extends Location {
    public enum characters {Power("power"), Agility("agility"), Hp("hp");
        private String character;
        characters(String s) {this.character = s;}
        public String getCharacter() {return character;}}

        public enum commands {Adventure("/adventure"), Info("/location"), Bar("/bar"), Update("/up");
        private String command;
        commands(String s) {
            this.command = s;
        }
        public String getCommand(){return command;}
    }

    private String[] getUpdatePlayer(MyStruct session, String character, int count)
    {
        characters parsedCharacter = null;
        for (characters currentCharacter: characters.values()) {
            if (currentCharacter.character.equals(character))
                parsedCharacter = currentCharacter;
        }
        switch (parsedCharacter){
            case Hp:
                if (count*20 < session.getPlayer().getMoneyInfo()){
                    session.getPlayer().setDefaultHP(session.getPlayer().getDefaultHP() + count);
                    session.getPlayer().setMoney(session.getPlayer().getMoneyInfo() - count*20);
                    return new String[] {"0", String.format("Хп увеличено на %s", count)};
                }
                else{
                    return new String[] {"0", "Не хватает денег. 20 монет за одну характеристику"};
                }
            case Power:
                if (count*20 < session.getPlayer().getMoneyInfo()){
                    session.getPlayer().setPower(session.getPlayer().getPower() + count);
                    session.getPlayer().setMoney(session.getPlayer().getMoneyInfo() - count*20);
                    return new String[] {"0", String.format("Сила увеличена на %s", count)};
                }
                else{
                    return new String[] {"0", "Не хватает денег. 20 монет за одну характеристику"};
                }
            case Agility:
                if (count*20 < session.getPlayer().getMoneyInfo()){
                    session.getPlayer().setAgility(session.getPlayer().getAgility() + count);
                    session.getPlayer().setMoney(session.getPlayer().getMoneyInfo() - count*20);
                    return new String[] {"0", String.format("Ловкость увеличена на %s", count)};
                }
                else{
                    return new String[] {"0", "Не хватает денег. 20 монет за одну характеристику"};
                }
            default:
                return new String[] {"0", "Такой характеристики не существует"};
        }
    }

    @Override
    public String[] processCommand(MyStruct infoAboutSession, String message) {
        String[] splittedMessage = message.split(" ");
        commands parsedCommand = null;
        for (commands currentCommand : commands.values()) {
            if (currentCommand.command.equals(splittedMessage[0]))
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
                case Bar:{
                    return new String[]{"Bar", "Вы зашли в бар."};
                }
                case Info:
                    return new String[]{"0", getInfoAboutLocation()};
                case Update:
                    if (splittedMessage.length == 3)
                        return getUpdatePlayer(infoAboutSession, splittedMessage[1],
                                               Integer.parseInt(splittedMessage[2]));
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