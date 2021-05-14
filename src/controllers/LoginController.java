package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.iap.Response;

import dao.AdminDaoImpl;
import dao.PublicDaoImpl;
import dao.UserDaoImpl;

import pojo.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String s=req.getParameter("type");
		String email=req.getParameter("email");
		String pas=req.getParameter("password");
		System.out.println(s+"| "+email+" | "+pas);
		System.out.println(s);
		if(s.equals("admin")) {
			AdminDaoImpl adi=new AdminDaoImpl();
			if(adi.validAdmin(email, pas))
			{
				session.setAttribute("type", "admin");
				resp.sendRedirect("index.jsp");
			}
			else {
				resp.sendRedirect("index.jsp");
			}
		}
		else if(s.equals("employee")){
			UserDaoImpl udi=new UserDaoImpl();
			if(udi.validUser(email, pas)) {
			session.setAttribute("type", "employee");
			session.setAttribute("email", email);
			resp.sendRedirect("index.jsp");
			}
			else {
				resp.sendRedirect("index.jsp");
			}
		}
		else {
			resp.sendRedirect("index.jsp");
		}
			
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String name=req.getParameter("name");
		int id = Integer.parseInt(req.getParameter("cid"));
		//String phone=req.getParameter("phone");
		User ruser=new User(id, email, name,"","request");
		PublicDaoImpl pdi=new PublicDaoImpl();
		UserDaoImpl udi=new UserDaoImpl();
		System.out.println(ruser);
		System.out.println(udi.requestAdmin(ruser)+ " reuesetd Adin");
		resp.setContentType("text/html");
		pdi.alertSuccess("Successfully Requested Admin", resp.getWriter());
		
	}
}
