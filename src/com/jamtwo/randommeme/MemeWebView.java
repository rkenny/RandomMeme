package com.jamtwo.randommeme;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Base64;
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
		// They display better this way for some reason
		int height = display.getWidth();
		int width = display.getHeight();
		
		String formatted_html = "<!DOCTYPE html>"+
				"<html>"+
				"<head></head>"+
				"<body style='background-color: black;'>"+
				"<div style='position: absolute; width: "+width+"px; height: "+height+"px; display: block; margin-left: 5%; margin-right: 5%; border-style: solid; border-color: yellow; border-width: 3px; top: 45%; '>"+
				"<img stype='position: relative;' src=" + url + " width=" + width + " height="+height+"/>"+
				"</div>"+
				"</body>"+
				"</html>";
				
		loadData(formatted_html, "text/html", "UTF-8");
	}
	
	private void createHTML2(byte[] jpegData){
		Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
		// They display better this way for some reason
		int height = display.getWidth();
		int width = display.getHeight();
		String formatted_html;
		if(jpegData != null)
		{
		
			formatted_html = "<!DOCTYPE html>"+
					"<html>"+
					"<head></head>"+
					"<body style='background-color: black;'>"+
					"<div style='position: absolute; width: "+width+"px; height: "+height+"px; display: block; margin-left: 5%; margin-right: 5%; border-style: solid; border-color: yellow; border-width: 3px; top: 45%; '>"+
					"<img stype='position: relative;' src='data:image/jpeg;base64," + Base64.encodeToString(jpegData, Base64.DEFAULT) + "' width=" + width + " height="+height+"/>"+
					"</div>"+
					"</body>"+
					"</html>";
		}
		else
		{
			formatted_html = "";
		}
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
		if (MemeStack.hasNextMeme())
		{
			//String url = MemeStack.getNextMeme().getUrl();
			byte[] jpegData = MemeStack.getNextMeme().getJpegData();
			if(jpegData != null)
			{
				createHTML2(jpegData);
			}
			else
			{
				Log.w("MemeWebView", "jpegdata null");
			}
			//createHTML(url);
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
