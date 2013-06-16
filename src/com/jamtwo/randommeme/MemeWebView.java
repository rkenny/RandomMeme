package com.jamtwo.randommeme;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

public class MemeWebView extends WebView{
	public static final String CLASS = "MemeWebView";
	private GestureDetector flingHandler;
	
	public MemeWebView(Context context) {
		super(context);
		
		if(!this.isInEditMode())
		{
			flingHandler = new GestureDetector(context, new FlingHandler(this));
		}
		// TODO Auto-generated constructor stub
	}
	
	public MemeWebView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		if(!this.isInEditMode())
		{
			flingHandler = new GestureDetector(context, new FlingHandler(this));
		}	}//
	
	 public boolean onTouchEvent(MotionEvent event) {
		 String TAG = CLASS + ".onTouchEvent()";
		 Log.w(TAG, "Handling touchEvent");
         return (flingHandler.onTouchEvent(event) || super.onTouchEvent(event));
     };
	
	private void createHTML(String url){
		Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		
		
		String formatted_html = "<!DOCTYPE html>"+
				"<html>"+
				"<head></head>"+
				"<body style='background-color: black;'>"+
				"<div style='width: "+width+"px; height: "+height+"px; display: block; margin-left: 15%; margin-right: 15%; border-style: solid; border-color: yellow; border-width: 3px; '>"+
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
		if(this.isInEditMode())
		{
			return;
		}
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
