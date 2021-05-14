package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pojo.User;
import utils.DbCon;
public class UserDaoImpl implements UserDao{
	private final Connection con;
	public UserDaoImpl() {
		con=DbCon.getConnection();
	}
	@Override
	public boolean addUser(User user) {
		if(con==null)return false;
		try {
			PreparedStatement ps=con.prepareStatement("insert into user(pid,email,name,password,type) values (?,?,?,?,?)");
			ps.setInt(1,user.getPid());
			ps.setString(2,user.getEmail());
			ps.setString(3,user.getName());
			ps.setString(4,user.getPassword());
			ps.setString(5,user.getType());
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePassword(String email, String pass, String newPass) {
		try {
			if(!validUser(email, pass))return false;
			PreparedStatement ps=con.prepareStatement("update user  set password=? where email=?");
			ps.setString(2, email);
			ps.setString(1,newPass);
			return ps.executeUpdate()>0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> getUsers() {
		try {
			PreparedStatement ps=con.prepareStatement("select * from user");
			ResultSet rs=ps.executeQuery();
			List<User> rtn=new ArrayList<User>();
			while(rs.next()) {
			User user=new User(); 
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPid(rs.getInt("pid"));
			user.setType(rs.getString("type"));
			rtn.add(user);
			}
			return rtn;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return null;
	}

	@Override
	public User searchUser(String email) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from user where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			User user=new User();
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword("");
			user.setPid(rs.getInt("pid"));
			user.setType(rs.getString("type"));
			return user;
			}
			return null;	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean validUser(String email, String pass) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from user where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs==null)return false;
			if(rs.next()) {
			String fp=rs.getString("password");
			if(fp==null)return false;
			System.out.println(fp);
			if(fp.equals(pass))return true;
			}
			return false;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	@Override
	public List<User> getUsers(String type) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from user where type =?");
			ps.setString(1, type);
			ResultSet rs=ps.executeQuery();
			List<User> rtn=new ArrayList<User>();
			while(rs.next()) {
			User user=new User(); 
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPid(rs.getInt("pid"));
			user.setType(rs.getString("type"));
			rtn.add(user);
			}
			return rtn;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return null;
	}
	@Override
	public boolean requestAdmin(User usr) { 
		String pass=getSaltString();
		usr.setType("request");
		usr.setPassword(pass);
		try {
			return addUser(usr);
		}
		catch(Exception x) {
			return false;
		}
		
	}
	private String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	@Override
	public User getUsers(int uid) {
		try{
		PreparedStatement ps=con.prepareStatement("select * from user where pid=?");
		ps.setInt(1, uid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			User user=new User(); 
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPid(rs.getInt("pid"));
			user.setType(rs.getString("type"));
			return user;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
