package com.jamtwo.randommeme;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

<<<<<<< HEAD:src/com/jamtwo/randommeme/HTMLParserAsyncTask.java
=======
import android.content.Context;
>>>>>>> Converted to AsyncTask:src/com/jamtwo/randommeme/HTMLParser.java
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

<<<<<<< HEAD:src/com/jamtwo/randommeme/HTMLParserAsyncTask.java
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
=======
public class HTMLParser extends AsyncTask<String, Void, String> {
	
	Meme meme;
	private Context mContext;
	private MemeWebView mWebView;
	Document doc;
	String TAG = "HTMLParser";
	
	public HTMLParser(Context context, MemeWebView mWebView)
	{
		this.mContext=context;
	    this.mWebView=mWebView;
	}
	public void parseHTML(String url) throws IOException{
		
        doc = Jsoup.connect(url).get();
       
    }
	
	
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		Log.w(TAG, "parsing HTML");
		try {
			parseHTML(arg0[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return null;
	}


	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		 Elements media = doc.select("[src]");
	        Log.v(TAG, "media: " + media.size());
	        for (Element src : media) {
	            if (src.tagName().equals("img"))
	            {
	            	int width = 100, height = 100;
	            	try{
	            		width = Integer.parseInt(src.attr("width"));
	            		height= Integer.parseInt(src.attr("height"));
	            	}catch(Exception e){
	            		e.printStackTrace();
	            	}
	            	meme = new Meme(src.attr("abs:src"), src.attr("alt"), width, height);
	            	Log.v("Parser", "width: " + src.attr("width"));
	            	Log.w(TAG, "adding meme to MemeStack");
	        		MemeStack.addMeme(meme);
	        		
	            }
	        }
		
		this.mWebView.loadNextMeme();
        
	}
	
	
>>>>>>> Converted to AsyncTask:src/com/jamtwo/randommeme/HTMLParser.java
}

