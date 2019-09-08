package ksd.smbms.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {

	private int id;
	
	private String goodscode;
	
	private String goodsname;
	
	private String goodspicture;
	
	private int goodscount;
	
	private double goodsprice;
	
	private Date creationdate;
	
	public Date getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(String goodscode) {
		this.goodscode = goodscode;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getGoodspicture() {
		return goodspicture;
	}
	public void setGoodspicture(String goodspicture) {
		this.goodspicture = goodspicture;
	}
	public int getGoodscount() {
		return goodscount;
	}
	public void setGoodscount(int goodscount) {
		this.goodscount = goodscount;
	}
	public double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}
	
}
