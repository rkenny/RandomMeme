package com.jamtwo.randommeme;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class HTMLParser {

	String TAG = "HTMLParser";
	public void parseHTML(String url) throws IOException{
		
        Document doc = Jsoup.connect(url).get();
        Elements media = doc.select("[src]");
        Log.v(TAG, "media: " + media.size());
        for (Element src : media) {
            if (src.tagName().equals("img")){
            	int width = 100, height = 100;
            	try{
            		width = Integer.parseInt(src.attr("width"));
            		height= Integer.parseInt(src.attr("height"));
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            	Meme meme = new Meme(src.attr("abs:src"), src.attr("alt"), width, height);

            	Log.v("Parser", "width: " + src.attr("width"));
            	MemeStack.addMeme(meme);
            }
        }
    }
}

