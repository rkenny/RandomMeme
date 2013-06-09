package com.jamtwo.randommeme;

/*
 * Class to parse DOM... we could use this to match a pattern to find image urls
 * lots more great info at:
 * http://argillander.wordpress.com/2011/11/23/get-web-page-source-code-in-android/
 * 
 * This class is not fully vetted, so further inspection is needed. 
 */

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SrcGrabber
{
    private HttpGet mRequest;
    private HttpClient mClient;
    private BufferedReader mReader;

    private DocumentBuilder mBuilder;

    private StringBuffer mBuffer;
    private String mNewLine;



    public SrcGrabber()
    {
        mRequest = new HttpGet();
        mClient = new DefaultHttpClient();
        mReader = null;

        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            mBuilder = factory.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }

        mBuffer = new StringBuffer(2000);
        mNewLine = System.getProperty("line.separator");
    }

    public String grabSource(String url) throws ClientProtocolException, IOException, URISyntaxException
    {
        mBuffer.setLength(0);

        try
        {
            mRequest.setURI(new URI(url));
            HttpResponse response = mClient.execute(mRequest);

            mReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = mReader.readLine()) != null)
            {
                mBuffer.append(line);
                mBuffer.append(mNewLine);
            }
        }
        finally
        {
            closeReader();
        }

        return mBuffer.toString();
    }

    public String parseTags(String code, String[] tags) throws IOException, SAXException
    {
        mBuffer.setLength(0);

        Document doc = mBuilder.parse(new ByteArrayInputStream(code.getBytes()));
        Element el = doc.getDocumentElement();

        for (String tag : tags)
            parseTags(el.getElementsByTagName(tag));

        return mBuffer.toString();
    }

    protected void parseTag(Node node)
    {
        if (node.getNodeType() == Node.TEXT_NODE)
        {
            mBuffer.append(node.getNodeValue());
            mBuffer.append(mNewLine);
        }
    }

    private void closeReader()
    {
        if (mReader == null)
            return;

        try
        {
            mReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void parseTags(NodeList nodes)
    {
        int length = nodes.getLength();

        for (int i = 0; i < length; ++i)
            parseTag(nodes.item(i).getFirstChild());
    }
}