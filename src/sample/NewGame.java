package sample;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.tools.DocumentationTool;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NewGame {
    private Map<String, MyStruct> DB = new HashMap<String, MyStruct>();
    private Map<String, String[]> arrayCommandForEachLocation = new HashMap<String, String[]>();
    private NameGenerator nameGenerator = new NameGenerator();

    public NewGame(){
        GenerateArrayComandForLocation();
    }

    public void AddPlayerToDataBase(String chatID, String name, int power, int agility){
        DB.put(chatID, new MyStruct(new Player(name, power, agility), "Camp", null));
    }

    private void GenerateArrayComandForLocation(){
        this.arrayCommandForEachLocation.put("Camp", new String[]{"/adventure"});
        this.arrayCommandForEachLocation.put("Adventure", new String[]{"/continue", "/home"});
    }

    public String SetRequestFromHandler(String chatID, String text){
        MyStruct infoAboutSession = this.DB.get(chatID);
        if (infoAboutSession != null){
            String location = infoAboutSession.GetNameLocation();
            int numberCommand = GetNumberCommand(location, text);
            if (numberCommand == -1){
                return "Неправильно введена команда";
            }
            switch (location){
                case "Camp":{
                    switch (numberCommand){
                        case (0):{
                            return GoToAdventure(chatID);
                        }
                    }
                }
                case "Adventure":{
                    if (infoAboutSession.GetLastGameEvent() == null) {
                        infoAboutSession.SetGameEvent(generateGameEvent(infoAboutSession.GetPlayer()));
                    }
                    if (infoAboutSession.GetLastGameEvent().EventIsProcessing()){
                        return "Дождитесь завершения прошлого события";
                    }
                    switch (numberCommand){
                        case (0):{
                            infoAboutSession.SetGameEvent(generateGameEvent(infoAboutSession.GetPlayer()));
                            int resultEvent = infoAboutSession.GetLastGameEvent().GetResultEwent();
                            if (resultEvent > 0){
                                return String.format("Ты исцелился на %d", resultEvent);
                            }
                            Player player = infoAboutSession.GetPlayer();
                            player.SetDamage(-resultEvent);
                            if(infoAboutSession.GetPlayer().IsAlive())
                                return String.format("В равном бою %s был убит твоей рукой у тебя осталось %d ХП", infoAboutSession.GetLastGameEvent().GetNameEnemy(),player.GetHealpoint());
                            return ComeToCamp(chatID, true);
                        }
                        case (1):{
                            return ComeToCamp(chatID, false);
                        }
                    }
                }
            }
        }
        return "Default";
    }

    public int GetNumberCommand(String nameLocation, String command){
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
        MyStruct info = DB.get(chatId);
        info.GetPlayer().SetDefaultHP();
        info.SetNameLocation("Camp");
        if (!wasKilled)
            return "Добро пожаловать в лагерь. /adventure чтобы отправиться в путешествие";
        else
            return "Ты был сильно ранен и ничего не помнишь, но какой-то незнакомец притащил тебя в лагерь";
    }

    private String GoToAdventure(String chatId){
        DB.get(chatId).SetNameLocation("Adventure");
        DB.get(chatId).SetGameEvent(null);
        return "Ты отправляешься в путешествие. /continue идти дальше, /home вернуться домой";
    }

    public boolean HaveThisPlayer(String chatID){
        MyStruct info = DB.get(chatID);
        if (info == null){
            return false;
        }
        return true;
    }
}
