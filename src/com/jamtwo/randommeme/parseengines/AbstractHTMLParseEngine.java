package com.jamtwo.randommeme.parseengines;

import android.content.Context;

public abstract class AbstractHTMLParseEngine implements HTMLParserEngine 
{
	public String baseUrl;
	private Context context;
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
	
	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
}
