package test;

import dao.AdminDaoImpl;
import dao.PublicDao;
import dao.PublicDaoImpl;
import pojo.Ruser;

public class AdminTest {
public static void main(String[] args) {
	PublicDaoImpl pimp=new PublicDaoImpl();
	Ruser rp=new Ruser(1, "souravprajapati31@gmail.com","Sourav", "");
	System.out.println(pimp.requestAdmin(rp));
	AdminDaoImpl imp=new AdminDaoImpl();
	System.out.println(imp.createUser(rp));
}
}
