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

import android.content.Context;

import com.jamtwo.randommeme.Meme;
import com.jamtwo.randommeme.MemeStack;

public class LiveMemeHtmlParseEngine extends AbstractHTMLParseEngine 
{
	private static final String CLASS = LiveMemeHtmlParseEngine.class.getSimpleName();
	Random randomGenerator = new Random();
	
	public LiveMemeHtmlParseEngine(Context context)
	{
		//http://j#.livememe.com/3113_r#
		this.setBaseUrl("http://www.livememe.com/random");
		this.setContext(context);
	}
	
	@Override
	public void parse() throws IOException 
	{
		String TAG = CLASS + ".parse()";
		//Log.w(TAG, "starting");
		//int seed = 1+ randomGenerator.nextInt(100); //2000 is just pulled out of the air
		int seed = 1;
		int numberOfMemesToPull = 10;
		String newUrl = "http://j"+seed+".livememe.com/3113_r"+numberOfMemesToPull; // pulled out of livememe's js
		this.setBaseUrl(newUrl);
		//Log.w(TAG, "Pulling JSON from " + baseUrl);
		
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
		
		//Log.w(TAG, "HTML: " +html.substring(0, 20) + "..."+ html.substring(html.length()-50));
		try 
		{
			JSONObject overallObject = new JSONObject(html);
			JSONObject t0 = overallObject.getJSONObject("t0");
			String jpegUrl = "http://t1.livememe.com/" + t0.get("id") + ".jpg";
			String memeType = t0.getString("name");
			int width = t0.getInt("t_w");
			int height = t0.getInt("t_h");
			//Log.w(TAG, "downloading jpg: " + jpegUrl);
			
			Meme meme = new Meme(getContext(), jpegUrl, memeType, width, height);
           	MemeStack.addMeme(meme);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
