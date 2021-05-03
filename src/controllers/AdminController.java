package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDaoImpl;
import dao.RuserDoaImpl;
import pojo.Ruser;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		AdminDaoImpl adi=new AdminDaoImpl();
		RuserDoaImpl rdi=new RuserDoaImpl();
		if(action!=null && action.equals("list")) {
			List<Ruser>rtn=adi.requestedUserList();
			session.setAttribute("users",rtn);
			response.sendRedirect("ListRuser.jsp");
		}
		else if(action!=null && action.equals("status")) {
			String status=request.getParameter("status");
			int fid=Integer.parseInt(request.getParameter("pid"));
			if(status!=null && status.equals("approved")) {
				System.out.println("Approved Form");
				adi.createUser(rdi.getRuser(fid));
			}
			else {
				System.out.println("Reject Form");
				
			}
			response.sendRedirect("index.jsp");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
