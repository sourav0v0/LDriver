package dao;

import java.sql.Connection;
import java.util.List;

import pojo.User;
import utils.DbCon;
public class UserDaoImpl implements UserDao{
	private final Connection con;
	public UserDaoImpl() {
		con=DbCon.getConnection();
	}
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePassword(String email, String pass, String newPass) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User searchUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
