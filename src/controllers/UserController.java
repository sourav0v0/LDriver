package controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PublicDao;
import dao.PublicDaoImpl;
import dao.UserDaoImpl;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/User")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		UserDaoImpl udi=new UserDaoImpl();
		PublicDaoImpl pdi=new PublicDaoImpl();
		PrintWriter out=response.getWriter();
		if(action!=null && action.equals("changePassword"))
		{
			String pass=request.getParameter("pass");
			String npass=request.getParameter("cpass");
			String email=request.getParameter("emails");
			System.out.println(email+" <> "+pass+" <> "+npass);
			if(udi.updatePassword(email, pass, npass)) {
				pdi.alertSuccess("Successfully Changed Your Password ", out);
				System.out.println("done");
			}
			else
			{
				System.out.println("Fail to change");
			}
			response.sendRedirect("index.jsp");
		}
	}

}
