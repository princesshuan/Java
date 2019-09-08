package ksd.smbms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ksd.smbms.pojo.Goods;
import ksd.smbms.service.GoodsService;
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
@RequestMapping("/goods")
public class GoodsController {

	@Resource(name = "GoodsService")
	private GoodsService goodsService;

	// private static final Logger logger =
	// Logger.getLogger(GoodsController.class);

	/**
	 * 查询全部并分页
	 */
	@RequestMapping(value = "/goodslist")
	public String goodslist(
			HttpServletRequest request,
			String currPageNo,
			@RequestParam(value = "queryGoodsCode", required = false) String queryGoodsCode,
			@RequestParam(value = "queryGoodsName", required = false) String queryGoodsName,
			Model model) {
		currPageNo = request.getParameter("pageIndex");
		if (currPageNo == null) {
			currPageNo = "1";
		}
		Page userPage = new Page();
		List<Goods> goodslist = goodsService.queryGoods(queryGoodsCode,
				queryGoodsName, Integer.parseInt(currPageNo),
				userPage.getPageSize());
		int totalCount = goodsService.getGoodsCount(queryGoodsCode,
				queryGoodsName);
		userPage.setTotalCount(totalCount);
		userPage.setCurrPageNo(Integer.parseInt(currPageNo));
		userPage.setTotalCount(totalCount);
		model.addAttribute("goodsList", goodslist);
		model.addAttribute("param", userPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currPageNo", currPageNo);
		model.addAttribute("totalPageCount", userPage.getTotalPageCount());
		model.addAttribute("queryGoodsCode", queryGoodsCode);
		model.addAttribute("queryGoodsName", queryGoodsName);
		return "goodslist";
	}

	/**
	 * 商品出库
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/toOut")
	public String toAdd(HttpSession session) {
		return "goodsout";
	}

	@RequestMapping(value = "/outgoods", method = RequestMethod.POST)
	public String goodsout(Goods goods,@RequestParam("goodsname") int goodsName,@RequestParam("goodscount") int goodsCount) {
		int count = goodsService.checkCount(goodsName);
		goods.setId(goodsName);		
		goods.setGoodscount(count-goodsCount);
		goodsService.updateCount(goods);
		return "redirect:goodslist";
	}
	
	/**
	 * 获取商品列表
	 * @return
	 */
	@RequestMapping("/Goodslist")
	@ResponseBody
	public List<Goods> goodslist(){
		List<Goods> goodslist=goodsService.goodslist();
		return goodslist;
	}
	

	/**
	 * 验证商品库存
	 */
	@RequestMapping(value = "/checkCount")
	@ResponseBody
	public Object GoodsCodeExit(@RequestParam int goodsName,@RequestParam int goodsCount) {
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isEmpty(goodsCount)) {
			resultMap.put("goodsCount", "no");
		} else {
			int count = goodsService.checkCount(goodsName);
			if (count>=goodsCount) {
				resultMap.put("goodsCount", "exist");
			} else {
				resultMap.put("goodsCount", "noexist");
			}

		}
		return JSONArray.toJSONString(resultMap);
	}

	
	
	/**
	 * 查看商品信息
	 */
	@RequestMapping("/goodsview")
	public String providerview(@RequestParam int gid, Model model,
			HttpServletRequest request) {
		Goods goods = goodsService.findById(gid);
		model.addAttribute("goods", goods);
		return "goodsview";
	}

	/**
	 * 修改数据
	 */
	@RequestMapping("/findById")
	public String toUpdate(@RequestParam int gid, Model model,
			HttpSession session) {
		Goods goods = goodsService.findById(gid);
		model.addAttribute("goods", goods);
		return "goodsmodify";
	}

	@RequestMapping(value = "/updategoods", method = RequestMethod.POST)
	public String updateprovider(Goods goods, HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile pic)
			throws Exception {

		String fileName = Upload.fileUpload(pic, request);
		goods.setGoodspicture(fileName);
		goodsService.updategoods(goods);
		return "redirect:goodslist";

	}

	/**
	 * 删除商品
	 */
	@RequestMapping("/delet")
	@ResponseBody
	public String delet(@RequestParam int gid){
		Map<String, String> map = new HashMap<String, String>();
		int count = goodsService.findCountByid(gid);
		if (count > 0) {
			map.put("delResult", "false");
		}else {
			goodsService.delet(gid);
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
