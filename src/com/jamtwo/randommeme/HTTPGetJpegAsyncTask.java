package com.jamtwo.randommeme;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

public class HTTPGetJpegAsyncTask extends AsyncTask<Void, Void, byte[]> 
{
	AsyncResponse delegate;
	URL url;
	
	public HTTPGetJpegAsyncTask(AsyncResponse delegate, String url)
	{
		this.delegate = delegate;
		try 
		{
			this.url = new URL(url);
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
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
		delegate.returnJpeg(result);
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
	}
	
}
