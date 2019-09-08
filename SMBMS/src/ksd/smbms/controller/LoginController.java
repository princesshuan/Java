package ksd.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import ksd.smbms.pojo.User;
import ksd.smbms.service.UserService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/index")
public class LoginController {
	@Resource(name = "userService")
	private UserService userService;
	// 加入log4j日志
	private static final Logger log = Logger.getLogger(UserController.class);

	/**
	 * 登陆功能
	 */
	@RequestMapping("/login.do")
	public String login(@RequestParam("userCode") String username,
			@RequestParam("userPassword") String userpwd, HttpSession session) {
		User user = userService.login(username, userpwd);
		/*if (userpwd != null && username != null) {*/
			if (user != null) {
				log.info("登陆成功！\n用户名：" + username + "\n密码：" + userpwd);
				session.setAttribute("currentuser", user.getUsername());
				session.setAttribute("userrole", user.getUserrole());
				return "frame";
			} else {
				session.setAttribute("error", "用户名或密码错误，请重新输入！");
				return "login";
			}
			/*}
		else{
			return null;
		}*/
	}

	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currentuser");
		session.removeAttribute("error");
		log.info("退出系统！");
		return "login";
	}

	
}
