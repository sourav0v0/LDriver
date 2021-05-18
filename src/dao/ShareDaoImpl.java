package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Util;

import pojo.Share;
import pojo.User;
import utils.DbCon;

public class ShareDaoImpl implements ShareDao{
	private final Connection con;
	public ShareDaoImpl() {
		con = DbCon.getConnection();
	}

	@Override
	public boolean privateShare(int fid, String frm_user, String[] user) {
		String type=typeOfAccess(fid);
		if( type!=null && type.equals("public"))deleteAccess(fid);
		for(String s:user) {
		String que= "insert into share (share,user,type,fid) values(?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setString(1, s);
			ps.setString(2, frm_user);
			ps.setString(3, "private");
			ps.setInt(4, fid);
            ps.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}
		return true;
	}

	@Override
	public boolean validEmail(int fid, String gmail) {
		String que= "select  * from share where fid=?";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setInt(1, fid);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
            	String to=rs.getString("share");
            	String type= rs.getString("type");
            	String upl= rs.getString("user");
            	if(upl.equals(gmail))return true;
            	if(type.equals("public"))return true;
            	if(to!=null && to.equals(gmail))
            		return true;
            }
            return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String typeOfAccess(int fid) {
		String que= "select  * from share where fid=?";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setInt(1, fid);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            	return rs.getString("type");
            return "none";
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> listUsers(int fid) {
		String que= "select  * from share where fid=?";
		List<String> st=new ArrayList<String>();
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setInt(1, fid);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
            	if(rs.getString("type") == "public" && st.size()<=0)
            		st.add("public");
            	String user =rs.getString("share");
            	st.add(user);
            }
            return st;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccess(int fid) {
		String que= "delete from share where fid=?";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setInt(1, fid);
            return ps.executeUpdate()>0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean publicShare(int fid, String frm_user) {
		String type=typeOfAccess(fid);
		if( type!=null && type.equals("private"))deleteAccess(fid);
		String que= "insert into share (user,type,fid) values(?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			//ps.setString(1, "");
			ps.setString(1, frm_user);
			ps.setString(2, "public");
			ps.setInt(3, fid);
			return ps.executeUpdate()>0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Share> getShareFiles(String email) {
		String que= "select  * from share where user=?";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            List<Share> rtn=new ArrayList<Share>();
            while(rs.next())
            {
            	String to=rs.getString("share");
            	String type= rs.getString("type");
            	String user= email;
            	int fid=rs.getInt("fid");
            	int sid=rs.getInt("sid");
            	rtn.add(new Share(to,email,type,sid,fid));
            }
            return rtn;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Share> getSharedFileUserFile(String email, int fileID) {
		String que= "select  * from share where user=? and fid=?";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setString(1,email);
			ps.setInt(2,fileID);			
            ResultSet rs=ps.executeQuery();
            List<Share> rtn=new ArrayList<Share>();
            while(rs.next())
            {
            	String to=rs.getString("share");
            	String type= rs.getString("type");
            	String user= email;
            	int fid=rs.getInt("fid");
            	int sid=rs.getInt("sid");
            	rtn.add(new Share(to,email,type,sid,fid));
            }
            return rtn;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAccessUsingSid(int sid) {
		String que= "delete from share where sid=?";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ps.setInt(1, sid);
            return ps.executeUpdate()>0;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
