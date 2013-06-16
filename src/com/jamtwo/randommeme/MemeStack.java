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
	
	private static final String CLASS = "MemeStack";
	
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
		String TAG = CLASS + ".getNextMeme";
		
		Log.v(TAG, "previous mCurrentIndex: " + mCurrentIndex);
		if (mMemes.size()>0 && mCurrentIndex<mMemes.size())
		{
//			if (mCurrentIndex+1 == mMemes.size()-RELOAD_INDEX)
//			{ //Load more memes
//				Log.v(TAG, "Loading more memes");
//				loadMoreMemesListener.loadMoreMemes();
//			}
			mCurrentIndex++;
		}
		else
		{
			mCurrentIndex=0;
			return null;
		}
		
		Log.v(TAG, "returning mCurrentIndex: " + mCurrentIndex);
		return mCurrentIndex > 0 ? mMemes.get(mCurrentIndex) : null;
	}
	
	public static boolean hasNextMeme()
	{
		String TAG = CLASS + ".hasNextMeme";
		Log.v(TAG, "called");
		
		return (mMemes.size()>0 && mCurrentIndex<mMemes.size() && !(mCurrentIndex+1 == mMemes.size()-RELOAD_INDEX));	
	}
	
	public static void loadMoreMemes()
	{
		String TAG = CLASS + ".loadMoreMemes";
		Log.v(TAG, "called");
		
		loadMoreMemesListener.loadMoreMemes();
	}
	
	public static Meme getPrevMeme(){
		String TAG = CLASS + ".getPrevMeme";

		Log.v(TAG, "previous mCurrentIndex: " + mCurrentIndex);

		if (mMemes.size()>0 && mCurrentIndex>0)
		{
			mCurrentIndex--;
			
		} else{
			mCurrentIndex=0;			
		}
		Log.v(TAG, "returning mCurrentIndex: " + mCurrentIndex);

		return mMemes.get(mCurrentIndex);
	}
	
	public static void addMeme(Meme meme){
		mMemes.add(meme);
	}
	
	public static Meme getCurrentMeme(){
		String TAG = CLASS + ".getCurrentMeme";

		if (mMemes.size()>(mCurrentIndex)){
			Log.v(TAG, "returning mCurrentIndex: " + mCurrentIndex);
			return mMemes.get(mCurrentIndex);
		}
		return null;
	}
	
	public void registerLoadMoreMemesListener(ILoadMoreMemesListener listener){
		loadMoreMemesListener = listener;
	}
	

}
