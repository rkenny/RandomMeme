package com.jamtwo.randommeme;

import com.jamtwo.randommeme.parseengines.LiveMemeHtmlParseEngine;
import com.jamtwo.randommeme.parseengines.QuickMemeHtmlParseEngine;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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
        prevButton.setOnClickListener(this);

        mWebView = (MemeWebView)findViewById(R.id.memeView);
        mWebView.getSettings().setJavaScriptEnabled(true); //
        
        mWebView.setPadding(0, 0, 0, 0);
        mWebView.setBackgroundColor(0x000000);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        
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
//		TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
//		tvTitle = (TextView) findViewById(R.id.tvTitle);
//		tvTitle.setText(MemeStack.getCurrentMeme().getTitle());
		switch(v.getId()){
		case R.id.nextButton:
			mWebView.loadNextMeme();
			//tvTitle = (TextView) findViewById(R.id.tvTitle);
			//tvTitle.setText(MemeStack.getCurrentMeme().getTitle());
			break;
		case R.id.prevButton:
			mWebView.loadPrevMeme();
			//tvTitle = (TextView) findViewById(R.id.tvTitle);
			//tvTitle.setText(MemeStack.getCurrentMeme().getTitle());
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
    	parser.parseEngine = new QuickMemeHtmlParseEngine(); //strategy!
//    	parser.parseEngine = new LiveMemeHtmlParseEngine(); 
		parser.execute("http://quickmeme.com/random/?num="+mPageIndex);
    	parser.execute();
	}

    
    
    
}
