package com.brian.Controller.PhotoBot;
import com.brian.Model.BotCredentials;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhotoBot extends TelegramLongPollingBot {
    BotCredentials botCredentials = new BotCredentials();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (message_text.equals("/start")) {
                SendMessage message = new SendMessage().setChatId(chat_id).setText(message_text);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/markup")) {
                SendMessage message = new SendMessage().setChatId(chat_id).setText("Here is Your Keyboard");
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboardRowList = new ArrayList<>();

                KeyboardRow row = new KeyboardRow();
                row.add("Row 1 Button 1");
                row.add("Row 1 Button 2");
                row.add("Row 1 Button 3");

                keyboardRowList.add(row);

                row = new KeyboardRow();

                row.add("Row 2 Button 1");
                row.add("Row 2 Button 2");
                row.add("Row 2 Button 3");
                keyboardRowList.add(row);
                keyboardMarkup.setKeyboard(keyboardRowList);

                message.setReplyMarkup(keyboardMarkup);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (message_text.equals("Row 1 Button 1")) {

                SendPhoto message = new SendPhoto().setChatId(chat_id).setPhoto("https://image.ibb.co/jSPxN6/mt_longonot.jpg").setCaption("Photo");
                   try{
                        sendPhoto(message);
                   }catch(TelegramApiException ex){
                     ex.printStackTrace();
                     }
                 }

                 else if(message_text.equals("/pic")){

                    SendPhoto benz = new SendPhoto().setChatId(chat_id).setPhoto("https://image.ibb.co/mX2LQR/164530279_mercedes_wallpapers.jpg").setCaption("Benz");
                    try{
                        sendPhoto(benz);
                    }catch(TelegramApiException ex){
                        ex.printStackTrace();
                    }


            }
            }


    }


    @Override
    public String getBotUsername() {
        String username ="";
        try{
            username = botCredentials.getUsername();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return username;
    }

    @Override
    public String getBotToken() {
        String botToken="";
        try{
            botToken = botCredentials.getToken();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return botToken;
    }
}
