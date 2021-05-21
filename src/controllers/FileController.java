package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import dao.ShareDaoImpl;
import pojo.Couple;
import pojo.FFile;
import pojo.Share;
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
		if(email==null)response.sendRedirect("Login.jsp");
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
		else if(action!=null && action.equals("SharedFile"))
		{
			System.out.println("IN FILE SHER");
			ShareDaoImpl sdi=new ShareDaoImpl();
			ArrayList<Share> sh= (ArrayList<Share>) sdi.getShareFiles(email);
			Map<Integer,ArrayList<Couple>> rtn=new HashMap<>();
			for(Share sha:sh) {
				int id=sha.getFid();
				ArrayList<Couple> t=rtn.getOrDefault(id, new ArrayList<Couple>());
				t.add(new Couple(sha.getToUser(),sha.getSid()));
				rtn.put(id, t);
			}
			request.setAttribute("share", rtn);
			RequestDispatcher rd=request.getRequestDispatcher("ShareFiles.jsp");
			rd.forward(request, response);
		}
		else if(action!=null && action.equals("download")) {
		    int fid=Integer.parseInt(request.getParameter("fid"));
			String name=fdi.getName(fid);
			email = (String)session.getAttribute("email");
			PublicDaoImpl pdi=new PublicDaoImpl();
			ShareDaoImpl sdi=new ShareDaoImpl();
			if(email==null)
			{
				response.sendRedirect("login.jsp");
			}
			try {
				System.out.println(email + " | "+ fid +" OUT ");
				System.out.println(sdi.validEmail(fid, email));
				if(sdi.validEmail(fid, email))
				{
				boolean b=fh.downloadFile(request.getServletContext(), response, name,email);
				if(b) {
					System.out.println("Donwloagede");
				}
				else
					pdi.alertFail("Something went Wrong", response.getWriter());
				}
				else {
					pdi.alertFail("You Dont Have Access...", response.getWriter());
				}
			} catch (Exception e) {
				pdi.alertFail("Something went Wrong..", response.getWriter());
				e.printStackTrace();
			}
			
			
		}
		else if(action!=null && action.equals("downloadShare")) {
		    int fid=Integer.parseInt(request.getParameter("fid"));
			String name=fdi.getName(fid);
			email = (String)session.getAttribute("email");
			PublicDaoImpl pdi=new PublicDaoImpl();
			ShareDaoImpl sdi=new ShareDaoImpl();
			if(email==null)
			{
				response.sendRedirect("login.jsp");
			}
			try {
				System.out.println(email + " | "+ fid +" OUT ");
				System.out.println(sdi.validEmail(fid, email));
				if(sdi.validEmail(fid, email))
				{
					email = sdi.getShareUser(fid);
				boolean b=fh.downloadFile(request.getServletContext(), response, name,email);
				if(b) {
					System.out.println("Donwloagede");
				}
				else
					pdi.alertFail("Something went Wrong", response.getWriter());
				}
				else {
					pdi.alertFail("You Dont Have Access...", response.getWriter());
				}
			} catch (Exception e) {
				pdi.alertFail("Something went Wrong..", response.getWriter());
				e.printStackTrace();
			}
			
			
		}
		else if(action!=null && action.equals("view")) {
			String name=fdi.getName(Integer.parseInt(request.getParameter("fid")));
			try {
				System.out.println("WEEE");
				System.out.println(email);
				fh.playContent(request.getServletContext(), response, name,email);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(action!=null && action.equals("share")) {
			String fid=request.getParameter("fid");
			request.setAttribute("fid",fid);
			RequestDispatcher rs=request.getRequestDispatcher("Share.jsp");
			rs.forward(request, response);
			
		}
		else if(action!=null && action.equals("delete")) {
			PublicDaoImpl pdi=new PublicDaoImpl();
			int fid=Integer.parseInt(request.getParameter("fid"));
			String fileName= request.getParameter("name");
			System.out.println(fileName +" "+fid + "  "+email);
			if(fdi.deleteFile(fid)) {
				if(fh.deleteFile(email, fileName))
					pdi.alertSuccess("File has Been Removed ", response.getWriter());
				else
					  pdi.alertFail("Something went Wrong... ", response.getWriter());
			}
			else
				  pdi.alertFail("Something went Wrong ", response.getWriter());
		}
		else if(action!=null && action.equals("deleteShareFile")) {
			PublicDaoImpl pdi=new PublicDaoImpl();
			ShareDaoImpl sdi=new ShareDaoImpl();
			int fid=Integer.parseInt(request.getParameter("fid"));
			if(sdi.deleteAccess(fid))
					pdi.alertSuccess("File Access have been Removed ", response.getWriter());
			else
					  pdi.alertFail("Something went Wrong... ", response.getWriter());
			
		}
		else if(action!=null && action.equals("removeUser")) {
			PublicDaoImpl pdi=new PublicDaoImpl();
			int sid =Integer.parseInt(request.getParameter("sid")==null ?"-1":request.getParameter("sid"));
			ShareDaoImpl sdi=new ShareDaoImpl();
			if(sdi.deleteAccessUsingSid(sid)) {
				pdi.alertSuccess("Deleted Access ",response.getWriter(),"FileController?action=SharedFile");
			}
			else
			{
				pdi.alertFail("Something Went Wrong",response.getWriter(),"FileController?action=SharedFile");
			}
			
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
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("email");
		if(email==null)response.sendRedirect("Login.jsp");
		String action=request.getParameter("action");
		PublicDaoImpl pdi=new PublicDaoImpl();
		if(action!=null && action.equals("shareType")) {
			int fid=Integer.parseInt(request.getParameter("fid"));
			String type=request.getParameter("type");
			ShareDaoImpl sdi=new ShareDaoImpl();
			String url="http://192.168.0.109:8080/Final/FileController?action=download&fid="+fid;
			if(type.equals("public")) {
				boolean b=sdi.publicShare(fid,email);
				response.setContentType("text/html");
				if(b) 
					pdi.alertSuccess("Publicly Shared File url is "+url, response.getWriter());
				else
					pdi.alertFail("Something Went wrong..", response.getWriter());
			}
			else
			{
				String userJoinList=request.getParameter("usersShare");
				String[] users=userJoinList.split("-");
				boolean b=sdi.privateShare(fid,email,users);
				response.setContentType("text/html");
				if(b) 
					pdi.alertSuccess("Privately Shared File url is "+url, response.getWriter());
				else
					pdi.alertFail("Something Went wrong..", response.getWriter());
			}
			
		}
		else {
		FileHandel fh=new FileHandel();
		boolean b=fh.uploadFile(request,email);
		response.setContentType("text/html");
		if(b) 
			pdi.alertSuccess("Your File Have Been Uploaded ", response.getWriter());
		else
			pdi.alertFail("Something Went wrong..", response.getWriter());
		}
	}

}
