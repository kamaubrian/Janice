package com.brian.Controller.xyzBank;

import com.brian.Model.BotCredentials;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendLocation;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
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

                }else if(new_message.toLowerCase().equals("Where is XYZ Bank HQ ?".toLowerCase())){
                    SendPhoto local = new SendPhoto().setChatId(chat).setPhoto("https://image.ibb.co/nNOGGT/xyz.jpg")
                            .setCaption("We are Located at 121-5215 Daystar Plaza https://goo.gl/maps/vWnLXze6RL12");


                    try{
                        sendPhoto(local);
                    }catch (TelegramApiException ex ){
                        ex.printStackTrace();
                    }
                }else if(new_message.toLowerCase().equals("What are our interest Rates ?".toLowerCase())){
                    SendDocument document = new SendDocument().setChatId(chat).setDocument("http://www.pdf995.com/samples/pdf.pdf").setCaption("Please See" +
                            " Our Rates for long term and short term Loans and Deposits");

                    try{
                        sendDocument(document);
                    }catch (TelegramApiException ex){
                        ex.printStackTrace();
                    }

                }else if(new_message.toLowerCase().equals("Access Services through USSD".toLowerCase())){
                    response = "To Access our USSD Service,Dial *384*564# or visit http://xyzbank.digiplan.tech/";
                    SendMessage sendMessage = new SendMessage().setChatId(chat).setText(response);

                    try{
                        execute(sendMessage);
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
