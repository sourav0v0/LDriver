package dao;

import java.io.PrintWriter;

import pojo.Ruser;

public interface PublicDao {
	boolean requestAdmin(Ruser usr);
	void alertSuccess(String message,PrintWriter out);
	
}
