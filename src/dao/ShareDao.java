package dao;

import java.util.List;

import pojo.Share;
import pojo.User;

public interface ShareDao {
	// public share
	// valid User for share 
	// private share
	// fetch for particular user with userID
	// Deleting File shared
	 /*change Premission -> EXTRA*/
	boolean publicShare(int fid,String frm_user);
	boolean privateShare(int fid,String frm_user,String[] user);
    boolean validEmail(int fid,String gmail);
    String typeOfAccess(int fid);
    List<String> listUsers(int fid);
    List<Share> getShareFiles(String email);
    List<Share> getSharedFileUserFile(String email,int fileID);
    boolean deleteAccess(int fid);
    boolean deleteAccessUsingSid(int sid);
    
}
