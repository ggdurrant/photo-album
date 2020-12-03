package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.User;

/**
 * Controller for the Admin subsystem
 * @author ggdurrant
 *
 */
public class AdminController extends SceneController{
	
	@FXML
	private Button listUsers;
	@FXML
	private Button deleteUser;
	@FXML
	private Button enterUser;
	@FXML
	private Button logoutPressed;
	@FXML
	private Button quitPressed;
	@FXML
	private TextField usernameField;
	@FXML
	private ListView userListView;
	
	private ObservableList<String> olist;
	
//	public void start(Stage s){
//		super.start(s);
//		olist = FXCollections.observableArrayList(Admin.getUsernames());
//		userListView.setItems(olist);
//		userListView.getSelectionModel().select(0);
//	}
	
	/**
	 * will list all users in the "listview" when pressed
	 * @param e
	 */
	public void listUsersPressed(ActionEvent e){
		// find users from Admin
		ObservableList<String> list = FXCollections.observableArrayList(Admin.getUsersNames());
		System.out.println(list);
		userListView.setItems(list);
		userListView.getSelectionModel().select(0);
		// getName of users from list
		// put list into ListView
		
	}
	
	/**
	 * saves admin changes, exits program
	 * @param e
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void quitPressed(ActionEvent e) throws FileNotFoundException, IOException{
		// save changes to users
		// quit
		Admin.saveUsers();
		Platform.exit();
		
	}
	
	/**
	 * saves admin changes, returns to login page
	 * @param e
	 */
	public void logoutPressed(ActionEvent e){
		System.out.println("logging out...");
		nextStage("view/LoginScreen.fxml");
		
		// save changes to users
		// new screen is login screen
//		loadStage("/view/LoginScreen.fxml", null);
	}
	
	/**
	 * deletes the selected user
	 * @param e
	 */
	public void deleteUserPressed(ActionEvent e){
		User deletedUser = (User) userListView.getSelectionModel().getSelectedItem();
		String deletedUserName = deletedUser.getUsername();
		Admin.deleteUser(deletedUserName);
	}
	
	/**
	 * creates new user with entered username
	 * @param e
	 */
	public void enterUserPressed(ActionEvent e){
		String newName = usernameField.getText();
		// need to implement no duplicates?
		// or is handled in Admin class?
		if(Admin.addUser(newName)==false){
			
		}
		else{
			olist = FXCollections.observableArrayList(Admin.getUsernames());
			int newUserLoc = 0;
			for(; newUserLoc<olist.size(); newUserLoc++){
				if(olist.get(newUserLoc).equals(newName)){
					break;
				}
			}
			userListView.setItems(olist);
			userListView.getSelectionModel().select(newUserLoc);
		}
	}
}
