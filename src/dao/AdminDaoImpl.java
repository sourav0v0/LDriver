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
	public List<User> requestedUserList() {
		List<User> rtn=new ArrayList<User>();
		String que="select * from user where type='request'";
		try {
			PreparedStatement ps=con.prepareStatement(que);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				rtn.add(new User(rs.getInt("pid"),rs.getString("email"), rs.getString("name"), rs.getString("password"),rs.getString("type")));
			}
			return rtn;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean approveUser(int pid) {
		UserDaoImpl udi=new UserDaoImpl();
		User user = udi.getUsers(pid);
		if(con==null || user==null)return false;
		try {
			PreparedStatement ps=con.prepareStatement("update user set type='user' where email=?");
			ps.setString(1, user.getEmail());
			if(ps.executeUpdate()>0) {
				new Approve(user);	 // Thread 
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean deleteRuser(String email) {
		if(con==null)return false;
		try {
			PreparedStatement ps=con.prepareStatement("delete from user where email =?");
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
	@Override
	public int countAdmin() {
		try {
			String que="select count(email) from admin";
			PreparedStatement ps=con.prepareStatement(que);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				return rs.getInt(1);
			else
				return 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public boolean rejectUser(User user) {
		new Reject(user);
		return true;
	}
	
}
class Approve implements Runnable {
	User r;
	Approve(User r){
		this.r=r;
		this.run();
	}
	@Override
	public void run() {
		mailUser(r);
	}
	public boolean mailUser(User r) {
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
}
class Reject implements Runnable{
	User u;
	 Reject(User u){
		 this.u=u;
		 this.run();
	 }
	@Override
	public void run() {
		rejectUser(u);
	}
	public boolean rejectUser(User user) {
		UserDaoImpl udi=new UserDaoImpl();
		if(!udi.removeUser(user))return false;
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
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));    
			message.setSubject("LDrive Account Info");    
			message.setText(">Your Account has Been Rejected ");
			Transport.send(message);    
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}   
	}
}
