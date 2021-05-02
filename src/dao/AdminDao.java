package dao;

import java.util.List;
import pojo.Ruser;
import pojo.User;

public interface AdminDao {
	boolean validAdmin(String email,String pass);
	List<Ruser> requestedUserList();
	boolean createUser(Ruser r);
	boolean deleteRuser(Ruser r);
}
