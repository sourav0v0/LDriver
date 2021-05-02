package dao;

import java.util.List;
import pojo.User;

public interface UserDao {
	boolean addUser(User user);
	boolean updatePassword(String email,String pass,String newPass);
	List<User> getUsers();
	User searchUser(String email);
	boolean validUser(String email,String pass);
}
