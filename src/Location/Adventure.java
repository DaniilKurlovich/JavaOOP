package Location;

import Creatures.Player;
import GameFile.MyStruct;
import GameFile.NewGameEvent;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.Random;

public class Adventure extends Location {
    public enum commands {Continue("/continue"), Home("/home"), Info("/location");
        private String command;
        commands(String s) {
            this.command = s;
        }
        public String getCommand(){return command;}
    }

    /*
    Генератор эвентов, если возвращает false, то событие типа 'bad', иначе 'good'
     */
    private boolean generateEvent(){
        Random rnd = new Random(System.currentTimeMillis());
        int number = rnd.nextInt(10);
        if (number < 8)
        {
            return false;
        }
        return true;
    }

    private NewGameEvent generateGameEvent(Player player)
    {
        return new NewGameEvent(player, player.name, generateEvent());
    }

    public String[] getMessageForContinueEvent(MyStruct infoAboutSession){
        String[] returnedMessage = new String[2];
        NewGameEvent gameEvent = generateGameEvent(infoAboutSession.getPlayer());
        int resultEvent = gameEvent.getResultEvent();
        if (resultEvent < 0) {
            if (!infoAboutSession.getPlayer().IsAlive()) {
                returnedMessage[0] = "Camp";
                returnedMessage[1] = "Вы подохли и вас отправили в лагерь.";
            } else {
                returnedMessage[0] = "0"; // соре
                returnedMessage[1] = "Вы справились в схватке с противником " + gameEvent.getNameEnemy() + "\n"
                        + "Денег получено: " + gameEvent.getGold();
            }
        } else {
            returnedMessage[0] = "0";
            returnedMessage[1] = "Хп вылечено: 5" + "\n" + "Денег получено: 5";
        }
        return returnedMessage;
    }

    @Override
    public String[] processCommand(MyStruct infoAboutSession, String message) {
        commands parsedCommand = null;
        for (commands currentCommand : commands.values()) {
            if (currentCommand.command.equals(message))
                parsedCommand = currentCommand;
        }
        if (parsedCommand == null){
            return new String[] {"0", "Неверная команда"};
        }
        switch (parsedCommand){
            case Continue: {
                return getMessageForContinueEvent(infoAboutSession);
            }
            case Home:{
                return new String[] {"Camp", "Вы отправились домой"};
            }
            case Info:
                return new String[]{"0", getInfoAboutLocation()};
            default:{
                return new String[]{"0", "Неверная команда"};
            }
        }
    }

    @Override
    public String getInfoAboutLocation() {
        return "ПРЕКЛЮЧЕНЕЕ";
    }
}