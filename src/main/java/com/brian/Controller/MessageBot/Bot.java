package com.brian.Controller.MessageBot;
/*
  Author:  Brian Kamau
 */

import com.vdurmont.emoji.EmojiParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
        String subUrl="";
        JSONParser parser = new JSONParser();
        ArrayList<String> ans= new ArrayList<>();
        long chat = update.getMessage().getChatId();
        String message_case="";
        String user_first = update.getMessage().getChat().getFirstName();

        if(update.hasMessage() && update.getMessage().hasText()) {

           message_case = update.getMessage().getText();

           try {
               ArrayList<String> information = new ArrayList<>();
               //Object dictionary = parser.parse(new FileReader("dictionary.json"));
               //JSONObject jsonObject = (JSONObject) dictionary;
               //JSONArray answer = (JSONArray) jsonObject.get(message_case.toLowerCase());
               if (message_case.equals("/start")) {
                   new_message = "Hello " + user_first + " ,My Name is Jarvis, a bot, You can tell ask me anything " +
                           ",";
               }
               if(message_case.length()<1){
                   new_message = EmojiParser.parseToUnicode("Query is too short :cry:");
               }
               if(message_case.length()>30){
                   new_message = EmojiParser.parseToUnicode("Query is too Long :cold_sweat:");
               }else{
                   try{
                       subUrl = message_case.substring(0,1).toUpperCase()+message_case.substring(1);
                       Document document = Jsoup.connect("http://en.wikipedia.org/wiki/"+subUrl).get();
                       Elements title = document.select("H1");
                       Elements tags = document.select("p");
                      /* if(tags.toString().length()>4096){
                           new_message = "Response Length Surpassed";
                       }*/
                      if(tags.text().length()<200){
                          new_message = tags.text().substring(0,200);
                      }else{
                          new_message = tags.text().substring(0,1000);
                      }

                       System.out.println(new_message);
                   }catch(Exception ex){
                       new_message = "Could Not Find Query";
                       ex.printStackTrace();
                   }
               }

               /*else if(answer!=null){
                   new_message = answer.toString();
               }else{
                   new_message = EmojiParser.parseToUnicode("Word Has Not Been found :cry:");
                   System.out.println(new_message);
               }*/

      /*     }catch(FileNotFoundException ex){
               ex.printStackTrace();
               new_message = "Word Not Found";

           } catch (ParseException e) {
               e.printStackTrace();
               new_message = "Word Not Found";

           } catch (IOException e) {
               e.printStackTrace();
               new_message = "Word Not Found";

           }*/
           }catch(Exception ex){
               ex.printStackTrace();
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

