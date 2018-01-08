package com.brian.Controller.Interactive;
import com.brian.Model.BotCredentials;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class InitBot extends TelegramLongPollingBot {
    private  BotCredentials botCredentials = new BotCredentials();

    @Override
    public void onUpdateReceived(Update update) {


    }

    @Override
    public String getBotUsername() {
        String username="";
        try{
            username= botCredentials.getUsername();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return username;
    }

    @Override
    public String getBotToken() {
        String botToken="";
        try{
            botToken= botCredentials.getToken();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return botToken;
    }
}
