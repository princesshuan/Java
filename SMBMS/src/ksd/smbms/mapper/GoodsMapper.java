package ksd.smbms.mapper;

import java.util.List;

import ksd.smbms.pojo.Goods;

import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
	public List<Goods> queryGoods(@Param("goodsCode")String goodsCode,@Param("goodsName")String goodsName,@Param("start")int start,@Param("size")int size);
	   
    public int getGoodsCount(@Param("goodsCode")String goodsCode,@Param("goodsName")String goodsName);

	public void add(Goods goods);

	public Goods findById(int gid);

	public void updategoods(Goods goods);

	public String findBillname(String productcode);

	public int findGoodsid(String productcode);

	public int findGoodscount(String productcode);

	public List<Goods> goodslist();

	public int checkCount(int goodsName);

	public void updateCount(Goods goods);

	public int findCountByid(int gid);

	public void delet(int gid);
}
