package com.brian.Controller.LoggingBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Bot {
    public static void main(String[] args){

        ApiContextInitializer.init();

        TelegramBotsApi botApi = new TelegramBotsApi();

        try{
            botApi.registerBot(new LoggingBot());
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }

}
