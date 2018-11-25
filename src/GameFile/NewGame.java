package GameFile;

import Creatures.NameGenerator;
import Creatures.Player;
import Location.Location;
import Location.Camp;
import Location.Adventure;

import java.security.acl.LastOwnerException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NewGame {
    private Map<String, MyStruct> DataBase = new HashMap<String, MyStruct>();
    private Map<String, String[]> arrayCommandForEachLocation = new HashMap<String, String[]>();
    private NameGenerator nameGenerator = new NameGenerator();
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

    public int getNumberCommand(String nameLocation, String command){
        return Arrays.asList(arrayCommandForEachLocation.get(nameLocation)).indexOf(command);
    }

    private NewGameEvent generateGameEvent(Player player)
    {
        Random random = new Random();
        //Генерируется число, которое будет определять саму вероятность
        int status = random.nextInt(4);
        return new NewGameEvent(player, nameGenerator.GetName(), status == 1);
    }

    private String ComeToCamp(String chatId, boolean wasKilled){
        MyStruct info = DataBase.get(chatId);
        info.getPlayer().SetDefaultHP();
        info.setLocation(locationMap.get("Camp"));
        if (!wasKilled)
            return "Добро пожаловать в лагерь. /adventure чтобы отправиться в путешествие";
        else
            return "Ты был сильно ранен и ничего не помнишь, но какой-то незнакомец притащил тебя в лагерь. " +
                    "/adventure начать новое путешествие";
    }

    private String goToAdventure(String chatId){
        DataBase.get(chatId).setLocation(locationMap.get("Adventure"));
        DataBase.get(chatId).setGameEvent(null);
        return "Ты отправляешься в путешествие. /continue идти дальше, /home вернуться домой";
    }

//    private String goToShop(String chatId) {
//        if (DataBase.get(chatId).getLocation().equals("Camp")) {
//            DataBase.get(chatId).setLocation("Shop");
//            DataBase.get(chatId).setGameEvent(null);
//            return "Вы находитесь в магазине";
//        }
//        else
//        {
//            return "В магазин можно отправиться только с лагеря.";
//        }
//    }

    public boolean haveThisPlayer(String chatID){
        MyStruct info = DataBase.get(chatID);
        if (info == null){
            return false;
        }
        return true;
    }
}