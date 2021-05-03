package dao;

import java.util.List;

import pojo.FFile;

public interface FileDao {
	boolean postName(String email,String name);
	List<FFile> getFiles(String email);
	String getName(int id);
}
