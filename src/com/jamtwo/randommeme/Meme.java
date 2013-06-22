package com.jamtwo.randommeme;

import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class Meme implements AsyncResponse {

	private int width;
	private int height;
	private String title;
	private String url;
	private byte[] jpegData; // the meme's jpg as a base64-encoded string
	
	public Meme(String url, String title, int width, int height){
		this(url);
		this.title = title;
		this.width = width;
		this.height = height;
		
	}
	
	public Meme(String url)
	{
		this.url=url;
		setJpegData(url);
	}
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public String getTitle(){return title;}
	public String getUrl(){return url;}
	public byte[] getJpegData(){return jpegData;}
	public void setJpegData(String url)
	{
		HTTPGetJpegAsyncTask getJpeg = new HTTPGetJpegAsyncTask(this, url);
		getJpeg.execute();
	}

	@Override
	public void returnJpeg(byte[] jpegData) 
	{
		//setJpegData(jpegData);
		this.jpegData = jpegData; 
		Log.w("Meme", "received jpegData");
	}

	
	
}
