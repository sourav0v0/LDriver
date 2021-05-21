package dao;

import java.util.List;

import pojo.FFile;
import pojo.User;

public interface FileDao {
	boolean postName(String email,String name);
	List<FFile> getFiles(String email);
	String getName(int id);
	FFile getFileWithId(int fid);
	int getId(String name) ;
	boolean deleteFile(int fid);

}
