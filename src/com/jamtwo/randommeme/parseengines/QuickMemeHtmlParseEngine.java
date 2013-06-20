package com.jamtwo.randommeme.parseengines;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jamtwo.randommeme.Meme;
import com.jamtwo.randommeme.MemeStack;

import android.util.Log;

public class QuickMemeHtmlParseEngine extends AbstractHTMLParseEngine
{
	public QuickMemeHtmlParseEngine()
	{
		this.setBaseUrl("http://quickmeme.com/random/?num=1");
	}
	
	public void parse() throws IOException
	{
	   Document doc = Jsoup.connect(baseUrl).get();
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
	
}
