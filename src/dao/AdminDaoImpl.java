package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import pojo.Ruser;
import pojo.User;
import utils.DbCon;
public class AdminDaoImpl implements AdminDao{
	static Connection con=DbCon.getConnection();
	@Override
	public boolean validAdmin(String email, String pass) {
		String query="select * from admin where email=? and password=?";
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
	@Override
	public List<Ruser> requestedUserList() {
		List<Ruser> rtn=new ArrayList<Ruser>();
		String que="select * from ruser";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				rtn.add(new Ruser(rs.getInt("pid"),rs.getString("email"), rs.getString("name"), rs.getString("password")));
			}
			return rtn;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean createUser(Ruser user) {
		if(con==null || user==null)return false;
		try {
			PreparedStatement ps=con.prepareStatement("delete from ruser where email =?");
			ps.setString(1, user.getEmail());
			if(ps.executeUpdate()>0) {
				UserDaoImpl usr=new UserDaoImpl();
				boolean b=usr.addUser(new User(user.getEmail(), user.getName(), user.getPassword() ,user.getPid()));
				if(b) {
					System.out.println("Mailed "+ mailUser(user));
					return true;
				}
				else {
					return false;
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean mailUser(Ruser r) {
		String email="javajava0v0@gmail.com";
		String pass="java@111";
		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");    
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.class",    
				"javax.net.ssl.SSLSocketFactory");    
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "465"); 
		Session session = Session.getInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(email,pass);  
			}    
		});    
		try {    
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(r.getEmail()));    
			message.setSubject("LDrive Account Info");    
			message.setText(">Your Account has Been Created  \n>Your UserName is your email : "+r.getEmail()+
					"\n>Your password is : "+r.getPassword()+
					"\n>Please change Your password");
			Transport.send(message);    
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}    

	}
	@Override
	public boolean deleteRuser(String email) {
		if(con==null)return false;
		try {
			PreparedStatement ps=con.prepareStatement("delete from ruser where email =?");
			ps.setString(1, email);
			return ps.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean addAdmin(String email, String pass) {
		String query="insert into admin (email,password) values(?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,email);
			ps.setString(2,pass);
			return ps.executeUpdate()>0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean removeAdmin(String email) {
		String query="delete from admin where email = ?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,email);
			return ps.executeUpdate()>0;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
