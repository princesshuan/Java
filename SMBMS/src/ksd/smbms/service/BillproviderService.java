package ksd.smbms.service;

import java.util.List;

import ksd.smbms.pojo.Billprovider;



public interface BillproviderService {
	public List<Billprovider> queryProviders(String proCode,String proName,int start,int size);
	   
    public int getProviderCount(String proCode,String proName);

	public void add(Billprovider provider);

	public Billprovider findById(int proid);

	public void updateprovider(Billprovider provider);

	public int findbill(int proid);

	public Billprovider checkCode(String proCode);

	public Billprovider checkName(String proName);

	public void delet(int proid);
	
}
