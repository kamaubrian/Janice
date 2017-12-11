package com.brian.Controller;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

public class Bot  extends TelegramLongPollingBot{


    public void onUpdateReceived(Update update) {
        String new_message="";
        long chat = update.getMessage().getChatId();

        if(update.hasMessage() && update.getMessage().hasText()) {
           String message_case;

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
                System.out.println(new_message);
            }catch(TelegramApiException ex){
                ex.printStackTrace();
            }
        }


    public String getBotUsername() {
        return "janiceAI_bot";
    }

    public String getBotToken() {
        return "451734586:AAFwKvWjCdgPdXZR5wBLC_ruYD1zkd-mGGk";
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

