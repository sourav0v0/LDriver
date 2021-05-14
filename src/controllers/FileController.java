package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
import dao.PublicDaoImpl;
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
			PrintWriter out=response.getWriter();
			String pid=request.getParameter("fid");
			String type=request.getParameter("type");
			if(type!=null && type.equals("image"))
				out.println("<img src=\"https://api.qrserver.com/v1/create-qr-code/?data='http://192.168.0.109:8080/Final/FileController?action=download&fid="+pid+"'&amp;size=100x100\" alt=\"\" title=\"\" />");
			else
				out.print("http://192.168.0.109:8080/Final/FileController?action=download&fid="+pid);
		}
		else if(action!=null && action.equals("download")) {
			System.out.println("Download");
			String fid=request.getParameter("fid");
			String name=fdi.getName(Integer.parseInt(fid));
			String close=request.getParameter("close");
			try {
				System.out.println("File name to downalod is "+name);
				boolean b=fh.downloadFile(request.getServletContext(), response, name);
				if(b) {
					System.out.println("Donwloagede");
				}
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
				e.printStackTrace();
			}
		}
		else if(action!=null && action.equals("share")) {
			String fid=fdi.getName(Integer.parseInt(request.getParameter("fid")));
			String url="http://192.168.0.109:8080/Final/FileController?action=download&fid="+fid;
			PublicDaoImpl pdi=new PublicDaoImpl();
			response.setContentType("text/html");
			pdi.alertSuccess("Sharable Link : "+url, response.getWriter());
		}
		else if(action!=null && action.split(",").length>1) {
			String[] sp=action.split(",");
			String id=sp[1];
			response.sendRedirect("FileController?action=download&fid="+id+"&close=true");
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
		PublicDaoImpl pdi=new PublicDaoImpl();
		response.setContentType("text/html");
		if(b) {
			pdi.alertSuccess("Your File Have Been Uploaded ", response.getWriter());
		}
		else
		{
			pdi.alertFail("Something Went wrong..", response.getWriter());
		}
		response.sendRedirect("index.jsp");
	}

}
