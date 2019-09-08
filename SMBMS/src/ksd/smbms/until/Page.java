package ksd.smbms.until;

import java.util.List;

import ksd.smbms.pojo.Billprovider;
import ksd.smbms.pojo.User;

public class Page {
	// 总页数
    private int totalPageCount = 0;
    // 页面大小，即每页显示记录数
    private int pageSize = 5;
    // 记录总数
    private int totalCount;
    // 当前页码
    private int currPageNo = 1;
    // 每页订单集合
    private List<User> userList;

    private List<Billprovider> providerList;

    public int getCurrPageNo() {

        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        if (currPageNo > 0)
            this.currPageNo = currPageNo; 
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0)
            this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            // 计算总页数
            this.totalPageCount = this.totalCount % pageSize == 0 ? (this.totalCount / pageSize)
                    : (this.totalCount / pageSize + 1);
            if(currPageNo>totalPageCount)
                this.currPageNo = totalPageCount;
        }
    }

    public int getTotalPageCount() {

        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

	public List<Billprovider> getProviderList() {
		return providerList;
	}

	public void setProviderList(List<Billprovider> providerList) {
		this.providerList = providerList;
	}

	


}

