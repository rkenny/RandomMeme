package com.jamtwo.randommeme;

public class Meme {

	private int width;
	private int height;
	private String title;
	private String url;
	
	public Meme(String url, String title, int width, int height){
		this.url = url;
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	public Meme(String url){
		this.url=url;
	}
	
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public String getTitle(){return title;}
	public String getUrl(){return url;}
	
}
