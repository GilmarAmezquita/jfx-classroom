package model;

public class UserAccount {
	private String username;
	private String password;
	private String imageRute;
	private String gender;
	private String career;
	private String birthday;
	private String browser;
	
	public UserAccount(String username, String password, String imageRute,String gender, String career, String bDay, String favBrowser) {
		this.username = username;
		this.password = password;
		this.imageRute = imageRute;
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
	public String getImageRute() {
		return imageRute;
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
	public String getBrowser() {
		return browser;
	}
}