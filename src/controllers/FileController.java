package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.Request;

import dao.FileDao;
import dao.FileDaoImpl;
import pojo.FFile;
import utils.FileHandel;

/**
 * Servlet implementation class FileController
 */
@WebServlet("/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see 
     *   HttpServlet#HttpServlet()
     */
    public FileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("email");
		String action=request.getParameter("action");
		FileHandel fh=new FileHandel();
		FileDaoImpl fdi=new FileDaoImpl();
		if(action!=null && action.equals("generate"))
		{
			System.out.println("GENERATE");
		}
		else if(action!=null && action.equals("download")) {
			System.out.println("Download");
			String name=fdi.getName(Integer.parseInt(request.getParameter("fid")));
			try {
				System.out.println("File name to downalod is "+name);
				boolean b=fh.downloadFile(request.getServletContext(), response, name);
				if(b)
					System.out.println("Donwloagede");
				else
					System.out.println("Fail to download the file");
			} catch (Exception e) {
				System.out.println("Somting Went worng");
				e.printStackTrace();
			}
		}
		else if(action!=null && action.equals("view")) {
			String name=fdi.getName(Integer.parseInt(request.getParameter("fid")));
			try {
				System.out.println("WEEE");
				fh.playContent(request.getServletContext(), response, name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			List<FFile> fls=fdi.getFiles(email);
			session.setAttribute("files", fls);
			response.sendRedirect("DisplayFile.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileHandel fh=new FileHandel();
		boolean b=fh.uploadFile(request);
		if(b) {
			System.out.println("Uploaded");
		}
		else
		{
			System.out.println("Fail to upload");
		}
		response.sendRedirect("index.jsp");
	}

}
