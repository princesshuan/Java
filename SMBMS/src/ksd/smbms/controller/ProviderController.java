package ksd.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ksd.smbms.pojo.Bill;
import ksd.smbms.pojo.Billprovider;
import ksd.smbms.service.BillService;
import ksd.smbms.service.BillproviderService;
import ksd.smbms.service.UserService;
import ksd.smbms.until.Page;
import ksd.smbms.until.Upload;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Resource(name = "providerService")
	private BillproviderService providerService;
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "billService")
	private BillService billService;

	/**
	 * 查询全部并分页
	 */
	@RequestMapping(value = "/providerlist")
	public String prividerlist(
			HttpServletRequest request,
			String currPageNo,
			@RequestParam(value = "queryProCode", required = false) String queryProCode,
			@RequestParam(value = "queryProName", required = false) String queryProName,
			Model model) {
		currPageNo = request.getParameter("pageIndex");
		if (currPageNo == null) {
			currPageNo = "1";
		}
		Page userPage = new Page();
		List<Billprovider> providerlist = providerService.queryProviders(
				queryProCode, queryProName, Integer.parseInt(currPageNo),
				userPage.getPageSize());
		int totalCount = providerService.getProviderCount(queryProCode,
				queryProName);
		userPage.setTotalCount(totalCount);
		userPage.setProviderList(providerlist);
		userPage.setCurrPageNo(Integer.parseInt(currPageNo));
		userPage.setTotalCount(totalCount);
		model.addAttribute("providerList", providerlist);
		model.addAttribute("param", userPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currPageNo", currPageNo);
		model.addAttribute("totalPageCount", userPage.getTotalPageCount());
		model.addAttribute("queryProCode", queryProCode);
		model.addAttribute("queryProName", queryProName);
		return "providerlist";
	}

	/**
	 * 添加供应商
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		return "provideradd";
	}

	@RequestMapping(value = "/addprovider", method = RequestMethod.POST)
	public String provideradd(Billprovider provider, HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile pic) throws Exception {
	    	String fileName = Upload.fileUpload(pic, request);
			String username = (String) session.getAttribute("currentuser");
			int uid = userService.finduserid(username);
			provider.setProlicence(fileName);
			provider.setCreatedby(uid);
			provider.setCreationdate(new Date());
			provider.setIsdelet(1);
			providerService.add(provider);
			return "redirect:providerlist";

	}

	/**
	 * 验证编码是否可用
	 * 
	 * @param proCode
	 * @return
	 */
	@RequestMapping(value = "/checkCode")
	@ResponseBody
	public Object ProCodeExit(@RequestParam String proCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isEmpty(proCode)) {
			resultMap.put("proCode", "no");
		} else {
			Billprovider provider = providerService.checkCode(proCode);
			if (null != provider) {
				resultMap.put("proCode", "exist");
			} else {
				resultMap.put("proCode", "noexist");
			}

		}
		return JSONArray.toJSONString(resultMap);
	}

	/*
	 * 验证供应商名称唯一性
	 */
	@RequestMapping(value = "/checkName")
	@ResponseBody
	public Object proNameExit(@RequestParam String proName) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isEmpty(proName)) {
			resultMap.put("proName", "no");
		} else {
			Billprovider provider = providerService.checkName(proName);
			if (null != provider) {
				resultMap.put("proName", "exist");
			} else {
				resultMap.put("proName", "noexist");
			}

		}
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * 修改供应商
	 */
	@RequestMapping("/findById")
	public String toUpdate(@RequestParam int proid, Model model,
			HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		Billprovider provider = providerService.findById(proid);
		model.addAttribute("provider", provider);
		return "providermodify";
	}

	@RequestMapping(value="/updateprovider", method = RequestMethod.POST)
	public String updateprovider(Billprovider provider, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile pic) throws Exception {
		  
		    String fileName = Upload.fileUpload(pic, request);
			String username = (String) session.getAttribute("currentuser");
			int uid = userService.finduserid(username);
			provider.setProlicence(fileName);
			provider.setModifyby(uid);
			provider.setModifydate(new Date());
			providerService.updateprovider(provider);
			return "redirect:providerlist";

	}

	/**
	 * 查看供应商信息
	 */
	@RequestMapping("/providerview")
	public String providerview(@RequestParam int proid, Model model,
			HttpServletRequest request) {
		Billprovider provider = providerService.findById(proid);
		model.addAttribute("provider", provider);
		List<Bill> bill = billService.findByProid(proid);
		int count = providerService.findbill(proid);
		request.setAttribute("count", count);
		model.addAttribute("bill", bill);
		return "providerview";
	}

	/**
	 * 删除供应商
	 */
	@RequestMapping("/delet")
	@ResponseBody
	public String delet(@RequestParam int proid) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (proid == 0) {
			map.put("delResult", "false");
		} else if (proid != 0 && (providerService.findbill(proid)) != 0) {

			map.put("delResult", providerService.findbill(proid));
		} else {
			providerService.delet(proid);
			map.put("delResult", "true");
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 跳转到指定页面
	 */
	@RequestMapping("login.htm")
	public String relogin() {

		return "logout";
	}

	/**
	 * 回到登陆界面
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}
}
