package com.jamtwo.randommeme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, ILoadMoreMemesListener {

	private MemeWebView mWebView;
	private int mPageIndex = 0;
	private int mPageIndexMax = 150;
	private static final String CLASS = "MainActivity";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MemeStack.getInstance().registerLoadMoreMemesListener(this);
        initView();
        
    }
    
    private void initView(){
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener((OnClickListener) this);
        
        Button prevButton = (Button) findViewById(R.id.prevButton);
        prevButton.setOnClickListener(this);

        
        mWebView = (MemeWebView)findViewById(R.id.memeView);
        mWebView.getSettings().setJavaScriptEnabled(true); //
        
        mWebView.setPadding(0, 0, 0, 0);
        
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
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
		TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
		switch(v.getId()){
		case R.id.nextButton:
			mWebView.loadNextMeme();
			tvTitle = (TextView) findViewById(R.id.tvTitle);
			tvTitle.setText(MemeStack.getCurrentMeme().getTitle());
			break;
		case R.id.prevButton:
			mWebView.loadPrevMeme();
			tvTitle = (TextView) findViewById(R.id.tvTitle);
			tvTitle.setText(MemeStack.getCurrentMeme().getTitle());
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
    		
		parser.execute("http://quickmeme.com/random/?num="+mPageIndex);
	}

    
    
    
}
