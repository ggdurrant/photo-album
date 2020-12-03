package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Album implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String albumName;
	private Photo albumPhoto;
//	private ArrayList<Photo> photos;
	private List<Photo> photos;
	private int numPhotos;
	private String earliest;
	private String latest;
	private int currentIndex;
	
	public Album(String name){
//		this.photos = new ArrayList<>();
		this.photos = new ArrayList<>();
		this.numPhotos = 0;
		this.albumName = name;
		this.earliest = "";
		this.latest = "";
		this.currentIndex = -1;
	}
	
	public Album(String name, List<Photo> list){
		this.albumName = name;
		this.currentIndex = 0;
		int i = 0;
		for(Photo p : list){
			Photo temp = new Photo(p);
			photos.add(temp);
			i++;
		}
		this.numPhotos = i;
	}
	
	
//	public Album(String s){
//		this.albumName = s;
//		this.photos = new ArrayList<Photo>();
//		this.numPhotos=0;
//	}
	
	public String getAlbumName(){
		return this.albumName;
	}
	
	public void setAlbumName(String s){
		this.albumName = s;
	}
	
	public List<Photo> getPhotos(){
		return this.photos;
	}
	
	public void addPhoto(Photo p){
		this.photos.add(p);
		this.numPhotos++;
	}
	
	public Photo getPhoto(int i){
		return photos.get(i);
	}
	
	public void removePhoto(Photo p){
		this.photos.remove(p);
		this.numPhotos--;
	}
	
	public int getNumPhotos(){
		return this.numPhotos;
	}
	
	public void setNumPhotos(int i){
		this.numPhotos = i;
	}
	
	public String getEarliestDate(){
		return this.earliest;
	}
	
	public String getLatestDate(){
		return this.latest;
	}
	
	public void setEarliestDate(String s){
		this.earliest = s;
	}
	
	public void setLatestDate(String s){
		this.latest = s;
	}
	
	public Photo getPhoto(){
		if(numPhotos>0){
			return photos.get(numPhotos-1);
		}
		else{
			return null;
		}
	}
	
	public int findCount(){
		return photos.size();			
	}


	
	
	public String getLastModified(){
		Date last = this.photos.get(0).getLastDate();
		for(int i=0; i<photos.size(); i++){
			if(photos.get(i).getLastDate().after(last)){
				last = photos.get(i).getLastDate();
			}
		}
		return last.toString();
	}
	
	public String getOldest(){
		Date old = this.photos.get(0).getLastDate();
		for(int i=0; i<photos.size(); i++){
			if(photos.get(i).getLastDate().before(old)){
				old = photos.get(i).getLastDate();
			}
		}
		return old.toString();
	}
	
	public void setDates(){
		if(numPhotos>0){
			
		}
	}
	
//	public String getLastModified(){
//		
//	}
}
