package com.jamtwo.randommeme;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jamtwo.randommeme.parseengines.HTMLParserEngine;
import com.jamtwo.randommeme.parseengines.QuickMemeHtmlParseEngine;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class HTMLParserAsyncTask extends AsyncTask<String, Void, String>{
	
	Context context;
	MemeWebView mWebView;
	HTMLParserEngine parseEngine; //= new QuickMemeHtmlParseEngine();
	
	public HTMLParserAsyncTask(Context context, MemeWebView mWebView)
	{
		this.context = context;
		this.mWebView = mWebView;
	}
	
	public void parseHTML() throws IOException
	{
		//parseEngine.setBaseUrl(url);
		parseEngine.parse();
    }
	
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		//if (params!=null && params[0]!=null){
			try {
				//parseHTML(params[0]);
				parseHTML();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//}
		return null;
	}

	@Override
	protected void onPostExecute(String result) 
	{
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		mWebView.loadNextMeme(); // if they lose their internet connection, this will start to loop and crash the app
	}
}

