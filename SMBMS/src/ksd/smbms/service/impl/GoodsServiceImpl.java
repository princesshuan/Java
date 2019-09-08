package ksd.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ksd.smbms.mapper.GoodsMapper;
import ksd.smbms.pojo.Goods;
import ksd.smbms.service.GoodsService;

@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService{

	@Resource
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Goods> queryGoods(String goodsCode, String goodsName,
			int start, int size) {
		// TODO Auto-generated method stub
		start = (start-1)*size;
		return goodsMapper.queryGoods(goodsCode, goodsName, start, size);
	}

	@Override
	public int getGoodsCount(String goodsCode, String goodsName) {
		// TODO Auto-generated method stub
		return goodsMapper.getGoodsCount(goodsCode, goodsName);
	}

	@Override
	public void add(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.add(goods);
	}

	@Override
	public Goods findById(int gid) {
		// TODO Auto-generated method stub
		return goodsMapper.findById(gid);
	}

	@Override
	public void updategoods(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.updategoods(goods);
	}

	@Override
	public String findBillname(String productname) {
		// TODO Auto-generated method stub
		return goodsMapper.findBillname(productname);
	}

	@Override
	public int findGoodsid(String productname) {
		// TODO Auto-generated method stub
		return goodsMapper.findGoodsid(productname);
	}

	@Override
	public int findGoodscount(String productname) {
		// TODO Auto-generated method stub
		return goodsMapper.findGoodscount(productname);
	}

	@Override
	public List<Goods> goodslist() {
		// TODO Auto-generated method stub
		return goodsMapper.goodslist();
	}

	@Override
	public int checkCount(int goodsName) {
		// TODO Auto-generated method stub
		return goodsMapper.checkCount(goodsName);
	}

	@Override
	public void updateCount(Goods goods) {
		// TODO Auto-generated method stub
		goodsMapper.updateCount(goods);
	}

	@Override
	public int findCountByid(int gid) {
		// TODO Auto-generated method stub
		return goodsMapper.findCountByid(gid);
	}

	@Override
	public void delet(int gid) {
		// TODO Auto-generated method stub
		goodsMapper.delet(gid);
	}

}
