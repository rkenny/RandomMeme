package com.jamtwo.randommeme.asynctasks;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class HTTPGetJpegAsyncTask extends AsyncTask<Void, Void, byte[]> 
{
	AsyncResponse delegate;
	Context context;
	URL url;
	
	public HTTPGetJpegAsyncTask(AsyncResponse delegate, Context context, String url)
	{
		this.delegate = delegate;
		this.context = context;
		try 
		{
			this.url = new URL(url);
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			delegate.removeFromMemeStack();
			e.printStackTrace();
		}
	}
	
	@Override
	protected byte[] doInBackground(Void... params) 
	{
		byte[] imageData = null;
		InputStream in = null;;
		ByteArrayOutputStream out = null;
		//URL url;
		HttpURLConnection urlConnection = null;
		try 
		{
			//url = new URL(params[0]);
			urlConnection = (HttpURLConnection) url.openConnection();
			in = new BufferedInputStream(urlConnection.getInputStream());
		    out = new ByteArrayOutputStream();			
		    
		    String str;
		    int c;
		    while((c= in.read()) != -1)
		    {
		    	out.write(c);
		    }
		    out.flush();
		    imageData = out.toByteArray();
		    
		    in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			delegate.removeFromMemeStack();
			e.printStackTrace();
		}
		finally 
		{
			if(urlConnection != null)
			{
				urlConnection.disconnect();
			}
			
		}
		 
		return imageData;
	}

	@Override
	protected void onPostExecute(byte[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.v("HTTPGetJpegAsyncTask", "I have another meme to show you!");
		//Toast.makeText(context, "I have another meme to show you!", Toast.LENGTH_SHORT).show();
		delegate.returnJpeg(result);
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
	}
	
}
