package com.jamtwo.randommeme;

import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class FlingHandler implements OnGestureListener
{
	private static final String CLASS = "FlingHandler";
	private MemeWebView mMemeWebView;
	
	public FlingHandler(MemeWebView mMemeWebView)
	{
		super();
		this.mMemeWebView = mMemeWebView;
	}
	
	@Override
	public boolean onDown(MotionEvent arg0) {
		String TAG = CLASS + ".onDown()";
		Log.w(TAG, "Called");
		return false;
	}

	@Override
	public boolean onFling(MotionEvent event1, MotionEvent event2, float arg2, float arg3) 
	{
		String TAG = CLASS + ".onFling()";
		
		if (event1.getRawX() > event2.getRawX() && StrictMath.abs(event1.getRawY()-event2.getRawY())<100) {
			Log.v(TAG, "Loading next meme");
			//mMemeWebView.loadNextMeme();
         } else if(event1.getRawX() < event2.getRawX() && StrictMath.abs(event1.getRawY()-event2.getRawY())<100){
        	 Log.v(TAG, "Loading previous meme");
        	// mMemeWebView.loadPrevMeme();
         } else {

         }
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
