package com.brian.Controller.EmojiBot;
import com.brian.Model.BotCredentials;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;

public class EBot extends TelegramLongPollingBot {
    BotCredentials botCredentials = new BotCredentials();
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String first_name = update.getMessage().getChat().getFirstName();
            String last_name = update.getMessage().getChat().getLastName();
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            String answer = EmojiParser.parseToUnicode("Here is a smile emoji: :smile:\n\n Here is alien emoji: :alien:");
            String answer2 = EmojiParser.parseToAliases(message_text);
            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage().setChatId(chat_id).setText(answer2);
            try{
                sendMessage(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }







        }




    }

    @Override
    public String getBotUsername() {
      String username="";
      try{
          username = botCredentials.getUsername();
      }catch(IOException e ){
          e.printStackTrace();
      }
      return username;
    }

    @Override
    public String getBotToken() {
        String token ="";
        try{
            token = botCredentials.getToken();
        }catch(IOException e){
            e.printStackTrace();
        }
        return token;
    }
}
