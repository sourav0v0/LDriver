package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.util.Random;

import pojo.Ruser;
import utils.DbCon;

public class PublicDaoImpl implements PublicDao{
	Connection con=DbCon.getConnection();
	@Override
	public boolean requestAdmin(Ruser usr) {
		String req="insert into ruser (pid,email,name,password) values(?,?,?,?)";
		String pass=getSaltString();
		usr.setPassword(pass);
		try {
			PreparedStatement ps=con.prepareStatement(req);
			ps.setInt(1,usr.getPid());
			ps.setString(2,usr.getEmail());
			ps.setString(3,usr.getName());
			ps.setString(4,usr.getPassword());
			int j=ps.executeUpdate();
			return j>0;
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
}
