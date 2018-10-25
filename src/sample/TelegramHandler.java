package sample;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.lang.reflect.Array;
import java.util.Arrays;

public class TelegramHandler extends TelegramLongPollingBot {
    private NewGame game;
    private String[] arrayCommand = new String[]{};
    private String[] arrayDefaultCommand = new String[]{"/start", "/help"};

    public TelegramHandler(NewGame game){
        this.game = game;
    }
    @Override
    public String getBotUsername() {
        return "testBot518";
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        if (Arrays.asList(arrayDefaultCommand).indexOf(txt) == 0){
            if (!game.HaveThisPlayer(msg.getChatId().toString())){
                game.AddPlayerToDataBase(msg.getChatId().toString(), "default", 4, 4);
                sendMsg(msg, "Персонаж успешно создан. Введите /help для получения туториала");
                return;
            }
            else {
                sendMsg(msg, "У вас уже есть свой персонаж");
                return;
            }
        }
        if (Arrays.asList(arrayDefaultCommand).indexOf(txt) == 1){
            sendMsg(msg, "Тут будет типо хелп");
            return;
        }
        sendMsg(msg, game.SetRequestFromHandler(msg.getChatId().toString(), txt));
    }

    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return "796588779:AAERVH3ghaGXImw_NumoITDKU1BqippIbfc";
        //Токен бота
    }

    public void setArrayCommand(String[] arrayCommand){
        this.arrayCommand = arrayCommand;
    }

}
