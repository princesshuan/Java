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
	// ����log4j��־
	private static final Logger log = Logger.getLogger(UserController.class);

	/**
	 * ��½����
	 */
	@RequestMapping("/login.do")
	public String login(@RequestParam("userCode") String username,
			@RequestParam("userPassword") String userpwd, HttpSession session) {
		User user = userService.login(username, userpwd);
		/*if (userpwd != null && username != null) {*/
			if (user != null) {
				log.info("��½�ɹ���\n�û�����" + username + "\n���룺" + userpwd);
				session.setAttribute("currentuser", user.getUsername());
				session.setAttribute("userrole", user.getUserrole());
				return "frame";
			} else {
				session.setAttribute("error", "�û���������������������룡");
				return "login";
			}
			/*}
		else{
			return null;
		}*/
	}

	/**
	 * �˳���¼
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("currentuser");
		session.removeAttribute("error");
		log.info("�˳�ϵͳ��");
		return "login";
	}

	
}
