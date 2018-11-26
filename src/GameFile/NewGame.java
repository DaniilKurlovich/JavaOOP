package GameFile;

import Creatures.NameGenerator;
import Creatures.Player;
import Location.Location;
import Location.Camp;
import Location.Adventure;

import java.util.HashMap;
import java.util.Map;


public class NewGame {
    private Map<String, MyStruct> DataBase = new HashMap<String, MyStruct>();
    private Map<String, Location> locationMap = new HashMap<String, Location>();

    public NewGame(){
        locationMap.put("Camp", new Camp());
        locationMap.put("Adventure", new Adventure());
    }

    public void addPlayerToDataBase(String chatID, String name, int power, int agility){
        DataBase.put(chatID, new MyStruct(new Player(name, power, agility), locationMap.get("Camp"), null));
    }

    public String setRequestFromHandler(String chatID, String textMessageFromPlayer) {
        MyStruct infoAboutSession = this.DataBase.get(chatID);
        if (textMessageFromPlayer.equals("/help"))
            return "Если вы только начали игру напишите /start для создания персонажа.";
        else if (textMessageFromPlayer.equals("/start"))
            if (infoAboutSession == null) {
                this.addPlayerToDataBase(chatID, "default", 4, 4);
                return "Персонаж успешно создан. Введите /help для получения информации об игре, " +
                        "либо /location для получения информации о текущей локации в которой вы находитесь.";
            } else {
                return "Персонаж уже был создан.";
            }
        else if (infoAboutSession != null)
            {
            String[] answer = infoAboutSession.getLocation().processCommand(infoAboutSession, textMessageFromPlayer);

            if (!answer[0].equals("0") && locationMap.containsKey(answer[0])) {
                infoAboutSession.setLocation(locationMap.get(answer[0]));
            }
            return answer[1];
        }
        return "Персонаж не был создан, введите /help";
    }

    public Location getLocation(String chatId)
    {
        return this.DataBase.get(chatId).getLocation();
    }

    public String getInfoAboutLocation(String nameLocation){
        switch (nameLocation){
            case("Camp"):{
                return "Лагерь. Начальный хаб. Доступные команды: /adventure - Отправиться в путешествие";
            }
            case ("Adventure"):{
                return "Ты находишься в пустоши. На каждому шагу тебе поджидается опасность. Доступные команды" +
                        "/home - вернуться домой. /continue - идти дальше";
            }
            case ("Shop"):{
                return "Вы находитесь в магазине";
            }
        }
        return "Error";
    }

    public boolean haveThisPlayer(String chatID){
        MyStruct info = DataBase.get(chatID);
        if (info == null){
            return false;
        }
        return true;
    }
}