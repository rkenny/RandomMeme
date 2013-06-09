package com.jamtwo.randommeme;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        WebView webview = (WebView)findViewById(R.id.memeView);
        webview.getSettings().setJavaScriptEnabled(true);
        
        webview.setPadding(0, 0, 0, 0);
        
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        
        webview.loadUrl("http://i.qkme.me/3r48s3.jpg");
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    
}
