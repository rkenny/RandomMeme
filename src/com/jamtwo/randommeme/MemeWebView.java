package com.jamtwo.randommeme;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.TextView;

public class MemeWebView extends WebView{
	public static final String CLASS = "MemeWebView";
	private GestureDetector flingHandler;
	private Context context;
	
	
	public MemeWebView(Context context) {
		super(context);
		initView(context);
	}
	
	public MemeWebView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
		initView(context);
	}
	
	
	
	public void initView(Context context)
	{
		this.context = context;
		if(!this.isInEditMode())
		{
			flingHandler = new GestureDetector(context, new FlingHandler(this));
		}	
	}
	
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
			updateTvTitle();
			
		}
		else
		{
			MemeStack.loadMoreMemes();
		}
	}

	public void loadPrevMeme() {
		String url = MemeStack.getPrevMeme().getUrl();
		createHTML(url);
		updateTvTitle();
	}
	
	public void updateTvTitle()
	{
		TextView tvTitle;
		tvTitle = (TextView) ((Activity)context).findViewById(R.id.tvTitle);
		if(MemeStack.getCurrentMeme() != null)
		{	
			float textSize;
			String currentMemeTitle =MemeStack.getCurrentMeme().getTitle();
			if(currentMemeTitle == null)
			{
				currentMemeTitle = "Loading...";
			}
			//22 chars?
			if(currentMemeTitle.length() < 22)
			{
				textSize = 15;
			}
			else if(currentMemeTitle.length() < 35)
			{
				textSize = 13;
			}
			else
			{
				textSize = 11;
			}

			tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
			tvTitle.setText(currentMemeTitle);
		}
	}

}
