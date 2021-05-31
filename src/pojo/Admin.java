package pojo;

public class Admin {
	private String email;
	private String password;
	private String type;
	
	public Admin(String email, String password, String type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Admin() {
		
	}
	
	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + ", type=" + type + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
