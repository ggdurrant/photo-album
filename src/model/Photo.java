package model;

import java.io.File;
import java.util.Date;

import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.io.Serializable;

public class Photo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private File file;
	private String caption;
	private Date date;
	private String url;
	private ArrayList<Tag> tags;
	private String dateString;
	
	
	public Photo(String u){
		this.url = u;
		// last modified or initial date?
//		this.date = new Date(this.file.lastModified());
	}
	
	public Photo(String u, String c){
		this.caption = c;
		this.url = u;
		this.date = new Date(this.file.lastModified());
	}
	
	public Photo(File f, String c){
		this.caption = c;
		this.file = f;
		this.date = new Date(this.file.lastModified());
	}
	
	public Photo(String u, String c, Date d){
		this.url = u;
		this.caption = c;
		this.date = d;
	}
	
	public Photo(String u, String c, String d){
		this.url = u;
		this.caption = c;
		this.dateString = d;
	}
	
	public Photo(Photo p){
		this.url = p.url;
		this.caption = p.caption;
		this.date = p.date;
		this.dateString = p.dateString;
		this.tags = new ArrayList<Tag>();
//		for(Tag t : p.tags){
//			this.tags.add(new Tag(t));
//		}
	}
	
	public static Photo createPhoto(String s, File f){
		if(f==null){
			f = new File(s);
		}
		long lastMod = f.lastModified();
		return new Photo(f, s);
		
	}
	
	public Date getLastDate(){
		return this.date;
	}
	
	public String getDateString(){
		return this.dateString;
	}
	
	public String getCaption(){
		return this.caption;
	}
	
	public void setCaption(String s){
		this.caption = s;
	}
	
	public String getFilePath(){
		return this.file.getPath();
	}
	
	public String getURL(){
		return this.url;
	}
	
	public void setFilePath(File f){
		this.file = f;
	}
	
	public void setURL(String s){
		this.url = s;
	}
	
	public void addTag(String tag, String value){
		Tag t = new Tag(tag, value);
	}
}
	
