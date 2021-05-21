package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		AdminDaoImpl adi=new AdminDaoImpl();
		PublicDaoImpl pdi=new PublicDaoImpl();
		String action = req.getParameter("action");
		String s=req.getParameter("type");
		String email=req.getParameter("email");
		String pas=req.getParameter("password");
		String ftype = (String) session.getAttribute("type");
		System.out.println(s+"| "+email+" | "+pas);
		System.out.println(s);
		if(action!=null && action.equals("addAdmin")) {
			String aemail= req.getParameter("email");
			String apass= req.getParameter("pass");
			if(adi.addAdmin(aemail, apass))
				pdi.alertSuccess("Admin Added Successfully", resp.getWriter());
			else
				pdi.alertFail("Something went Wrong", resp.getWriter());
				
		}
		if(action!=null && action.equals("changePassword")) {
			email =(String)session.getAttribute("email");
			String apass= req.getParameter("pass");
			String cpass= req.getParameter("cpass");
			if(ftype!=null && ftype.equals("suadmin")) {
				System.out.println(email + " change pass "+ apass +" | "+cpass);
				if(adi.changePassword(email, apass, cpass, "su"))
					pdi.alertSuccess("Admin Added Successfully", resp.getWriter());
				else
					pdi.alertFail("Something went Wrong", resp.getWriter());
			}
			else {
				if(adi.changePassword(email, apass, cpass, "user"))
					pdi.alertSuccess("Admin Added Successfully", resp.getWriter());
				else
					pdi.alertFail("Something went Wrong", resp.getWriter());
			}
		}
		else {
		if(s.equals("admin")) {
			 if(adi.validSU(email, pas)) {
					session.setAttribute("type", "suadmin");
					session.setAttribute("email", email);
					System.out.println(" IN SU ");
					resp.sendRedirect("index.jsp");
				}
			 else if(adi.validAdmin(email, pas))
			{
				session.setAttribute("type", "admin");
				session.setAttribute("email", email);
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
			System.out.println(email);
			resp.sendRedirect("index.jsp");
			}
			else {
				resp.setContentType("text/html");
				new PublicDaoImpl().alertFail("Fail To Login", resp.getWriter());
			}
			
		}
		else {
			resp.sendRedirect("index.jsp");
		}
		}
			
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		AdminDaoImpl adi=new AdminDaoImpl();
		String email1=(String)session.getAttribute("email");
		if(email1==null)resp.sendRedirect("Login.jsp");
		String action= req.getParameter("action");
		PublicDaoImpl pdi=new PublicDaoImpl();
		UserDaoImpl udi=new UserDaoImpl();
		if(action!=null && action.equals("removeAdmin")) {
			String aemail= req.getParameter("email");
			if(adi.removeAdmin(aemail))
					pdi.alertSuccess("Admin Removed Successfully", resp.getWriter());
			else
				pdi.alertFail("Something went Wrong or The system has single Admin ", resp.getWriter());
		}
		else
		{
			String email=req.getParameter("email");
			String name=req.getParameter("name");
			int id = Integer.parseInt(req.getParameter("cid"));
			User ruser=new User(id, email, name,"","request");
		if(udi.requestAdmin(ruser)) {
			resp.setContentType("text/html");
			pdi.alertSuccess("Successfully Requested Admin", resp.getWriter());
		}
		else
		{
			resp.setContentType("text/html");
			pdi.alertFail("Something Went Wrong...", resp.getWriter());
		}
		}
	}
}
