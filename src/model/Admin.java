package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.ObjectInputStream;
import model.User;

/**
 * class for admin users to add/delete/save/load users
 * @author ggdurrant
 *
 */
public class Admin {

	/**
	 * file and directory to save users 
	 */
	public static final String storeDir = "dat";
	public static final String storeFile = "admin.dat";
	
	/**
	 * list of users as an array list
	 */
	private static ArrayList<User> users = new ArrayList<User>(Arrays.asList(new User("stock")));

	/**
	 * saves users 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void saveUsers() throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir+File.separator+storeFile));
		oos.writeObject(users);
//		oos.close();
	}
	
	/**
	 * loads users
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void loadUsers() throws ClassNotFoundException, IOException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir+File.separator+storeFile));
		users = (ArrayList<User>) ois.readObject();
	}
	
	public static User login(String name){
		for(User u : users){
			System.out.println(u);
			if(u!=null){
				if(name.equals(u.getUsername())){
					return u;
				}
			}
		}
		return null;
	}
	
	

	public static ArrayList<String> getUsernames(){
		ArrayList<String> usernames = new ArrayList<String>();
		for(User u: users){
			usernames.add(u.getUsername());
		}
		return usernames;
	}
	
	

	public static boolean deleteUser(String name){
		for(int i=0; i<users.size(); i++){
			if(name.equals(users.get(i).getUsername())){
				users.remove(i);
				return true;
			}
		}
		return false;
	}
	
	
	
	public static boolean addUser(String name){
		for(User u : users){
			System.out.println(u.getUsername());
			if(u!=null){
				if(u.getUsername().equals(name)){
					return false;
				}
			}
		}
		users.add(new User(name));
		return true;
	}
	
	
	
	
	/**
	 * get list of User objects
	 * @return
	 */
	public static ArrayList<User> getUsers(){
		return users;
	}
	
	public static void setUser(){
		users.add(new User("stock"));
	}
	
	public static void resetUsers(){
		users = new ArrayList<User>(Arrays.asList(new User("stock")));
	}
	
	
	
	public static int getListSize(){
		return users.size();
	}
	
	public static boolean isUser(String name){
		boolean isAUser = false;
		if(!users.contains(null)){
			for(User u : users){
				if(u.getUsername().equals(null))
					isAUser = true;
			}
//			for(int i=0; i<users.size(); i++){
//				if(users.get(i).getUsername().equals(name))
//					isAUser=true;
//			}
		}
		return isAUser;
	}
	
	/**
	 * get list of User's names as array list of Strings
	 * @return
	 */
	public static ArrayList<String> getUsersNames(){
		ArrayList<String> usernames = new ArrayList<String>();
		for(int i=0; i<users.size(); i++){
			usernames.add(users.get(i).getUsername());
		}
		ArrayList<String> uNames = new ArrayList<String>();
		System.out.println("original: "+uNames);
		ArrayList<User> test = new ArrayList<User>();
		test.add(new User("cb"));
		test.add(new User("george"));
		test.add(new User("bonga"));
		for (User u : users) {
			uNames.add(u.getUsername());
			System.out.println("individual: "+u.getUsername());
		}
		System.out.println("final: "+uNames);
		return uNames;
//		System.out.println("these are the unames: "+usernames);
//		return usernames;
	}
	
	
	/**
	 * delete User object with username "name"
	 * @param name
	 */
	public static void removeUser(String name){
		for(int i=0; i<users.size(); i++){
			if(users.get(i).getUsername().equals(name)){
				users.remove(i);
			}
		}
	}	
}
