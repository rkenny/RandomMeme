package com.jamtwo.randommeme;

import java.io.IOException;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
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
//        //mWebView.loadNextMeme(); /* !!! */
        
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
		mPageIndex++;
		if (mPageIndex > mPageIndexMax){
			mPageIndex=0;
		}
		
<<<<<<< HEAD
    	HTMLParserAsyncTask parser = new HTMLParserAsyncTask();
    		
		parser.execute("http://quickmeme.com/random/?num="+mPageIndex);
		
=======
    	HTMLParser parser = new HTMLParser(this, mWebView);
    	parser.execute("http://quickmeme.com/random/?num="+mPageIndex);
//    	try {
//			parser.parseHTML("http://quickmeme.com/random/?num="+mPageIndex);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
>>>>>>> Converted to AsyncTask
	}

    
    
    
}
