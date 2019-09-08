package ksd.smbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ksd.smbms.pojo.User;

public interface UserMapper {
	/**
	 * µÇÂ½
	 */
	public User login(String username,String userpwd);

	public void add(User user);

	public User checkName(String userCode);

	public List<User> queryUsers(@Param("userName")String userName,@Param("userRole")String userRole,@Param("start")int start,@Param("size")int size);
   
    public int getUserCount(@Param("userName")String userName,@Param("userRole")String userRole);

	public int delet(int delid);

	public User findById(int uid);

	public void update(User user);

	public String findOldpwd(String username); 

	public void pwdupdate(@Param("username")String userName,@Param("userpassword")String newpassword);

	public int finduserid(String username);



	
	
}
