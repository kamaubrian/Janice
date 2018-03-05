package com.brian.Controller.MessageBot;
/*
  Author:  Brian Kamau
 */

import com.vdurmont.emoji.EmojiParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import com.brian.Model.BotCredentials;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bot  extends TelegramLongPollingBot{
    BotCredentials botmodel = new BotCredentials();

    public void onUpdateReceived(Update update) {
        String new_message="";
        JSONParser parser = new JSONParser();
        ArrayList<String> ans= new ArrayList<>();
        long chat = update.getMessage().getChatId();
        String message_case="";
        String user_first = update.getMessage().getChat().getFirstName();

        if(update.hasMessage() && update.getMessage().hasText()) {

           message_case = update.getMessage().getText();
           try{
               Object dictionary = parser.parse(new FileReader("dictionary.json"));
               JSONObject jsonObject = (JSONObject) dictionary;
               JSONArray answer = (JSONArray) jsonObject.get(message_case.toLowerCase());
               if(message_case.equals("/start")){
                   new_message ="Hello "+user_first+" ,My Name is Jarvis, a bot, You can tell ask me any " +
                           "word, I will search for its meaning";
               }
               else if(answer!=null){
                   new_message = answer.toString();
               }else{
                   new_message = EmojiParser.parseToUnicode("Word Has Not Been found :cry:");
                   System.out.println(new_message);
               }

           }catch(FileNotFoundException ex){
               ex.printStackTrace();
               new_message = "Word Not Found";

           } catch (ParseException e) {
               e.printStackTrace();
               new_message = "Word Not Found";

           } catch (IOException e) {
               e.printStackTrace();
               new_message = "Word Not Found";

           }

        }
            SendMessage message = new SendMessage()
                    .setChatId(chat)
                    .setText(new_message);
            try{
                execute(message);
                System.out.println("Message Sent From: "+user_first);
                System.out.println(getDateTime());
                System.out.println(message_case);
            }catch(TelegramApiException ex){
                new_message = "Word Not Found";

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
    public String getInstanceUsername() throws TelegramApiException{
        return null;
    }

    public String testCaseString(){return "abcd";}

}

