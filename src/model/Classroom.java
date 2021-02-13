package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
	private List<UserAccount> accounts;
	
	public Classroom() {
		accounts = new ArrayList<>();
	}
	
	public List<UserAccount> getAccounts(){
		return accounts;
	}
	
	private boolean searchAccount(String username) {
		boolean finded = false;
		for(int i = 0; i<accounts.size() && !finded; i++) {
			if(accounts.get(i).getUsername().equals(username)) {
				finded = true;
			}
		}
		return finded;
	}
	
	public String getUserAvatar(String username) {
		boolean finded = false;
		int i;
		for(i = 0; i<accounts.size() && !finded; i++) {
			if(accounts.get(i).getUsername().equals(username)) {
				finded = true;
			}
		}
		i--;
		return accounts.get(i).getImageRute();
	}
	
	public boolean createAccount(String username, String password, String imageRute, String gender, String career, String bDay, String favBrowser) {
		boolean finded = searchAccount(username);
		boolean added = false;
		if(!finded) {
			UserAccount newUserAccount = new UserAccount(username, password,imageRute, gender, career, bDay, favBrowser);
			accounts.add(newUserAccount);
			added = true;
		}
		return added;
	}
	
	public boolean logInAccount(String username, String password) {
		boolean finded = searchAccount(username);
		boolean logIn = false;
		for(int i = 0; i<accounts.size() && finded; i++) {
			if(accounts.get(i).getUsername().equals(username) && accounts.get(i).getPassword().equals(password)) {
				logIn = true; 
			}
		}
		return logIn;
	}
}
