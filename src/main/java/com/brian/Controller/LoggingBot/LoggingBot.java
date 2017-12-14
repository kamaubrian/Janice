package com.brian.Controller.LoggingBot;
import com.brian.Model.BotCredentials;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggingBot extends TelegramLongPollingBot {
    BotCredentials botmodel = new BotCredentials();
    private File logfile;
    private BufferedWriter logFileWriter;

    public LoggingBot(){
        logfile = new File("./log");
            try {
                logFileWriter = new BufferedWriter(new FileWriter(logfile));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
            String user_first_name = update.getMessage().getChat().getFirstName();
            String user_last_name = update.getMessage().getChat().getLastName();
            String user_username = update.getMessage().getChat().getUserName();
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            String answer="";
            long chat_id = update.getMessage().getChatId();
            if(message_text.toLowerCase().equals("who am i?")){
                answer ="Your Name is "+user_first_name+" "+user_last_name;
            }else{
                answer="Bot Development in Progress";
            }

            SendMessage message = new SendMessage().setChatId(chat_id).setText(answer);
            log(user_first_name,user_last_name,user_id,message_text,answer);
            try{
                execute(message);
            }catch(TelegramApiException e){
                e.printStackTrace();
            }
        }


    }

    @Override
    public String getBotUsername() {
       String username="";
       try{
           username = botmodel.getUsername();
       }catch(IOException e){
           e.printStackTrace();
       }
       return username;
    }

    @Override
    public String getBotToken() {
       String token="";
       try{
           token = botmodel.getToken();
       }catch(Exception e){
           e.printStackTrace();
       }
       return token;
    }

    private void log(String first_name,String last_name,long user_id,String text,String bot_answer){
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + text);
        System.out.println("Bot answer: \n Text - " + bot_answer);

        try{
            logFileWriter = new BufferedWriter(new FileWriter(logfile));
            logFileWriter.write("------------------------");
            logFileWriter.write("Written On "+dateFormat.format(date));
            logFileWriter.write("Message From: "+first_name+""+last_name +"User ID: "+user_id);
            logFileWriter.write("Text:" +text);
            logFileWriter.write("Bot Answer: "+ bot_answer);
            logFileWriter.write("------------------------");

        }catch(IOException e ){
            e.printStackTrace();
        }
    }
}
