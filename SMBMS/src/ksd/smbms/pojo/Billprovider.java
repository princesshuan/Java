package ksd.smbms.pojo;

import java.util.Date;

public class Billprovider {
	private int id;

    private String procode;

    private String proname;

    private String prodesc;

    private String procontact;

    private String prophone;

    private String proaddress;

    private String profax;

    private int createdby;

    private Date creationdate;

    private Date modifydate;

    private int modifyby;
    
    private String prolicence;
    
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

	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getProdesc() {
		return prodesc;
	}

	public void setProdesc(String prodesc) {
		this.prodesc = prodesc;
	}

	public String getProcontact() {
		return procontact;
	}

	public void setProcontact(String procontact) {
		this.procontact = procontact;
	}

	public String getProphone() {
		return prophone;
	}

	public void setProphone(String prophone) {
		this.prophone = prophone;
	}

	public String getProaddress() {
		return proaddress;
	}

	public void setProaddress(String proaddress) {
		this.proaddress = proaddress;
	}

	public String getProfax() {
		return profax;
	}

	public void setProfax(String profax) {
		this.profax = profax;
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

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public int getModifyby() {
		return modifyby;
	}

	public void setModifyby(int modifyby) {
		this.modifyby = modifyby;
	}

	public String getProlicence() {
		return prolicence;
	}

	public void setProlicence(String prolicence) {
		this.prolicence = prolicence;
	}

    
}
