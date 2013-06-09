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
            	URLStack.addUrl(src.attr("abs:src"));
            	Log.v(TAG, "adding_url: " + src.attr("abs:src"));
            }
        }
    }
}

