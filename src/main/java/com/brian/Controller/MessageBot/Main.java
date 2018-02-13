package com.brian.Controller.MessageBot;
import java.util.Scanner;
/*
   Author: Brian Kamau
 */
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int choice;

    public static void main(String [] args){
        ApiContextInitializer.init();

        TelegramBotsApi botApi = new TelegramBotsApi();

        try{
            System.out.println("Bot is Running");
            for(int i=0;i<5;i++){
                System.out.println(".");
                Thread.sleep(200);
            }
            System.out.println("Telegram Bots by: [Brian Kamau]");
            System.out.println("Choose Bot");

            botApi.registerBot(new Bot());
        }catch(TelegramApiException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


