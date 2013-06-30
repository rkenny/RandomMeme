package com.jamtwo.randommeme.asynctasks;

public interface AsyncResponse 
{
	public void returnJpeg(byte[] jpegData);
	public void removeFromMemeStack();
}
