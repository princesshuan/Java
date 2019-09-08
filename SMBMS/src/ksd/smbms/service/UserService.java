package ksd.smbms.service;

import java.util.List;

import ksd.smbms.pojo.Role;
import ksd.smbms.pojo.User;

public interface UserService {

	/**
	 * µÇÂ½
	 */
	public User login(String username, String userpwd);

	public void add(User user);

	User checkName(String userCode);

	public List<Role> rolelist();

	public List<User> queryUsers(String queryname, String queryUserRole,
			int start, int size);

	public int getUserCount(String queryname, String queryUserRole);

	public boolean delet(int delid);

	public User findById(int uid);

	public void update(User user);

	public String findOldpwd(String username);

	public void pwdupdate(String username,String newpassword);

	public int finduserid(String username);


}
