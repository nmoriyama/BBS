package beans;

import java.io.Serializable;

public class Management implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String loginId;
	private String password;
	private String account;
	private int branchId;
	private int positionId;
	private String status;
	private String name;
	private String name2;
	private String branchName;
	private String positionName;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getPositionName() {
		return positionName;
	}
	
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}