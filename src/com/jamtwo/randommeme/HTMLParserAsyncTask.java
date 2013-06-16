package com.jamtwo.randommeme;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.util.Log;

public class HTMLParserAsyncTask extends AsyncTask<String, Void, String>{

	public void parseHTML(String url) throws IOException{
		
        Document doc = Jsoup.connect(url).get();
        Elements media = doc.select("[src]");
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
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		if (params!=null && params[0]!=null){
			try {
				parseHTML(params[0]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}

