package com.jamtwo.randommeme.events;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemeDownloadFinishSource 
{
	private List _listeners = new ArrayList();
	public synchronized void addEventListener(MemeDownloadFinishInterface listener)  
	{
		_listeners.add(listener);
	}
	  
	public synchronized void removeEventListener(MemeDownloadFinishInterface listener)   
	{
		_listeners.remove(listener);
	}
	 
	// call this method whenever you want to notify
	//the event listeners of the particular event
	private synchronized void fireEvent() 
	{
	  MemeDownloadFinishEvent event = new MemeDownloadFinishEvent(this);
	  Iterator i = _listeners.iterator();
	  while(i.hasNext())  
	  {
	    ((MemeDownloadFinishInterface) i.next()).onMemeDownloadFinish(event);
	  }
	}
}
	

