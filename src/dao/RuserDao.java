package dao;

import pojo.Ruser;

public interface RuserDao {
	Ruser getRuser(int pid);
	boolean removeRuser(int pid);
}
