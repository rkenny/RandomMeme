package com.jamtwo.randommeme;

import java.util.ArrayList;

import android.util.Log;


public class MemeStack {
	
	private static ILoadMoreMemesListener loadMoreMemesListener;
	private static ArrayList<Meme> mMemes;
	private static MemeStack mStack =null;
	private static int mCurrentIndex;
	
	// Load more random memes when we get to X memes before the last one
	private static int RELOAD_INDEX = 3;
	
	private static String CLASS = "MemeStack";
	
	protected MemeStack(){
		mMemes = new ArrayList<Meme>();
	}
	
	public static MemeStack getInstance(){
		if (mStack==null){
			mStack = new MemeStack();
		}
		return mStack;
	}
	
	public static Meme getNextMeme(){
		if (mMemes.size()>0 && mCurrentIndex<mMemes.size()){
			if (mCurrentIndex == mMemes.size()-RELOAD_INDEX){		//Load more memes
				loadMoreMemesListener.loadMoreMemes();
			}
			mCurrentIndex++;
			return mMemes.get(mCurrentIndex);
		}
		else{
			mCurrentIndex=0;
			return null;
		}
	}
	
	public static Meme getPrevMeme(){
		if (mMemes.size()>0 && mCurrentIndex>0){
			mCurrentIndex --;
			return mMemes.get(mCurrentIndex);
		} else{
			mCurrentIndex=0;
			return mMemes.get(mCurrentIndex);
		}
	}
	
	public static void addMeme(Meme meme){
		String TAG = CLASS + ".addMeme";
		mMemes.add(meme);
		
		Log.w(TAG, " complete");
	}
	
	public static Meme getCurrentMeme(){
		if (mMemes.size()>(mCurrentIndex)){
			return mMemes.get(mCurrentIndex);
		}
		return null;
	}
	
	public void registerLoadMoreMemesListener(ILoadMoreMemesListener listener){
		loadMoreMemesListener = listener;
	}
	
	

}
