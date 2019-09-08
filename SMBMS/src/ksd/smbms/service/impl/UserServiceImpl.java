package ksd.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ksd.smbms.mapper.RoleMapper;
import ksd.smbms.mapper.UserMapper;
import ksd.smbms.pojo.Role;
import ksd.smbms.pojo.User;
import ksd.smbms.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
    @Resource
	private UserMapper userMapper;
    @Resource
	private RoleMapper roleMapper;
	@Override
	public User login(String username, String userpwd) {
		// TODO Auto-generated method stub
		
		return userMapper.login(username, userpwd);
	}
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userMapper.add(user);
	}
	@Override
	public User checkName(String userCode) {
		// TODO Auto-generated method stub
		return userMapper.checkName(userCode);
	}
	@Override
	public List<Role> rolelist() {
		// TODO Auto-generated method stub
		return roleMapper.rolelist();
	}
	   @Override
	    public List<User> queryUsers(String userName, String userRole,
	            int start, int size) {
	        start = (start-1)*size;
	        return userMapper.queryUsers(userName, userRole, start, size);
	    }
	    @Override
	    public int getUserCount(String userName, String userRole) {
	        return userMapper.getUserCount(userName, userRole);
	    }
		@Override
		public boolean delet(int delid) {
			// TODO Auto-generated method stub
			boolean flag = false;
	        int rows = userMapper.delet(delid);
	        if(rows>0){
	            flag=true;
	        }
	        return flag;

		}
		@Override
		public User findById(int uid) {
			// TODO Auto-generated method stub
			return userMapper.findById(uid);
			
		}
		@Override
		public void update(User user) {
			// TODO Auto-generated method stub
			userMapper.update(user);
		}
		@Override
		public String findOldpwd(String username) {
			// TODO Auto-generated method stub
			return userMapper.findOldpwd(username);
		}
		@Override
		public void pwdupdate(String username,String newpassword) {
			// TODO Auto-generated method stub
			userMapper.pwdupdate(username,newpassword);
		}
		@Override
		public int finduserid(String username) {
			// TODO Auto-generated method stub
			return userMapper.finduserid(username);
		}
	

}
