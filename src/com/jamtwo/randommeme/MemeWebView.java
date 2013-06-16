package com.jamtwo.randommeme;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.webkit.WebView;

public class MemeWebView extends WebView{
	public static final String CLASS = "MemeWebView";
	public MemeWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MemeWebView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
	}//
	
	private void createHTML(String url){
		Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		String formatted_html = "<!DOCTYPE html>"+
				"<html>"+
				"<head></head>"+
				"<body>"+
				"<div align=center>"+
				"<img src=" + url + " width=" + width + " height="+height+"/>"+
				"</div>"+
				"</body>"+
				"</html>";
				
		loadData(formatted_html, "text/html", "UTF-8");
	}
	
	public void loadNextMeme()
	{
		String TAG = CLASS + ".loadNextMeme";
		Log.v(TAG, "called");
		if (MemeStack.hasNextMeme()){
			String url = MemeStack.getNextMeme().getUrl();
			createHTML(url);
		}
		else
		{
			MemeStack.loadMoreMemes();
		}
	}

	public void loadPrevMeme() {
		String url = MemeStack.getPrevMeme().getUrl();
		createHTML(url);
		
	}

}
