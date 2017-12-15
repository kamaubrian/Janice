package com.brian.Controller.CallBack;

import com.brian.Controller.EmojiBot.EBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class CallBot {
    public static void main(String [] args){
        ApiContextInitializer.init();

        TelegramBotsApi botApi = new TelegramBotsApi();

        try{
            botApi.registerBot(new CallBack());
        }catch(TelegramApiException e){
            e.printStackTrace();
        }
    }
}
