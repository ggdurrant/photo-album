package controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.User;

public class LoginController extends SceneController{
	
	@FXML
	private Button loginButton;
	@FXML
	private Button quitButton;
	@FXML
	private TextField usernameField;
	
	
	public void start(Stage s){
		super.start(s);
	}
	
	/**
	 * called when login is pressed
	 * opens admin subsystem if "admin"
	 * else opens main page
	 * @param e
	 */
	public void loginPressed(ActionEvent e){

//		System.out.println("You pressed login");
		// get entered username
		String username = usernameField.getText();
		
//		System.out.println(Admin.isUser(username));
		User newUser = new User(username);

		User u = Admin.login(username);
		
		// if nothing entered in field
		if(username.isEmpty()){
			System.err.println("Please enter your username in the text field");
		}
		
		// if admin
		if(username.equals("admin")){
			System.out.println("Opening admin view");
			nextStage("/view/AdminScreen.fxml");
		}
		
		// else add user
		else{
			System.out.println("opening menu screen");
			nextStage("/view/MenuScreen.fxml");
//			System.out.println("creating new user: "+username);
		}
	}
	
	/**
	 * quits program from login screen
	 * no need to save since at login
	 * @param e
	 */
	public void quitPressed(ActionEvent e){
		Platform.exit();
	}
}
