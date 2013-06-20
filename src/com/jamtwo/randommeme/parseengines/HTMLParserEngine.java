package com.jamtwo.randommeme.parseengines;

import java.io.IOException;

public interface HTMLParserEngine 
{
	public void parse() throws IOException;
	public void setBaseUrl(String baseUrl);
	public String getBaseUrl();
}
