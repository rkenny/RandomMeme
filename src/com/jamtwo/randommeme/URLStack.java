package com.jamtwo.randommeme;

import java.util.ArrayList;
import java.util.Currency;

public class URLStack {
	
	private static ArrayList<String> mUrls;
	private static URLStack mStack =null;
	private static int mCurrentIndex;
	
	public URLStack(){
		//singleton
	}
	
	public static URLStack getInstance(){
		if (mStack!=null){
			return mStack;
		}
		mUrls = new ArrayList<String>();
		return new URLStack();
	}
	
	public static String getNextUrl(){	
		String next =  mUrls.get(mCurrentIndex);
		mCurrentIndex++;
		return next;
	}
	
	public void addUrl(String url){
		mUrls.add(url);
	}
	

}
