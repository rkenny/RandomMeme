package com.jamtwo.randommeme;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class MemeWebView extends WebView{
	
	public MemeWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public MemeWebView(Context context, AttributeSet attributeSet){
		super(context, attributeSet);
	}//
	
	
	public void loadNextMeme(){
		loadUrl(URLStack.getNextUrl());
		
	}
	
	

}
