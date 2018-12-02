package GameFile;

import Creatures.NameGenerator;
import Creatures.Player;
import DataBase.MySQLDriver;
import Location.Location;
import Location.Camp;
import Location.Adventure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewGame {
    private MySQLDriver driver;
    private Map<String, MyStruct> DataBase = new HashMap<String, MyStruct>();
    private Map<String, Location> locationMap = new HashMap<String, Location>();

    public NewGame(){
        driver = new MySQLDriver();
        locationMap.put("Camp", new Camp());
        locationMap.put("Adventure", new Adventure());
    }

    public void addPlayerToDataBase(String chatID, String name, int power, int agility){
//        DataBase.put(chatID, new MyStruct(new Player(nickname, power, agility), locationMap.get("Camp"), null));
        Map<String, String> infoAboutNewUser = new Player(name, power, agility).surrializedPlayer();
        infoAboutNewUser.put("chatID", chatID);
        infoAboutNewUser.put("location", "Camp");
        System.out.println(infoAboutNewUser);
        driver.setNewInformation(infoAboutNewUser);
    }

    public String setRequestFromHandler(String chatID, String textMessageFromPlayer) {
        if (textMessageFromPlayer.equals("/help"))
            return "Если вы только начали игру напишите /start для создания персонажа.";
        else if (textMessageFromPlayer.equals("/start"))
            if (!driver.haveThisPlayer(chatID)) {
                this.addPlayerToDataBase(chatID, "default", 4, 4);
                return "Персонаж успешно создан. Введите /help для получения информации об игре, " +
                        "либо /location для получения информации о текущей локации в которой вы находитесь.";
            } else {
                return "Персонаж уже был создан.";
            }
        Map<String, String> infoAboutPlayer = getInfoAboutConcretePlayer(driver.getInformation(chatID), 0);
        MyStruct infoAboutSession = new MyStruct(new Player(infoAboutPlayer), locationMap.get(infoAboutPlayer.get("location")), null);
        if (infoAboutSession != null)
            {
            String[] answer = infoAboutSession.getLocation().processCommand(infoAboutSession, textMessageFromPlayer);

            if (!answer[0].equals("0") && locationMap.containsKey(answer[0])) {
                setNewLocation(chatID, locationMap.get(answer[0]));
            }
            Map<String, String> finalInformationForDB = infoAboutSession.getPlayer().surrializedPlayer();
            finalInformationForDB.put("chatID", chatID);
            driver.setNewInformation(finalInformationForDB);
            return answer[1];
        }
        return "Персонаж не был создан, введите /help";
    }

    public Location getLocation(String chatId)
    {
        return this.DataBase.get(chatId).getLocation();
    }

    public void setNewLocation(String chatID, Location newLocation){
        Map<String, String> mapForNewLocation = new HashMap<>();
        mapForNewLocation.put("location", newLocation.getNameLocation());
        mapForNewLocation.put("chatID", chatID);
        driver.setNewInformation(mapForNewLocation);
    }

    private Map<String, String> getInfoAboutConcretePlayer(Map<String, List<Object>> arrayAllPlayer, int numberUser){
        Map<String, String> infoAboutUser = new HashMap<>();
        infoAboutUser.put("nickname", arrayAllPlayer.get("nickname").get(numberUser).toString());
        infoAboutUser.put("hp", arrayAllPlayer.get("hp").get(numberUser).toString());
        infoAboutUser.put("power", arrayAllPlayer.get("power").get(numberUser).toString());
        infoAboutUser.put("agility", arrayAllPlayer.get("agility").get(numberUser).toString());
        infoAboutUser.put("gold", arrayAllPlayer.get("gold").get(numberUser).toString());
        infoAboutUser.put("defaultHP", arrayAllPlayer.get("defaultHP").get(numberUser).toString());
        infoAboutUser.put("location", arrayAllPlayer.get("location").get(numberUser).toString());
        infoAboutUser.put("chatID", arrayAllPlayer.get("chatID").get(numberUser).toString());
        return infoAboutUser;
    }

    public boolean haveThisPlayer(String chatID){
        MyStruct info = DataBase.get(chatID);
        if (info == null){
            return false;
        }
        return true;
    }
}