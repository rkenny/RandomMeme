package com.jamtwo.randommeme.parseengines;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.content.Context;
import android.util.Log;

import com.jamtwo.randommeme.Meme;
import com.jamtwo.randommeme.MemeStack;

public class QuickMemeHtmlParseEngine extends AbstractHTMLParseEngine
{
	public static final String CLASS = "QuickMemeHtmlParseEngine";
	public QuickMemeHtmlParseEngine(Context context)
	{
		this.setBaseUrl("http://quickmeme.com/random/?num=1");
		this.setContext(context);
	}
	
	public void parse() throws IOException
	{
		String TAG = CLASS + ".parse()";
	   Document doc = Jsoup.connect(baseUrl).get();
       Elements media = doc.select("[src]");
       for (Element src : media) {
           if (src.tagName().equals("img"))
           {
        	   if(src.attr("abs:src").contains("qkme.me"))
        	   {
		           	int width = 100, height = 100;
		           	String URL = "";
		           	try
		           	{
		           		width = Integer.parseInt(src.attr("width"));
		           		height= Integer.parseInt(src.attr("height"));
		           		URL = src.attr("src");
		           	}
		           	catch(Exception e)
		           	{
		           		e.printStackTrace();
		           	}
		           	Meme meme = new Meme(getContext(), src.attr("abs:src"), src.attr("alt"), width, height);
		           	
		           	Log.v(TAG, "image: [" + src.attr("abs:src") + "]");
		           	MemeStack.addMeme(meme);
        	   }
           }
       }
	}
	
}
