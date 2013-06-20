package com.jamtwo.randommeme.parseengines;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class LiveMemeHtmlParseEngine extends AbstractHTMLParseEngine 
{
	private static final String CLASS = LiveMemeHtmlParseEngine.class.getSimpleName();
	Random randomGenerator = new Random();
	
	public LiveMemeHtmlParseEngine()
	{
		//http://j#.livememe.com/3113_r#
		this.setBaseUrl("http://www.livememe.com/random");
	}
	
	@Override
	public void parse() throws IOException 
	{
		String TAG = CLASS + ".parse()";
		Log.w(TAG, "starting");
		int seed = 1+ randomGenerator.nextInt(100); //2000 is just pulled out of the air
		String newUrl = "http://j"+seed+".livememe.com/3113_r"+seed; // pulled out of livememe's js
		this.setBaseUrl(newUrl);
		Log.w(TAG, "Pulling JSON from " + baseUrl);
		
		HttpClient httpclient = new DefaultHttpClient();
		
		
		HttpGet httpget = new HttpGet(baseUrl);
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		String line, html = "";
		if (entity != null) 
		{
	
		    InputStream instream = entity.getContent();
		    try 
		    {
		        BufferedReader htmlReader = new BufferedReader(new InputStreamReader(instream));
		        while ((line = htmlReader.readLine()) != null) 
		        { 
		        	html += line;  
		        }
		    } 
		    finally 
		    {
		        instream.close();
		    }
		}
		
		html = html.substring(13, html.length()-2);
		
		Log.w(TAG, "HTML: " +html.substring(0, 20) + "..."+ html.substring(html.length()-50));
		try 
		{
			JSONObject overallObject = new JSONObject(html);
			JSONObject t0 = overallObject.getJSONObject("t0");
			
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Elements media = doc.select("[src]");
//		Log.w(TAG, "There are ["+media.size()+"] elemenets in media.");
//		for (Element src : media) 
//		{
//			if (src.tagName().equals("img"))
//			{
//				int width = 100, height = 100;
//				try
//	           	{
//	           		width = Integer.parseInt(src.attr("width"));
//	           		height= Integer.parseInt(src.attr("height"));
//	           	}
//	           	catch(Exception e)
//	           	{
//	           		e.printStackTrace();
//	           	}
//	           	Meme meme = new Meme(src.attr("abs:src"), src.attr("alt"), width, height);
//	
//	           	Log.v("Parser", "width: " + src.attr("width"));
//	           	MemeStack.addMeme(meme);
//			}
//		}
	}

}
