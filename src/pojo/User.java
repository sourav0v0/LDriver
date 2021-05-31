package pojo;

public class User {
	private int pid;
	private String email,name,password,type;
	public User() {
		super();
	}
	public User(int pid, String email, String name, String password, String type) {
		super();
		this.pid = pid;
		this.email = email;
		this.name = name;
		this.password = password;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "User [pid=" + pid + ", email=" + email + ", name=" + name + ", password=" + password + ", type=" + type
				+ "]";
	}	
}
