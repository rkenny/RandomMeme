package com.jamtwo.randommeme.parseengines;

public abstract class AbstractHTMLParseEngine implements HTMLParserEngine 
{
	public String baseUrl;
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
	
	public void setBaseUrl(String baseUrl)
	{
		this.baseUrl = baseUrl;
	}
}
