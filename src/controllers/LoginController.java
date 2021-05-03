package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDaoImpl;
import dao.PublicDaoImpl;
import dao.UserDaoImpl;
import pojo.Ruser;

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
		Ruser ruser=new Ruser(id, email, name,"");
		PublicDaoImpl pdi=new PublicDaoImpl();
		System.out.println(ruser);
		System.out.println(pdi.requestAdmin(ruser)+ " reuesetd Adin");
		resp.sendRedirect("index.jsp");
	}
}
