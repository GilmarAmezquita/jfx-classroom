package model;

public class UserAccount {
	private String username;
	private String password;
	private String gender;
	private String career;
	private String birthday;
	private String browser;
	
	public UserAccount(String username, String password, String gender, String career, String bDay, String favBrowser) {
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.career = career;
		birthday = bDay;
		browser = favBrowser;
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getGender() {
		return gender;
	}
	public String getCareer() {
		return career;
	}
	public String getBirthday() {
		return birthday;
	}
	public String getFavoriteBrowser() {
		return browser;
	}
}