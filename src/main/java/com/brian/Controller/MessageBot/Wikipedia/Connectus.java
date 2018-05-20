package com.brian.Controller.MessageBot.Wikipedia;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Connectus {
    public boolean getConnection() {
        boolean success = true;
        try {
            Document connect = Jsoup.connect("http://en.wikipedia.org/").get();
        }catch(IOException ex){
            success = false;
            ex.printStackTrace();
        }
        return success;
    }
}
