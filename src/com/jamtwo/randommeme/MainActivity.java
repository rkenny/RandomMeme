package com.jamtwo.randommeme;

import java.io.IOException;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private MemeWebView mWebView;
	private int RandomNumber = 1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
    }
    
    private void initView(){
    	HTMLParser parser = new HTMLParser();
    	try {
			parser.parseHTML("http://quickmeme.com/random/?num="+RandomNumber);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
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
		}
		
	}

    
    
    
}
