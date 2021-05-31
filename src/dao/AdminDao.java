package dao;

import java.util.List;

import pojo.Admin;
import pojo.User;

public interface AdminDao {
	boolean addAdmin(String email,String pass);
	List<Admin> getAdmins();
	boolean removeAdmin(String email);
	boolean validAdmin(String email,String pass);
	boolean validSU(String email,String pass);
	List<User> requestedUserList();
	boolean approveUser(int pid);
	boolean deleteRuser(String email);
	boolean changePassword(String email,String oldpass, String pass, String type);
	boolean rejectUser(User user);
}
