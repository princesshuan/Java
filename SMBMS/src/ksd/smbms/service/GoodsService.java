package ksd.smbms.service;

import java.util.List;

import ksd.smbms.pojo.Goods;


public interface GoodsService {

	public List<Goods> queryGoods(String goodsCode,String goodsName,int start,int size);
	   
    public int getGoodsCount(String goodsCode,String goodsName);

	public void add(Goods goods);

	public Goods findById(int gid);

	public void updategoods(Goods goods);

	public String findBillname(String productname);

	public int findGoodsid(String productname);

	public int findGoodscount(String productname);

	public List<Goods> goodslist();

	public int checkCount(int goodsName);

	public void updateCount(Goods goods);

	public int findCountByid(int gid);

	public void delet(int gid);
	
}
