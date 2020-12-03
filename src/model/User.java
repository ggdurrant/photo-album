package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements Comparable<User>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Album> albums;
	
	private String username;
	
	private ObservableList<Album> albumList;
	
	private Album currentAlbum;
	
	public User(String name){
		username = name;
		albumList = FXCollections.observableArrayList();
	}
	
	public Album getCurrentAlbum(){
		return currentAlbum;
	}
	
	public void setCurrentAlbum(Album a){
		this.currentAlbum = a;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public Album deleteAlbum(String name){
		for(int i=0; i<albumList.size(); i++){
			if(albumList.get(i).getAlbumName().equals(name)){
				return albumList.remove(i);
			}
		}
		return null;
	}
	
	public Album getAlbum(String name){
		for(int i=0; i<albumList.size(); i++){
			if(albumList.get(i).getAlbumName().equals(name)){
				return albumList.get(i);
			}
		}
		return null;
	}
	
	public Album addAlbum(String name){
		Album newAlbum = new Album(name);
		albumList.add(newAlbum);
		return newAlbum;
	}
	
	public void renameAlbum(String newName, int i){
		albumList.get(i).setAlbumName(newName);
	}
	
	public void copyPhotoToAlbum(Photo p, String name){
		Photo temp = new Photo(p);
		Album newAlbum = getAlbum(name);
		newAlbum.addPhoto(temp);
	}
	
	public void movePhoto(Photo p, String name){
		Album newAlbum = getAlbum(name);
		this.currentAlbum.removePhoto(p);
		newAlbum.addPhoto(p);
	}
	
	public int compareTo(User u){
		return username.compareTo(u.getUsername());
	}

	public  ArrayList<Album> getLib() {
		// TODO Auto-generated method stub
		return albums;
	}
}
