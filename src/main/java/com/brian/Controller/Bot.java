package com.brian.Controller;
/*
  Author:  Brian Kamau
 */
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.brian.Model.BotCredentials;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Bot  extends TelegramLongPollingBot{
    BotCredentials botmodel = new BotCredentials();

    public void onUpdateReceived(Update update) {
        String new_message="";
        long chat = update.getMessage().getChatId();
        String message_case="";

        if(update.hasMessage() && update.getMessage().hasText()) {

           message_case = update.getMessage().getText();
           switch (message_case.toLowerCase()) {
               case "hello":
                   new_message ="Hello, My name is Janice, What is your name?";
                   break;

               case "good morning":
                    Integer time = Integer.valueOf(getDateTime());
                    if(time<=12){
                        new_message ="Good Morning to you too";

                    }else if(time<=16){
                        new_message = "Its Afternoon, Good Afternoon";
                    }
                    else{
                        new_message="Its Evening, Good Evening";
                    }
                   break;

               case "good evening":
                   Integer timer = Integer.valueOf(getDateTime());
                   if(timer<=12){
                       new_message ="Good Morning to you too";

                   }else if(timer<=16){
                       new_message = "Its Afternoon, Good Afternoon";
                   }
                   else{
                       new_message="Its Evening, Good Evening";
                   }

                   break;

               case "good night":
                   Integer tim = Integer.valueOf(getDateTime());
                   if(tim>22){
                       new_message ="Good Night Buddy";
                   }else if(tim<21){
                       new_message = "Its Very Early, Get A Life";
                   }
                   break;


               default:
                   new_message="Response Has not Yet Been Configured";
                   break;
           }
       }

            SendMessage message = new SendMessage()
                    .setChatId(chat)
                    .setText(new_message);
            try{
                execute(message);
                System.out.println(getDateTime());
                System.out.println(message_case);
            }catch(TelegramApiException ex){
                ex.printStackTrace();
            }
        }


    public String getBotUsername() {
        String username = "";
        try {
            username = botmodel.getUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username;
    }

    public String getBotToken() {
        String bottoken ="";
        try {
            bottoken= botmodel.getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bottoken;
    }

    public String getUsername(){return "Janice";}

    public String getDateTime(){
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
        String date = dateformat.format(new Date());
        String[] time = date.split(":");
        String hour = time[0];
        return hour;
    }
}

