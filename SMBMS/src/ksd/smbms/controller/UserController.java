package ksd.smbms.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ksd.smbms.pojo.Role;
import ksd.smbms.pojo.User;
import ksd.smbms.service.UserService;
import ksd.smbms.until.Page;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name = "userService")
	private UserService userService;

	// ����log4j��־
	private static final Logger log = Logger.getLogger(UserController.class);

	/**
	 * ��ѯȫ������ҳ
	 */
	@RequestMapping(value = "/userlist")
	public String userlist(
			HttpServletRequest request,
			String currPageNo,
			@RequestParam(value = "queryname", required = false) String queryname,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			Model model,
			HttpSession session) {
		currPageNo = request.getParameter("pageIndex");
		if (currPageNo == null) {
			currPageNo = "1";
		}
		Page userPage = new Page();
		List<User> userlist = userService.queryUsers(queryname, queryUserRole,
				Integer.parseInt(currPageNo), userPage.getPageSize());
		int totalCount = userService.getUserCount(queryname, queryUserRole);
		List<Role> roleList = userService.rolelist();
		userPage.setTotalCount(totalCount);
		userPage.setUserList(userlist);
		userPage.setCurrPageNo(Integer.parseInt(currPageNo));
		userPage.setTotalCount(totalCount);
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		model.addAttribute("userList", userlist);
		model.addAttribute("roleList", roleList);
		model.addAttribute("param", userPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currPageNo", currPageNo);
		model.addAttribute("totalPageCount", userPage.getTotalPageCount());
		model.addAttribute("queryname", queryname);
		model.addAttribute("queryUserRole", queryUserRole);
		return "userlist";
	}

	/**
	 * ����û�
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		return "useradd";
	}

	@RequestMapping("/adduser")
	public String add(User user, HttpSession session){
		String username=(String) session.getAttribute("currentuser");
		int uid=userService.finduserid(username);
		user.setCreatedby(uid);
		user.setCreationdate(new Date());
		userService.add(user);
		return "redirect:userlist";
	}

	/*
	 * ��֤�û����Ƿ����
	 */
	@RequestMapping(value = "/checkName")
	@ResponseBody
	public Object userCodeExit(@RequestParam String userCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isEmpty(userCode)) {// ���ַ���ʲô��������Ļ�//import
											// org.apache.commons.lang.StringUtils;
			resultMap.put("userCode", "no");
		} else {
			User user = userService.checkName(userCode);
			if (null != user) {// ��ѯ���ݿ���ڸ�usercode
				resultMap.put("userCode", "exist");
			} else {
				resultMap.put("userCode", "noexist");
			}

		}
		log.info(JSONArray.toJSONString(resultMap));
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * ��ɫ��
	 */
	@ResponseBody
	@RequestMapping(value = "/rolelist")
	public List<Role> rolelist() {
		List<Role> rolelist = userService.rolelist();
		return rolelist;
	}

	/**
	 * ɾ��
	 */
	@RequestMapping("/delet")
	@ResponseBody
	public String delet(@RequestParam String uid) {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (uid == null) {
			resultMap.put("delResult", "notexist");
		} else {
			if (userService.delet(Integer.parseInt(uid))) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * ��id��ѯ
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam int uid, Map<String, Object> map,HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		User user = userService.findById(uid);
		map.put("user", user);
		return "usermodify";
	}

	/**
	 * �޸��û���Ϣ
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value = "/update")
	public String modity(User user,HttpSession session) {
		String username=(String) session.getAttribute("currentuser");
		int uid=userService.finduserid(username);
		user.setModifyby(uid);
		user.setModifydate(new Date());
		userService.update(user);
		return "redirect:userlist";
	}

	/**
	 * �鿴��Ϣ
	 * 
	 * @param uid
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/view")
	public String view(@RequestParam int uid, Map<String, Object> map,
			HttpServletRequest request) throws ParseException {
		User user = userService.findById(uid);
		map.put("user", user);
		return "userview";
	}

	/**
	 * ��ת��ָ��ҳ��
	 */
	@RequestMapping("login.htm")
	public String relogin() {

		return "logout";
	}
	/**
	 * �ص���½����
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}
	/**
	 * �޸�����
	 */
	/**
	 * ��ת���޸�����ҳ��
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/findpwd")
	public String findPwd(HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		log.info("��ǰ�û���" + username);
		session.setAttribute("currentuser", username);
		return "pwdmodify";
	}

	/**
	 * ��������Ƿ�������ȷ
	 * 
	 * @param oldpassword
	 * @param session
	 * @return
	 */
	@RequestMapping("/oldpassword")
	@ResponseBody
	public String findoldpwd(@RequestParam String oldpassword,
			HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		HashMap<String, String> map = new HashMap<String, String>();
		if (username == null) {
			map.put("result", "sessionerror");
		}
		if ("".equals(oldpassword)) {
			map.put("result", "error");
		} else {
			String findOldpwd = userService.findOldpwd(username);
			log.info("��ǰ�û����룺" + findOldpwd);
			if (oldpassword.equals(findOldpwd)) {
				map.put("result", "true");
			} else {
				map.put("result", "false");
			}
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * �޸�������
	 */
	@RequestMapping("/pwdupdate")
	public String pwdupdate(@RequestParam("newpassword") String newpassword,
			HttpSession session, HttpServletRequest request) {
		String username = (String) session.getAttribute("currentuser");

		log.info("��ǰ�û���" + username);
		userService.pwdupdate(username, newpassword);
		session.removeAttribute("currentuser");
		session.removeAttribute("error");
		return "login";
	}
	
	
}
