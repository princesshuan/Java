package ksd.smbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ksd.smbms.pojo.Billprovider;

public interface BillproviderMapper {

	public List<Billprovider>providerlist();
	
	public List<Billprovider> queryProviders(@Param("proCode")String proCode,@Param("proName")String proName,@Param("start")int start,@Param("size")int size);
	   
    public int getProviderCount(@Param("proCode")String proCode,@Param("proName")String proName);

	public void add(Billprovider provider);

	public Billprovider findById(int proid);

	public void updateprovider(Billprovider provider);

	public int findbill(int proid);

	public Billprovider checkCode(String proCode);

	public Billprovider checkName(String proName);

	public void delet(int proid);
}
