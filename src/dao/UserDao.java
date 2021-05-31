package dao;

import java.util.List;
import pojo.User;

public interface UserDao {
	boolean addUser(User user);
	boolean updatePassword(String email,String pass,String newPass);
	List<User> getUsers(String type);
	User getUsers(int uid);
	List<User> getUsers();
	User searchUser(String email);
	boolean removeUser(User user);
	boolean removeUserById(int fid);
	boolean validUser(String email,String pass);
	boolean requestAdmin(User usr);
}
