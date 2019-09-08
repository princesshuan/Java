package ksd.smbms.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Bill {
	private int id;

    private String billcode;

    private String productname;

    private String productdesc;

    private String productunit;

    private int productcount;

    private double totalprice;

    private Integer ispayment;

    private int createdby;

    private Date creationdate;

    private int modifyby;

    private Date modifydate;

    private int providerid;
    
    private String providername;
    
    private int isdelet;

	public int getIsdelet() {
		return isdelet;
	}

	public void setIsdelet(int isdelet) {
		this.isdelet = isdelet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductdesc() {
		return productdesc;
	}

	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}

	public String getProductunit() {
		return productunit;
	}

	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}

	public int getProductcount() {
		return productcount;
	}

	public void setProductcount(int productcount) {
		this.productcount = productcount;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public Integer getIspayment() {
		return ispayment;
	}

	public void setIspayment(Integer ispayment) {
		this.ispayment = ispayment;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public int getModifyby() {
		return modifyby;
	}

	public void setModifyby(int modifyby) {
		this.modifyby = modifyby;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public int getProviderid() {
		return providerid;
	}

	public void setProviderid(int providerid) {
		this.providerid = providerid;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}
    
}
