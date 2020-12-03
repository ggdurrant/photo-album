package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import model.Album;
import model.Library;
import model.Photo;
import model.User;

public abstract class SceneController {
	
	public static Scene loginScene;
	public static Scene adminScene;
	public static Scene userScene;
	public static Scene albumScene;
	public static Stage loginStage;
	public static Stage albumStage;
	public static SceneController loginController;
	public static SceneController adminController;
	public static SceneController albumController;
	public static SceneController userController;
	private static Library lib;
	public final static String filePath = "Photos79.dat";
	
	public Stage stage;
	
	public void start(Stage s){
		this.stage = s;
	}
	
	public Stage nextStage(String resource){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(resource));
			AnchorPane root = loader.load();
			
			SceneController sc = loader.getController();
			Stage next = new Stage();
			
			Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
			next.setScene(scene);
			
			
			sc.start(next);
			next.show();
			this.stage.close();
			return next;
		} catch(Exception e){
			return null;
			}
	}
	
	public static Library getLib(){
		if(lib==null){
			try{
				FileInputStream fIn = new FileInputStream(filePath);
				ObjectInputStream in = new ObjectInputStream(fIn);
				lib = (Library) in.readObject();
				in.close();
				fIn.close();
			} catch(Exception e){
				lib = null;
			}
			
			if(lib==null){
				lib = new Library();
				lib.addUser("admin");
				lib.addUser("stock");
				lib.setUser(lib.getUser("stock"));
				User u = lib.getUser();
				Album stock = u.addAlbum("stock");
				stock.addPhoto(Photo.createPhoto("stockphotos/stock1.jpg", null));
				stock.addPhoto(Photo.createPhoto("stockphotos/stock2.jpg", null));
				stock.addPhoto(Photo.createPhoto("stockphotos/stock3.jpg", null));
				stock.addPhoto(Photo.createPhoto("stockphotos/stock4.jpg", null));
				stock.addPhoto(Photo.createPhoto("stockphotos/stock5.jpg", null));
				stock.addPhoto(Photo.createPhoto("stockphotos/stock6.jpg", null));
			}
		}
		
		return lib;
	}
	
	public static void storeFile(){
		if(lib!=null){
			try{
				FileOutputStream fOut = new FileOutputStream(filePath);
				ObjectOutputStream oos = new ObjectOutputStream(fOut);
				oos.writeObject(lib);
				oos.close();
				fOut.close();
			} catch(Exception e){
			}
		}
	}
	
	public void goToAlbumFromUser(){
		albumStage.setScene(albumScene);
		albumStage.show();
	}
	
	public static void goToAdminFromLogin(){
		loginStage.setScene(adminScene);
		loginStage.show();
	}
	
	public static void goToLoginFromAdmin(){
		goToLogin();
	}
	
	public static void goToLoginFromAlbum(){
		albumStage.hide();
		goToLogin();
	}
	
	public static void goToLoginFromUser(){
		albumStage.hide();
		goToLogin();
	}
	
	private static void goToLogin(){
		loginStage.setScene(loginScene);
		loginStage.show();
	}
	
	public static void goToUserFromLogin(){
		loginStage.hide();
		goToUser();
	}
	
	public static void goToUserFromAdmin(){
		loginStage.hide();
		goToUser();
	}
	
	public static void goToUserFromAlbum(){
		goToUser();
	}
	
	private static void goToUser(){
		albumStage.setScene(userScene);
		albumStage.show();
	}
	
}