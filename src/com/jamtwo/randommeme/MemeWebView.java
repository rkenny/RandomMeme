package com.jamtwo.randommeme;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.webkit.WebView;

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
		 //Log.w(TAG, "Handling touchEvent");
         return (flingHandler.onTouchEvent(event) || super.onTouchEvent(event));
     };
	
	
	private String createHTML2(byte[] jpegData)
	{
		String TAG = CLASS + "createHTML2()";
		Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
		// They display better this way for some reason
		int height = display.getWidth();
		int width = display.getHeight();
		String formatted_html;
		//Log.d(TAG, "JPEG md5: " + md5Java(new String(jpegData)));
		
		if(jpegData != null)
		{
			Log.w(TAG, "creating HTML");
			formatted_html = "<!DOCTYPE html>"+
					"<html>"+
					"<head></head>"+
					"<body style='background-color: black;'>"+
					//"<div style='position: absolute; width: "+width+"px; height: "+height+"px; display: block; margin-left: 5%; margin-right: 5%; border-style: solid; border-color: yellow; border-width: 3px; top: 45%; '>"+
					"<div >"+
					//"<img style='position: absolute; margin-left: auto; margin-right: auto; top: 30&#37;' src='data:image/jpeg;base64," + Base64.encodeToString(jpegData, Base64.DEFAULT) + "' width=" + width + " height="+height+"/>"+
					"<img style='width: 100%; height: auto' src='data:image/jpeg;base64," + Base64.encodeToString(jpegData, Base64.DEFAULT) + "' />"+
					"</div>"+
					"</body>"+
					"</html>";
		}
		else
		{
			//Log.w(TAG, "the jpeg was null");
			formatted_html = "";
		}
		
		return formatted_html;
	}
	
	 public static String md5Java(String message){
	        String digest = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] hash = md.digest(message.getBytes("UTF-8"));
	           
	            //converting byte array to Hexadecimal String
	           StringBuilder sb = new StringBuilder(2*hash.length);
	           for(byte b : hash){
	               sb.append(String.format("%02x", b&0xff));
	           }
	          
	           digest = sb.toString();
	          
	        } catch (UnsupportedEncodingException ex) {
	            //Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (NoSuchAlgorithmException ex) {
	           // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return digest;
	    }


	
	public void display(Meme meme)
	{
		String TAG = CLASS + ".display()";
		//Log.w(TAG, "displaying a meme - " + meme.getTitle() +" --["+meme.getUrl()+"]");
		byte[] jpegData = meme.getJpegData();
		String formatted_html = createHTML2(jpegData);
		//loadData(formatted_html, "text/html", "UTF-8");
		//reload();
		loadDataWithBaseURL("javascript:window.location.reload( true )", formatted_html, "text/html", "UTF-8", "");
	}

	

}
