package com.jamtwo.randommeme.events;

import java.util.EventObject;

public class MemeDownloadFinishEvent extends EventObject {
	public int stackPosition;
	
	public MemeDownloadFinishEvent(Object source, int stackPosition)
	{
		super(source);
		this.stackPosition = stackPosition;
	}
	
	public MemeDownloadFinishEvent(Object source)
	{
		super(source);
	}
}
