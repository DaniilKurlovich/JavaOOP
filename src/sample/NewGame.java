package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.List;

public class NewGame {
    private Map<String, MyStruct> DataBase = new HashMap<String, MyStruct>();
    private Map<String, String[]> arrayCommandForEachLocation = new HashMap<String, String[]>();
    private NameGenerator nameGenerator = new NameGenerator();

    public NewGame(){
        generateArrayComandForLocation();
    }

    public void addPlayerToDataBase(String chatID, String name, int power, int agility){
        DataBase.put(chatID, new MyStruct(new Player(name, power, agility), "Camp", null));
    }

    private void generateArrayComandForLocation(){

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
                }
                else
                    return "Персонаж уже был создан.";
        if (infoAboutSession != null){
            String location = infoAboutSession.getNameLocation();
            if (textMessageFromPlayer.equals("/location")){
                return getInfoAboutLocation(location);
            }
            if (location.equals("Camp")) {
                if (textMessageFromPlayer.equals("/adventure"))
                    return goToAdventure(chatID);
                if (textMessageFromPlayer.equals("/shop"))
                    return "Магазин не работает";
                }
            if (location.equals("Adventure")){
                if (infoAboutSession.getLastGameEvent() == null) {
                    infoAboutSession.setGameEvent(generateGameEvent(infoAboutSession.getPlayer()));
                }
                if (textMessageFromPlayer.equals("/continue")){
                    infoAboutSession.setGameEvent(generateGameEvent(infoAboutSession.getPlayer()));
                    int resultEvent = infoAboutSession.getLastGameEvent().getResultEvent();
                    if (resultEvent > 0){
                        return String.format("Ты исцелился на %d", resultEvent);
                    }
                    Player player = infoAboutSession.getPlayer();
                    player.SetDamage(-resultEvent);
                    if(infoAboutSession.getPlayer().IsAlive())
                        return String.format("В равном бою %s был убит твоей рукой у тебя осталось %d ХП",
                                infoAboutSession.getLastGameEvent().getNameEnemy(),player.GetHealpoint());
                    return ComeToCamp(chatID, true);
                }
                if (textMessageFromPlayer.equals("/home")){
                    return ComeToCamp(chatID, false);
                }
            }
        }
        return "Default";
    }

    public String getLocation(String chatId)
    {
        return this.DataBase.get(chatId).getNameLocation();
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
        info.setNameLocation("Camp");
        if (!wasKilled)
            return "Добро пожаловать в лагерь. /adventure чтобы отправиться в путешествие";
        else
            return "Ты был сильно ранен и ничего не помнишь, но какой-то незнакомец притащил тебя в лагерь. " +
                    "/adventure начать новое путешествие";
    }

    private String goToAdventure(String chatId){
        DataBase.get(chatId).setNameLocation("Adventure");
        DataBase.get(chatId).setGameEvent(null);
        return "Ты отправляешься в путешествие. /continue идти дальше, /home вернуться домой";
    }

    private String goToShop(String chatId) {
        if (DataBase.get(chatId).getNameLocation().equals("Camp")) {
            DataBase.get(chatId).setNameLocation("Shop");
            DataBase.get(chatId).setGameEvent(null);
            return "Вы находитесь в магазине";
        }
        else
        {
            return "В магазин можно отправиться только с лагеря.";
        }
    }

    public boolean haveThisPlayer(String chatID){
        MyStruct info = DataBase.get(chatID);
        if (info == null){
            return false;
        }
        return true;
    }
}
