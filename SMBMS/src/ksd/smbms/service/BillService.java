package ksd.smbms.service;

import java.util.List;

import ksd.smbms.pojo.Bill;
import ksd.smbms.pojo.Billprovider;


public interface BillService {

	public List<Bill> queryBills(String productName,String startDate,String endDate,String providerId,String isPayment,int start,int size);
	   
    public int getBillCount(String productName,String startDate,String endDate,String providerId,String isPayment);

    public List<Billprovider>providerlist();

	public void add(Bill bill);

	public Bill findById(int bid);

	public void billupdate(Bill bill);

	public int findpay(int billid);

	public List<Bill> findByProid(int proid);

	public int findBillCount(int proid);

	public void delet(int billid);

	public Bill checkCode(String billCode);

}
