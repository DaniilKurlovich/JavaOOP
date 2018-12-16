package telegramHandler;

import GameFile.NewGame;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.SplittableRandom;

public class TelegramHandler extends TelegramLongPollingBot {
    private Settings settings = new Settings("telegramCfg.properties");
    private NewGame game;

    public TelegramHandler(NewGame game){
        this.game = game;
    }
    public TelegramHandler(){}

    public void setGame(NewGame game){
        this.game = game;
    }

    @Override
    public String getBotUsername() {
        return settings.getPropertyValue("BOT_USERNAME");
    }

    @Override
    public void onUpdateReceived(Update e) {
        Message msg = e.getMessage();
        String txt = msg.getText();
        sendMsg(msg, game.setRequestFromHandler(msg.getChatId().toString(), txt));
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

    public void sendMsgForGroup(String text, List<String> arrayChatID){
        for (String chatID:arrayChatID) {
            SendMessage s = new SendMessage();
            s.setChatId(chatID);
            s.setText(text);
            try { //Чтобы не крашнулась программа при вылете Exception
                execute(s);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotToken() {
        return settings.getPropertyValue("BOT_TOKEN");
    }

}