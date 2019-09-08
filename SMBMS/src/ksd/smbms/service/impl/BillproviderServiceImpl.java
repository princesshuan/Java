package ksd.smbms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ksd.smbms.mapper.BillproviderMapper;
import ksd.smbms.pojo.Billprovider;
import ksd.smbms.service.BillproviderService;


@Service("providerService")
public class BillproviderServiceImpl implements BillproviderService{

	@Resource
	private BillproviderMapper providerMapper;
	
	@Override
	public List<Billprovider> queryProviders(String proCode, String proName,
			int start, int size) {
		// TODO Auto-generated method stub
		start = (start-1)*size;
		return providerMapper.queryProviders(proCode, proName, start, size);
	}

	@Override
	public int getProviderCount(String proCode, String proName) {
		// TODO Auto-generated method stub
		return providerMapper.getProviderCount(proCode, proName);
	}

	@Override
	public void add(Billprovider provider) {
		// TODO Auto-generated method stub
		providerMapper.add(provider);
	}

	@Override
	public Billprovider findById(int proid) {
		// TODO Auto-generated method stub
		return providerMapper.findById(proid);
	}

	@Override
	public void updateprovider(Billprovider provider) {
		// TODO Auto-generated method stub
		providerMapper.updateprovider(provider);
	}

	@Override
	public int findbill(int proid) {
		// TODO Auto-generated method stub
		return providerMapper.findbill(proid);
	}

	@Override
	public Billprovider checkCode(String proCode) {
		// TODO Auto-generated method stub
		return providerMapper.checkCode(proCode);
	}

	@Override
	public Billprovider checkName(String proName) {
		// TODO Auto-generated method stub
		return providerMapper.checkName(proName);
	}

	@Override
	public void delet(int proid) {
		// TODO Auto-generated method stub
		providerMapper.delet(proid);
	}


}
