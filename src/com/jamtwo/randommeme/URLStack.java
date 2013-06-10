package com.jamtwo.randommeme;

import java.util.ArrayList;


public class URLStack {
	
	private static ILoadMoreMemesListener loadMoreMemesListener;
	private static ArrayList<Meme> mMemes;
	private static URLStack mStack =null;
	private static int mCurrentIndex;
	
	// Load more random memes when we get to X memes before the last
	private static int RELOAD_INDEX = 3;
	
	protected URLStack(){
		mMemes = new ArrayList<Meme>();
	}
	
	public static URLStack getInstance(){
		if (mStack==null){
			mStack = new URLStack();
		}
		return mStack;
	}
	
	public static String getNextUrl(){
		if (mMemes.size()>0 && mCurrentIndex<=mMemes.size()-1){
			String next =  mMemes.get(mCurrentIndex).getUrl();
			if (mCurrentIndex == mMemes.size()-RELOAD_INDEX){		//Load more memes
				loadMoreMemesListener.loadMoreMemes();
			}
			mCurrentIndex++;
			return next;
		}
		else{
			mCurrentIndex=0;
			return mMemes.get(mCurrentIndex).getUrl();
		}
	}
	
	public static void addMeme(Meme meme){
		mMemes.add(meme);
	}
	
	public static Meme getCurrentMeme(){
		if (mMemes.size()>(mCurrentIndex-1)){
			return mMemes.get(mCurrentIndex-1);
		}
		return null;
	}
	
	public void registerLoadMoreMemesListener(ILoadMoreMemesListener listener){
		loadMoreMemesListener = listener;
	}
	

}
