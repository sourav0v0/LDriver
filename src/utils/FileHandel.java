package utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FileDaoImpl;

public class FileHandel {
	public final static String path= "C:\\Users\\soura\\Desktop\\files\\";
	public boolean uploadFile(HttpServletRequest request) {
		FileDaoImpl fdi =new FileDaoImpl();
		HttpSession session = request.getSession();
		String email=(String) session.getAttribute("email");
		if(ServletFileUpload.isMultipartContent(request)){
            try {
                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                    	System.out.println("In");
                        String name = new File(item.getName()).getName();
                        System.out.println(fdi.postName(email, name)+" | File Name posted in DB");
                        item.write( new File(path + File.separator + name));
                    }
                }
            } catch (Exception ex) {
            	return false;
            }         	
            return true;
        }else
            return false;
	}
	public boolean downloadFile(ServletContext ctx,HttpServletResponse response,String fileName) throws Exception   {
	        File file = new File(path+fileName	);
	        if(!file.exists()){
	            throw new FileNotFoundException();
	        }
	        System.out.println("File location on server::"+file.getAbsolutePath());
	        InputStream fis = new FileInputStream(file);
	        String mimeType = ctx.getMimeType(file.getAbsolutePath());
	        response.setContentType(mimeType != null? mimeType:"application/octet-stream");
	        response.setContentLength((int) file.length());
	        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
	        ServletOutputStream os       = response.getOutputStream();
	        byte[] bufferData = new byte[2048];
	        int read=0;
	        while((read = fis.read(bufferData))!= -1){
	            os.write(bufferData, 0, read);
	        }
	        os.flush();
	        os.close();
	        fis.close();
	        return true;
	}
	public void playContent(ServletContext ctx,HttpServletResponse response,String fileName) throws Exception   {
        File file = new File(path+fileName);
        if(!file.exists()){
            throw new FileNotFoundException();
        }
        InputStream fis = new FileInputStream(file);
        ServletOutputStream os= response.getOutputStream();
        byte[] bufferData = new byte[2048];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
	}
	boolean valid(String name) {
		String[] dis=name.split(".");
		if(dis.length<=1)return false;
		if(dis[1].equals("mp4"))return true;
		else if (dis[1].equals("mkv"))return true;
		return false;
	}
}
