package com.jamtwo.randommeme;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MemeWebView extends WebView{
	
	private static final String QUICK_MEME_BASE = "http://www.quickmeme.com/";
	private static final String RANDOM_LIST_QUERY = "random/?num=";
	private static final String IMAGE_VIEW = "http://i.qkme.me/";
	
	public MemeWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void loadNextMeme(){
		URLStack stack = URLStack.getInstance();
		loadUrl( IMAGE_VIEW + stack.getInstance().getNextUrl());
	}
	
	

}
