package ksd.smbms.mapper;

import java.util.List;

import ksd.smbms.pojo.Bill;

import org.apache.ibatis.annotations.Param;

public interface BillMapper {
	public List<Bill> queryBills(@Param("productName")String productName,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("providerId")String providerId,@Param("isPayment")String isPayment,@Param("start")int start,@Param("size")int size);
	   
    public int getBillCount(@Param("productName")String productName,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("providerId")String providerId,@Param("isPayment")String isPayment);

	public void add(Bill bill);

	public Bill findById(int bid);

	public void billupdate(Bill bill);

	public int findpay(int billid);

	public List<Bill> findByProid(int proid);

	public int findBillCount(int proid);

	public void delet(int billid);

	public Bill checkCode(String billCode);

}
