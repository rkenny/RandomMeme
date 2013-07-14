package com.jamtwo.randommeme.events;

import java.util.EventObject;

public class MemeDownloadFinishEvent extends EventObject {
	public MemeDownloadFinishEvent(Object source)
	{
		super(source);
	}
}
