package pojo;

public class Couple {
	private String email;
	private int id;
	public Couple() {
		
	}
	public Couple(String email, int id) {
		super();
		this.email = email;
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Couple [email=" + email + ", id=" + id + "]";
	}
	@Override  
	public boolean equals(Object obj)   
	{  
		Couple temp =(Couple)obj;
		if( this.getEmail() != temp.getEmail())return false;
		if( this.getId() != temp.getId()) return false;
		if( this.getClass() != temp.getClass()) return false;
		return true;
	}
}
