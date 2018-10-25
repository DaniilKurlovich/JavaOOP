package sample;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramHandler extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "testBot518";
        //возвращаем юзера
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        if (txt.equals("/start")) {
            sendMsg(msg, "Hello, world! This is simple bot!");
        }
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

}
