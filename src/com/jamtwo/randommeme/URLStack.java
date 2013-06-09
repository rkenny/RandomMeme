package com.jamtwo.randommeme;

import java.util.ArrayList;

public class URLStack {
	
	private static ArrayList<String> mUrls;
	private static URLStack mStack =null;
	private static int mCurrentIndex;
	
	// Load more random images when we get to 3 images before the last
	private static int RELOAD_INDEX = 3;
	
	protected URLStack(){
		mUrls = new ArrayList<String>();
	}
	
	public static URLStack getInstance(){
		if (mStack==null){
			mStack = new URLStack();
		}
		return mStack;
	}
	
	public static String getNextUrl(){
		if (mUrls.size()>0 && mCurrentIndex<=mUrls.size()-1){
			String next =  mUrls.get(mCurrentIndex);
			if (mCurrentIndex == mUrls.size()-RELOAD_INDEX){
				
			}
			mCurrentIndex++;
			return next;
		}
		else{
			mCurrentIndex=0;
			return mUrls.get(mCurrentIndex);
		}
	}
	
	public static void addUrl(String url){
		mUrls.add(url);
	}
	

}
