package pojo;

public class Ruser {
	private int pid;
	private String email;
	private String name;
	private String password;

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
	
	public Ruser(int pid, String email, String name, String password) {
		super();
		this.pid = pid;
		this.email = email;
		this.name = name;
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + "]";
	}
	
}
