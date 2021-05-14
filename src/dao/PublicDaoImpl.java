package dao;

import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.util.Random;

import pojo.User;
import utils.DbCon;

public class PublicDaoImpl implements PublicDao{
	Connection con=DbCon.getConnection();
	
	@Override
	public void alertSuccess(String message, PrintWriter out) {
		out.println("<!DOCTYPE html>"); 
		out.println("<html>"); 
		out.println("<head>");
		out.println("<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>"); 
		out.println("</head>");
		out.println("<body>\r\n");
		out.println("<script type=\"text/javascript\">"); 
		out.println("swal(\"Congrats!\", \""+message+"\", \"success\")"); 
		out.println(".then((value) => {"); 
		out.println("location='http://localhost:8080/Final/index.jsp'"); 
		out.println("});");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>"); 
	}
	@Override
	public void alertFail(String message, PrintWriter out) {
		out.println("<!DOCTYPE html>"); 
		out.println("<html>"); 
		out.println("<head>");
		out.println("<script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>"); 
		out.println("</head>");
		out.println("<body>\r\n");
		out.println("<script type=\"text/javascript\">"); 
		out.println("swal(\"Fail\",\""+message+"\", \"error\")"); 
		out.println(".then((value) => {"); 
		out.println("location='http://localhost:8080/Final/index.jsp'"); 
		out.println("});");
		out.println("</script>");
		out.println("</body>");
		out.println("</html>"); 
	}
	
}
