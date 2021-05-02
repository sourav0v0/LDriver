package test;

import java.util.List;

import dao.UserDaoImpl;
import pojo.User;

public class UserImpl {
	public static void main(String[] args) {
		UserDaoImpl doa=new UserDaoImpl();
		User u=new User("p@g.c","p","1234","user");
		System.out.println(doa.updatePassword("p@g.c","123", "1234"));
	}
}
