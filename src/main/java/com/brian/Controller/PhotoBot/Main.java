package com.brian.Controller.PhotoBot;

import com.brian.Controller.MessageBot.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args){

        ApiContextInitializer.init();

        TelegramBotsApi botApi = new TelegramBotsApi();

        try{
            botApi.registerBot(new PhotoBot());
        }catch(TelegramApiException e){
            e.printStackTrace();
        }



    }
}
