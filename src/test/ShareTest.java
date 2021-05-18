package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Painter;

import dao.ShareDaoImpl;
import pojo.Couple;
import pojo.Share;

public class ShareTest {
	public static void main(String[] args) {
//		ShareDaoImpl sdi=new ShareDaoImpl();
//		String name[]= {"abudl@gmail.com","brijlalppt@gmail.com","ggfkdd@gmail.com"};
//	    System.out.println(sdi.privateShare(20, "souravprajapati31@gmail.com", name));
//		for(String s:sdi.listUsers(20))
//			System.out.println(s);
//		System.out.println(sdi.publicShare(20, "souravprajapati31@gmail.com" ));
//		System.out.println(sdi.deleteAccess(20));
//		System.out.println(sdi.publicShare(20, "souravprajapati31@gmail.com" ));
		ShareDaoImpl sdi=new ShareDaoImpl();
		ArrayList<Share> sh= (ArrayList<Share>) sdi.getShareFiles("souravprajapati31@gmail.com");
		Map<Integer,ArrayList<Couple>> rtn=new HashMap<>();
		for(Share sha:sh) {
			int id=sha.getFid();
			ArrayList<Couple> t=rtn.getOrDefault(id, new ArrayList<Couple>());
			t.add(new Couple(sha.getToUser(),sha.getSid()));
			rtn.put(id, t);
		}
		for(int key:rtn.keySet())
		{
			System.out.println(key +"  "+ rtn.get(key));
		}
		
	}

}
