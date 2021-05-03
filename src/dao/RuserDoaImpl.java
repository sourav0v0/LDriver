package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pojo.Ruser;
import pojo.User;
import utils.DbCon;

public class RuserDoaImpl implements RuserDao {
	private Connection con=DbCon.getConnection();
	@Override
	public Ruser getRuser(int pid) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from ruser where pid=?");
			ps.setInt(1, pid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
			Ruser user=new Ruser();
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setPid(rs.getInt("pid"));
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
	public boolean removeRuser(int pid) {
		String query="delete from ruser where pdi = ?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,pid);
			return ps.executeUpdate()>0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}
