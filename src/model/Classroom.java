package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	List<UserAccount> classroomList;
	
	public Classroom() {
		classroomList = new ArrayList<>();
	}
	
	public List<UserAccount> getClassroomList(){
		return classroomList;
	}
	
	private boolean searchAccount(String username) {
		boolean finded = false;
		for(int i = 0; i<classroomList.size() && !finded; i++) {
			if(classroomList.get(i).getUsername().equals(username)) {
				finded = true;
			}
		}
		return finded;
	}
	
	public boolean createAccount(String username, String password,  String gender, String career, String bDay, String favBrowser) {
		boolean finded = searchAccount(username);
		boolean added = false;
		if(!finded) {
			UserAccount newUserAccount = new UserAccount(username, password, gender, career, bDay, favBrowser);
			classroomList.add(newUserAccount);
			added = true;
		}
		return added;
	}
	
	public boolean logInAccount(String username, String password) {
		boolean finded = searchAccount(username);
		boolean logIn = false;
		for(int i = 0; i<classroomList.size() && finded; i++) {
			if(classroomList.get(i).getUsername().equals(username) && classroomList.get(i).getPassword().equals(password)) {
				logIn = true; 
			}
		}
		return logIn;
	}
}
