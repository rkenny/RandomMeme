package com.jamtwo.randommeme;

import java.io.IOException;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, ILoadMoreMemesListener {

	private MemeWebView mWebView;
	private int mPageIndex = 0;
	private int mPageIndexMax = 150;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        URLStack.getInstance().registerLoadMoreMemesListener(this);
        initView();
        
    }
    
    private void initView(){
    	loadMoreMemes();
    	
        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener((OnClickListener) this);

        
        mWebView = (MemeWebView)findViewById(R.id.memeView);
        mWebView.getSettings().setJavaScriptEnabled(true); //
        
        mWebView.setPadding(0, 0, 0, 0);
        
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        
        mWebView.loadNextMeme();
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
			mWebView.loadNextMeme();
			TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
			tvTitle.setText(URLStack.getCurrentMeme().getTitle());
		}
		
	}
	
	public void loadMoreMemes(){
		mPageIndex++;
		if (mPageIndex > mPageIndexMax){
			mPageIndex=0;
		}
    	HTMLParser parser = new HTMLParser();
    	try {
			parser.parseHTML("http://quickmeme.com/random/?num="+mPageIndex);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
    
    
}
