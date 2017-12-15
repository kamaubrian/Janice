package com.brian.Controller.CallBack;
import com.brian.Model.BotCredentials;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class CallBack extends TelegramLongPollingBot{
    BotCredentials botCredentials = new BotCredentials();
    @Override
    public void onUpdateReceived(Update update) {

        if(update.hasMessage() && update.getMessage().hasText()){
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            if(update.getMessage().getText().equals("/start")){
                SendMessage message = new SendMessage().setChatId(chat_id).setText("You Sent /start");
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsinLine = new ArrayList<>();
                List<InlineKeyboardButton> rowonline = new ArrayList<>();
                rowonline.add(new InlineKeyboardButton().setText("update message txt").setCallbackData("update_message_text"));
                rowsinLine.add(rowonline);

                inlineKeyboardMarkup.setKeyboard(rowsinLine);
                message.setReplyMarkup(inlineKeyboardMarkup);
                try{
                    sendMessage(message);
                }catch (TelegramApiException e){
                    e.printStackTrace();
                }
            }else if(update.hasCallbackQuery()){
                String call_data = update.getCallbackQuery().getData();
                long message_id = update.getCallbackQuery().getMessage().getMessageId();
                long chat_i = update.getCallbackQuery().getMessage().getChatId();

                if(call_data.equals("update_message_text")){
                    String answer = "Updated Message Text";
                    EditMessageText new_message = new EditMessageText().setChatId(chat_i)
                            .setMessageId(toIntExact(message_id)).setText(answer);
                    try{
                        execute(new_message);
                    }catch(TelegramApiException e){
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public String getBotUsername() {
        String username="";
        try{
            username = botCredentials.getUsername();
        }catch(IOException e){
            e.printStackTrace();
        }
        return username;
    }

    @Override
    public String getBotToken() {
        String token ="";
        try{
            token = botCredentials.getToken();

        }catch(IOException ex){
            ex.printStackTrace();
        }
        return token;
    }
}
