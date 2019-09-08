package ksd.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ksd.smbms.mapper.BillMapper;
import ksd.smbms.mapper.BillproviderMapper;
import ksd.smbms.pojo.Bill;
import ksd.smbms.pojo.Billprovider;
import ksd.smbms.service.BillService;
@Service("billService")
public class BillServiceImpl implements BillService{

	@Resource
	private BillMapper billMapper;
	@Resource
	private BillproviderMapper billproviderMapper;

	@Override
	public List<Bill> queryBills(String productName,String startDate,String endDate, String providerId,
			String isPayment, int start, int size) {
		// TODO Auto-generated method stub
		start = (start-1)*size;
		return billMapper.queryBills(productName, startDate, endDate, providerId, isPayment, start, size);
	}

	@Override
	public int getBillCount(String productName,String startDate,String endDate, String providerId,
			String isPayment) {
		// TODO Auto-generated method stub
		return billMapper.getBillCount(productName, startDate, endDate, providerId, isPayment);
	}

	@Override
	public List<Billprovider> providerlist() {
		// TODO Auto-generated method stub
		return billproviderMapper.providerlist();
	}

	@Override
	public void add(Bill bill) {
		// TODO Auto-generated method stub
		billMapper.add(bill);
	}

	@Override
	public Bill findById(int bid) {
		// TODO Auto-generated method stub
		return billMapper.findById(bid);
	}

	@Override
	public void billupdate(Bill bill) {
		// TODO Auto-generated method stub
		billMapper.billupdate(bill);
	}

	@Override
	public int findpay(int billid) {
		// TODO Auto-generated method stub
		return billMapper.findpay(billid);
	}

	@Override
	public List<Bill> findByProid(int proid) {
		// TODO Auto-generated method stub
		return billMapper.findByProid(proid);
	}

	@Override
	public int findBillCount(int proid) {
		// TODO Auto-generated method stub
		return billMapper.findBillCount(proid);
	}

	@Override
	public void delet(int billid) {
		// TODO Auto-generated method stub
		billMapper.delet(billid);
	}

	@Override
	public Bill checkCode(String billCode) {
		// TODO Auto-generated method stub
		return billMapper.checkCode(billCode);
	}


}
