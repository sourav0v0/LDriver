package dao;

import java.util.List;
import pojo.Ruser;

public interface AdminDao {
	boolean validAdmin(String email,String pass);
	List<Ruser> requestedUser();
	boolean createUser(Ruser r);

}
