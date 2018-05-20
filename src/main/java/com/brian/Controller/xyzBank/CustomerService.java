package com.brian.Controller.xyzBank;

import com.brian.Model.BotCredentials;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService  extends TelegramLongPollingBot {
    BotCredentials botCredentials = new BotCredentials();

    @Override
    public void onUpdateReceived(Update update) {
        String new_message = "";
        String response = "";
        long chat = update.getMessage().getChatId();
        String username = update.getMessage().getChat().getFirstName();
        if(update.hasMessage() && update.getMessage().hasText()){
            try{
                new_message = update.getMessage().getText();
                if(new_message.equals("/start")){
                    response = "Hello " + username +" ,My name is Jarvis, Your Online XYZ Bank Customer Care Agent," +
                            "How may I be of help Today ?";
                    SendMessage message = new SendMessage().setChatId(chat).setText(response);
                    try{
                        execute(message);
                    }catch(TelegramApiException ex){
                        ex.printStackTrace();
                    }
                }else if(new_message.toLowerCase().equals("/FAQS".toLowerCase()) || new_message.toLowerCase().equals("FAQS".toLowerCase())){
                    SendMessage responses = new SendMessage().setChatId(chat).setText("Here are Some Frequently Asked Questions");
                    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                    List<KeyboardRow> keyboardRowList = new ArrayList<>();
                    KeyboardRow keyboardRow = new KeyboardRow();
                    keyboardRow.add("Where is XYZ Bank HQ ?");
                    keyboardRow.add("What are our interest Rates ?");
                    keyboardRow.add("Access Services through USSD");
                    keyboardRowList.add(keyboardRow);
                    replyKeyboardMarkup.setKeyboard(keyboardRowList);
                    responses.setReplyMarkup(replyKeyboardMarkup);
                    try{
                        execute(responses);
                    }catch (TelegramApiException ex){
                        ex.printStackTrace();
                    }

                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        String username = "";
        try {
            username = botCredentials.getUsername();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username;
    }
    @Override
    public String getBotToken() {
          String bottoken ="";
        try {
            bottoken= botCredentials.getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bottoken;
    }
}
