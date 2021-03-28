package pojo;

public class User {
	String email;
	String name;
	String password;
	String pri;
	public User(String email, String name, String password, String pri) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.pri = pri;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPri() {
		return pri;
	}
	public void setPri(String pri) {
		this.pri = pri;
	}
	
}
