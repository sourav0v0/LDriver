package dao;

import java.io.PrintWriter;
import pojo.User;

public interface PublicDao {
	void alertSuccess(String message,PrintWriter out);
	void alertFail(String message ,PrintWriter out);
	void alertSuccess(String message, PrintWriter out,String page);
	void alertFail(String message, PrintWriter out,String page);
}
