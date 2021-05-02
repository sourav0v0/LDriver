package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DbCon;
public class AdminDaoImpl implements AdminDao{
	static Connection con=DbCon.getConnection();
	@Override
	public boolean validAdmin(String email, String pass) {
		String query="select * from admin where email=? and pass=?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,email);
			ps.setString(2,pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next())return true;
			return false;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	

}
