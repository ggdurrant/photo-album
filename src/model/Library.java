package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;
    private ObservableList<User> userList;
    private ArrayList<User> userArray;
    private User user;
    
    public Library() {
        userList = FXCollections.observableArrayList();
        userArray = null;
        user = null;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }


    public User getUser() {
        return user;
    }
    
    public User getUser(String name){
    	User temp = userList.remove(userList.indexOf(name));
    	userList.add(temp);
    	return temp;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public void addUser(String name) {
        User u = new User(name);
        //
        userList.add(u);
    }


    public User deleteUser(String name) {
        if (!name.equals("admin")) {
        	return userList.remove(userList.indexOf(name));
        }
        else {
            return null;
        }
    }


    public void deleteUser(int i) {
    	userList.remove(i);

    }
}



