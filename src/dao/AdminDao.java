package dao;

import java.util.List;
import pojo.User;

public interface AdminDao {
	boolean addAdmin(String email,String pass);
	boolean removeAdmin(String email);
	boolean validAdmin(String email,String pass);
	List<User> requestedUserList();
	boolean approveUser(int pid);
	boolean deleteRuser(String email);
	boolean rejectUser(User user);
	int countAdmin();
}
