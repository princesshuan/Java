package ksd.smbms.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ksd.smbms.pojo.Bill;
import ksd.smbms.pojo.Billprovider;
import ksd.smbms.pojo.Goods;
import ksd.smbms.service.BillService;
import ksd.smbms.service.GoodsService;
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
@RequestMapping("/bill")
public class BillController {

	@Resource(name = "billService")
	private BillService billService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "GoodsService")
	private GoodsService goodsService;

	/**
	 * 查询全部并分页
	 */
	@RequestMapping(value = "/billlist")
	public String billList(
			HttpServletRequest request,
			String currPageNo,
			@RequestParam(value = "queryProductName", required = false) String queryProductName,
			@RequestParam(value = "queryStartDate", required = false) String queryStartDate,
			@RequestParam(value = "queryEndDate", required = false) String queryEndDate,
			@RequestParam(value = "queryProviderId", required = false) String queryProviderId,
			@RequestParam(value = "queryIsPayment", required = false) String queryIsPayment,
			Model model) {
		currPageNo = request.getParameter("pageIndex");
		if (currPageNo == null) {
			currPageNo = "1";
		}
		Page userPage = new Page();
		List<Bill> billlist = billService.queryBills(queryProductName,
				queryStartDate, queryEndDate, queryProviderId, queryIsPayment,
				Integer.parseInt(currPageNo), userPage.getPageSize());
		int totalCount = billService.getBillCount(queryProductName,
				queryStartDate, queryEndDate, queryProviderId, queryIsPayment);
		List<Billprovider> providerList = billService.providerlist();
		userPage.setProviderList(providerList);
		userPage.setCurrPageNo(Integer.parseInt(currPageNo));
		userPage.setTotalCount(totalCount);
		model.addAttribute("billList", billlist);
		model.addAttribute("providerList", providerList);
		model.addAttribute("param", userPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currPageNo", currPageNo);
		model.addAttribute("totalPageCount", userPage.getTotalPageCount());
		model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", queryProviderId);
		model.addAttribute("queryIsPayment", queryIsPayment);
		model.addAttribute("queryStartDate", queryStartDate);
		model.addAttribute("queryEndDate", queryEndDate);
		return "billlist";
	}

	/**
	 * 获取供应商
	 */

	@RequestMapping(value = "/providerlist")
	@ResponseBody
	public List<Billprovider> providerlist() {
		List<Billprovider> providerlist = billService.providerlist();
		return providerlist;
	}

	/**
	 * 添加订单
	 */
	@RequestMapping("/toAdd")
	public String toAdd(HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		return "billadd";
	}

	@RequestMapping(value="/addbill", method = RequestMethod.POST)
	public String add(Bill bill, HttpSession session, Goods goods,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile pic)
			throws Exception {
		String username = (String) session.getAttribute("currentuser");
		int uid = userService.finduserid(username);
		bill.setCreatedby(uid);
		bill.setCreationdate(new Date());
		bill.setIsdelet(1);		
		if (goodsService.findBillname(bill.getProductname())!=""&& goodsService.findBillname(bill.getProductname())!=null) {
			goods.setId(goodsService.findGoodsid(bill.getProductname()));
			int count=goodsService.findGoodscount(bill.getProductname());			
			goods.setGoodscount(count + bill.getProductcount());
			goods.setGoodsprice((bill.getTotalprice()) / (bill.getProductcount()));
			goodsService.updategoods(goods);
		} else {
			String fileName = Upload.fileUpload(pic, request);
			goods.setGoodscode("GOODS_027");
			goods.setGoodsname(bill.getProductname());
			goods.setGoodspicture(fileName);
			goods.setCreationdate(new Date());
			goods.setGoodscount(bill.getProductcount());
			goods.setGoodsprice((bill.getTotalprice()) / (bill.getProductcount()));
			goodsService.add(goods);
		}
		billService.add(bill);
		return "redirect:billlist";
	}

	/**
	 * 验证编码是否可用
	 * 
	 * @param billCode
	 * @return
	 */
	@RequestMapping(value = "/checkCode")
	@ResponseBody
	public Object ProCodeExit(@RequestParam String billCode) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isEmpty(billCode)) {
			resultMap.put("billCode", "no");
		} else {
			Bill bill = billService.checkCode(billCode);
			if (null != bill) {
				resultMap.put("billCode", "exist");
			} else {
				resultMap.put("billCode", "noexist");
			}

		}
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * 修改订单
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam int billid, Map<String, Object> map,
			HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		session.setAttribute("currentuser", username);
		Bill bill = billService.findById(billid);
		map.put("bill", bill);
		return "billmodify";
	}

	@RequestMapping("/billupdate")
	public String doUpdate(Bill bill, HttpSession session) {
		String username = (String) session.getAttribute("currentuser");
		int uid = userService.finduserid(username);
		bill.setModifyby(uid);
		bill.setModifydate(new Date());
		billService.billupdate(bill);
		return "redirect:billlist";
	}

	/**
	 * 删除订单
	 */
	@RequestMapping("/delet")
	@ResponseBody
	public String delet(@RequestParam int billid) {
		Map<String, String> map = new HashMap<String, String>();
		int pay = billService.findpay(billid);
		if (billid == 0) {
			map.put("delResult", "false");
		} else if (billid != 0 && pay == 1) {
			map.put("delResult", "no");
		} else {
			billService.delet(billid);
			map.put("delResult", "true");
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * 查看订单信息
	 * 
	 * @param uid
	 * @param map
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/billview")
	public String view(@RequestParam int billid, Map<String, Object> map,
			HttpServletRequest request) throws ParseException {
		Bill bill = billService.findById(billid);
		map.put("bill", bill);
		return "billview";
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
