package com.brian.Controller.MessageBot;
import java.util.Scanner;
/*
   Author: Brian Kamau
 */
import com.brian.Controller.PhotoBot.PhotoBot;
import com.brian.Controller.xyzBank.CustomerService;
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
            System.out.println("Choose Bot\n1.Message Bot\n2.Photo Bot\n3.XYZ Bank");
            choice = scanner.nextInt();
            switch(choice){

                case 1:
                    System.out.println("Messaging Bot is Running");
                    botApi.registerBot(new Bot());
                    break;

                case 2:
                    System.out.println("Photo Bot is Running");
                    botApi.registerBot(new PhotoBot());
                    break;
                case 3:
                    System.out.println("Running XYZ Bank Service");
                    botApi.registerBot(new CustomerService());
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;

            }
        }catch(TelegramApiException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


