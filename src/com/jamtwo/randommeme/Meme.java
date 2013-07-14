package com.jamtwo.randommeme;

import java.util.EventObject;

import android.content.Context;

import com.jamtwo.randommeme.asynctasks.AsyncResponse;
import com.jamtwo.randommeme.asynctasks.HTTPGetJpegAsyncTask;
import com.jamtwo.randommeme.events.MemeDownloadFinishEvent;
import com.jamtwo.randommeme.events.MemeDownloadFinishInterface;
import com.jamtwo.randommeme.events.MemeDownloadFinishListener;

public class Meme implements AsyncResponse {
	public static final String CLASS = "Meme";
	private int width;
	private int height;
	private String title;
	private String url;
	private byte[] jpegData; // the meme's jpg as a base64-encoded string
	private int stackPosition;
	private Context context;
	private MemeDownloadFinishInterface downloadListener;
	
	public void setStackPosition(int stackPosition)
	{
		this.stackPosition = stackPosition;
	}
	
	public int getStackPosition()
	{
		return stackPosition;
	}
	
	public void removeFromMemeStack()
	{
		MemeStack.removeMeme(this);
	}
	
	public Meme(Context context, String url, String title, int width, int height){
		this(context, url);
		this.title = title;
		this.width = width;
		this.height = height;
		
	}
	
	public Meme(Context context, String url)
	{
		String TAG = CLASS + "()";
		//Log.w(TAG, "Constructed from " + url);
		this.url=url;
		this.context = context;
		setJpegData(url);
	}
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public String getTitle(){return title;}
	public String getUrl(){return url;}
	public byte[] getJpegData(){return jpegData;}
	
	public void setJpegData(String url)
	{
		HTTPGetJpegAsyncTask getJpeg = new HTTPGetJpegAsyncTask(this, context, url);
		getJpeg.execute();
		
	}

	@Override
	public void returnJpeg(byte[] jpegData) 
	{
		this.jpegData = jpegData;
		MemeDownloadFinishEvent event = new MemeDownloadFinishEvent(this);
		if(context != null) 
		{
			((MemeDownloadFinishInterface) context).onMemeDownloadFinish(event);
		}
		//Log.w("Meme", "jpegData transfer complete for meme " + title + "[" +url+ "]");
	}
	
	public boolean readyToDisplay()
	{
		//Log.w("Meme.readyToDisplay", "Ready to display? " + ((jpegData != null) && (jpegData.length > 0)));
		return((jpegData != null) && (jpegData.length > 0));
	}
	
	public synchronized void setDownloadListener(MemeDownloadFinishInterface listener)
	{
		downloadListener = listener;
	}
	
}
