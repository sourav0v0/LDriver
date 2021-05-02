package pojo;

public class User {
	int pid;
	String email;
	String name;
	String password;
	
	public User() {
		
	}
	public User(String email, String name, String password,int pid) {
		super();
		this.pid=pid;
		this.email = email;
		this.name = name;
		this.password = password;
		
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
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
	
	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + "]";
	}
	
}
