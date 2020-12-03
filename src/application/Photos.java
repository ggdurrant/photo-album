package application;

import controller.AdminController;
import controller.LoginController;
import controller.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.Admin;

public class Photos extends Application{
	
	LoginController loginController;
	
	@Override
	public void start(Stage s) {
		try {
//			Admin.saveUsers();
			Admin.loadUsers();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/LoginScreen.fxml"));
			AnchorPane root = loader.load();

			loginController = loader.getController();
			loginController.start(s);
			Scene scene = new Scene(root,root.getPrefWidth(),root.getPrefHeight());
			s.setScene(scene);
			s.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop(){
		try{
			Admin.saveUsers();
		} catch(Exception e){
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}