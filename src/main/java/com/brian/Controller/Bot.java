package com.brian.Controller;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;

public class Bot  extends TelegramLongPollingBot{


    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){

            String message_text = update.getMessage().getText().toLowerCase();
            long chat = update.getMessage().getChatId();
            String newMessage_text ="";

            if(message_text.toLowerCase().equals("hi")){
                newMessage_text ="Hello, My Name is Janice, what is your name?";
            }else if(message_text.toLowerCase().equals("Goodnight")){
                newMessage_text="Goodnight to you too!!";
            }
            else if(message_text.toLowerCase().equals("who made you?")){
                newMessage_text ="I am a Program developed using Java and the Telegram API,Brian is my Developer";
            }
            else{
                newMessage_text="Brian Has not Configured Response";
            }
            SendMessage message = new SendMessage()
                    .setChatId(chat)
                    .setText(newMessage_text);
            try{
                execute(message);
                System.out.println(message_text);
            }catch(TelegramApiException ex){
                ex.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "janiceAI_bot";
    }

    public String getBotToken() {
        return "451734586:AAFwKvWjCdgPdXZR5wBLC_ruYD1zkd-mGGk";
    }

    public String getUsername(){return "Janice";}
}

