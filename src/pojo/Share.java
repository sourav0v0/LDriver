package pojo;

public class Share implements Comparable<Share> {
	@Override
	public String toString() {
		return "Share [toUser=" + toUser + ", fromUser=" + fromUser + ", type=" + type + ", sid=" + sid + ", fid=" + fid
				+ "]";
	}
	private String toUser,fromUser,type;
	private int sid,fid;
	public Share() {};
	public Share(String toUser, String fromUser, String type, int sid, int fid) {
		super();
		this.toUser = toUser;
		this.fromUser = fromUser;
		this.type = type;
		this.sid = sid;
		this.fid = fid;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	@Override  
	public boolean equals(Object obj)   
	{  
		Share temp =(Share)obj;
		if( this.fid != temp.fid)return false;
		if( this.fromUser != temp.fromUser) return false;
		if( this.sid != temp.sid)return false;
		if( this.toUser != temp.toUser)return false;
		if( this.type != temp.type)return false;
		return true;
	}
	 @Override
	 public int hashCode()
	 {
		 int cal=0;
		 int tro=0;
		 for(char c:this.fromUser.toCharArray()) {
			 cal+=(int)c;
			 if(tro>=6)break;
			 tro++;
		 }
		 for(char c:this.type.toCharArray()) {
			 cal+=(int)c;
			 if(tro>=6)break;
			 tro++;
		 }
		cal+=fid+sid;
		return cal; 
	 }
	@Override
	public int compareTo(Share o) {
		if(this.fid == o.getFid() && this.fromUser.equals(o.getFromUser()))return 1;
		return -1;
	}
}
