package com.jamtwo.randommeme;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jamtwo.randommeme.asynctasks.HTMLParserAsyncTask;
import com.jamtwo.randommeme.parseengines.LiveMemeHtmlParseEngine;

public class MainActivity extends Activity implements OnClickListener, ILoadMoreMemesListener {

	private MemeWebView mWebView;
	private int mPageIndex = 0;
	private int mPageIndexMax = 150;
	private static final String CLASS = "MainActivity";
	
    private GestureDetector flingHandler;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MemeStack.getInstance().registerLoadMoreMemesListener(this);
        
        initView();
    }
    
    private void initView(){
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButton);
        nextButton.setOnClickListener((OnClickListener) this);
        
        ImageButton prevButton = (ImageButton) findViewById(R.id.prevButton);
        prevButton.setOnClickListener((OnClickListener) this);

        mWebView = (MemeWebView)findViewById(R.id.memeView);
        mWebView.getSettings().setJavaScriptEnabled(true); //
        
        mWebView.setPadding(0, 0, 0, 0);
        mWebView.setBackgroundColor(0x000000);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
        
        flingHandler = new GestureDetector(this, new FlingHandler(mWebView));
        loadMoreMemes();
        //mWebView.loadNextMeme();
        
    }

    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.nextButton:
			displayNextMeme();
			break;
		case R.id.prevButton:
			displayPrevMeme();
			break;
		}
		
	}
	
	public void loadMoreMemes(){
		String TAG = CLASS + ".loadMoreMemes";
		
		Log.v(TAG, "loading more memes");
		mPageIndex++;
		if (mPageIndex > mPageIndexMax){
			mPageIndex=0;
		}
		
    	HTMLParserAsyncTask parser = new HTMLParserAsyncTask(this, mWebView);
    	//parser.parseEngine = new QuickMemeHtmlParseEngine(); //strategy!
    	parser.parseEngine = new LiveMemeHtmlParseEngine();
    	parser.execute();
	}

	//called when the async'ly downloaded jpeg is ready
    public void updateWebView()
    {
    	String TAG = CLASS + ".updateWebView()";
		Log.w(TAG, "updating web view");
    	//mWebView.updateWebView();
    }
    
    public void displayNextMeme()
    {
    	if(MemeStack.nextMemeIsReady())
    	{
    		Meme meme = MemeStack.getNextMeme();
    		
    		mWebView.display(meme);
    		updateTvTitle(meme.getTitle());
    		mWebView.invalidate();
    		if(MemeStack.atLastMeme())
    		{
    			downloadAnotherMeme();
    		}
    		
    	}
    	else
    	{
    		Toast.makeText(this, "Getting more memes", Toast.LENGTH_LONG);
    	}
    }
    
    
    public void displayPrevMeme()
    {
    	if(MemeStack.prevMemeIsReady())
    	{
    		Meme meme = MemeStack.getPrevMeme();
    		
    		mWebView.display(meme);
    		updateTvTitle(meme.getTitle());
    		mWebView.invalidate();
    		//downloadAnotherMeme();
    	}
    	else
    	{
    		Toast.makeText(this, "There are no previous memes", Toast.LENGTH_LONG);
    	}
    }
    
    public void updateTvTitle(String currentMemeTitle)
    {
    	String TAG = CLASS + ".updateTvTitle()";
    	Log.v(TAG, "updating with title" + currentMemeTitle);
		TextView tvTitle;
		tvTitle = (TextView) this.findViewById(R.id.tvTitle);
		
		float textSize;
		
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

	@Override
	public void downloadAnotherMeme() {
		
		String TAG = CLASS + ".downloadAnotherMeme";
		
		Log.v(TAG, "loading more memes");
    	HTMLParserAsyncTask parser = new HTMLParserAsyncTask(this, mWebView);
    	//parser.parseEngine = new QuickMemeHtmlParseEngine(); //strategy!
    	parser.parseEngine = new LiveMemeHtmlParseEngine();
    	parser.execute();
		
	}
    
}
