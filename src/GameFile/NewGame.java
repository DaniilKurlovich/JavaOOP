package GameFile;

import Creatures.NameGenerator;
import Creatures.Player;
import DataBase.MySQLDriver;
import Location.*;
import telegramHandler.TelegramHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewGame {
    private MySQLDriver driver;
    private TelegramHandler telegramHandler;
    private Map<String, MyStruct> DataBase = new HashMap<String, MyStruct>();
    private Map<String, Location> locationMap = new HashMap<String, Location>();

    public NewGame(TelegramHandler telegramHandler){
        driver = new MySQLDriver();
        initializeLocatipnMap();
        this.telegramHandler = telegramHandler;
    }

    public NewGame() {
        driver = new MySQLDriver();
        initializeLocatipnMap();
    }

    private void initializeLocatipnMap(){
        locationMap.put("Camp", new Camp());
        locationMap.put("Adventure", new Adventure());
        locationMap.put("Bar", new Bar());
    }

    public void addPlayerToDataBase(String chatID, String name, int power, int agility){
//        DataBase.put(chatID, new MyStruct(new Player(nickname, power, agility), locationMap.get("Camp"), null));
        Map<String, String> infoAboutNewUser = new Player(name, power, agility).surrializedPlayer();
        infoAboutNewUser.put("chatID", chatID);
        infoAboutNewUser.put("location", "Camp");
        System.out.println(infoAboutNewUser);
        driver.setNewInformation(infoAboutNewUser);
    }

    private String getInfoPlayer(Map<String, String> map) {
        return "Nickname: " + map.get("nickname") + "\n" +
                "Hp: " + map.get("hp") + "\n" +
                "Location: " + map.get("location") + "\n" +
                "Power: " + map.get("power") + "\n" +
                "Agility: " + map.get("agility") + "\n" +
                "Gold " + map.get("gold");

    }

    public boolean isPlayerInDB(String chatID){
        return driver.haveThisPlayer(chatID);
    }

    public String setRequestFromHandler(String chatID, String textMessageFromPlayer) {
        if (textMessageFromPlayer.equals("/help")) {
            return "Если вы только начали игру напишите /start для создания персонажа.";
        }
        Map<String, List<Object>> playerInfo = driver.getInformation(chatID);
        Boolean havePlayer = !(playerInfo == null);

        if (textMessageFromPlayer.equals("/start")) {
            if (!havePlayer) {
                this.addPlayerToDataBase(chatID, "default", 4, 4);
                return "Персонаж успешно создан. Введите /help для получения информации об игре, " +
                        "либо /location для получения информации о текущей локации в которой вы находитесь.";
            } else {
                return "Персонаж уже был создан.";
            }
        }
        if (havePlayer) {
            Map<String, String> infoAboutPlayer = getInfoAboutConcretePlayer(driver.getInformation(chatID), 0);
            MyStruct infoAboutSession = new MyStruct(new Player(infoAboutPlayer), locationMap.get(infoAboutPlayer.get("location")), null);
            if (textMessageFromPlayer.equals("/info")) {
                return getInfoPlayer(infoAboutPlayer);
            }
            String[] answer = infoAboutSession.getLocation().processCommand(infoAboutSession, textMessageFromPlayer);
            updatePlayer(infoAboutSession, chatID);
            if (!answer[0].equals("0")) {
                if (locationMap.containsKey(answer[0])) {
                    setNewLocation(chatID, locationMap.get(answer[0]));
                }
                else if (answer[0].equals("1")){
                    List<String> chatIDPlayeInLocation = driver.getPlayerInLocation(infoAboutSession.getLocation().getNameLocation());
                    chatIDPlayeInLocation.remove(chatID);
                    telegramHandler.sendMsgForGroup(answer[1], chatIDPlayeInLocation);
                }
            }
            Map<String, String> finalInformationForDB = infoAboutSession.getPlayer().surrializedPlayer();
            finalInformationForDB.put("chatID", chatID);
            driver.setNewInformation(finalInformationForDB);
            return answer[1];
        }
        return "Персонаж не был создан, введите /help";
    }

    public void updatePlayer(MyStruct infoAboutSession, String chatID){
        Map<String, String> arrayInformation = new HashMap<String, String>(){};
        arrayInformation.put("chatID", chatID);
        arrayInformation.put("power", String.valueOf(infoAboutSession.getPlayer().getPower()));
        arrayInformation.put("agility", String.valueOf(infoAboutSession.getPlayer().getAgility()));
        arrayInformation.put("defaultHp", String.valueOf(infoAboutSession.getPlayer().getDefaultHP()));
        arrayInformation.put("gold", String.valueOf(infoAboutSession.getPlayer().getMoneyInfo()));
        driver.setNewInformation(arrayInformation);
    }

    public String getLocation(String chatId)
    {
        return this.driver.getInformation(chatId).get("location").get(0).toString();
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
}