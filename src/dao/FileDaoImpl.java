package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pojo.FFile;
import pojo.User;
import utils.DbCon;

public class FileDaoImpl implements FileDao{
	private Connection con=DbCon.getConnection();
	@Override
	public boolean postName(String email, String name) {
		try {
			PreparedStatement ps=con.prepareStatement("insert into files (email,fname) values(?,?)");
			ps.setString(1, email);
			ps.setString(2, name);
			return ps.executeUpdate()>0;
		}
		catch(Exception ex) {
			return false;
		}
	}
	@Override
	public List<FFile> getFiles(String email) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from files where email=?");
			ps.setString(1, email);
			List<FFile> f=new ArrayList<FFile>();
			System.out.println(" IN herer "+f.size());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FFile f1=new FFile();
				f1.setEmail(rs.getString("email"));
				f1.setName(rs.getString("fname"));
				f1.setId(rs.getInt("fid"));
				f.add(f1);
				
			}
			System.out.println(" IN herer "+f.size());
			return f;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@Override
	public String getName(int id) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from files where fid=?");
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			String name=null;
			if(rs.next())
				name=rs.getString("fname");			
			return name;
		}
		catch(Exception ex) {
			return null;
		}
	}
	@Override
	public int getId(String name) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from files where fname=?");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				return rs.getInt("fid");			
			return -1;
		}
		catch(Exception ex) {
			return -1;
		}
	}
	@Override
	public FFile getFileWithId(int fid) {
		try {
			PreparedStatement ps=con.prepareStatement("select * from files where fid=?");
			ps.setInt(1, fid);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				return new FFile(rs.getInt("fid"), rs.getString("email"), rs.getString("fname"));
		}
		catch(Exception ex) {
			return null;
		}
		return null;
	}

	@Override
	public boolean deleteFile(int fid) {
		System.out.println(fid+" DELETENG ");
		try {
			ShareDaoImpl sdi=new ShareDaoImpl();
			if(sdi.deleteAccess(fid)){;
				PreparedStatement ps=con.prepareStatement("delete from files where fid=?");
				ps.setInt(1, fid);
				return ps.executeUpdate()>0;
			}
			return false;
		}
		catch(Exception ex) {
			return false;
		}
	}
	

}
