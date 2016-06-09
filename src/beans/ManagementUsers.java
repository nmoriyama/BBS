package beans;

import java.io.Serializable;

public class ManagementUsers implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String loginId;
	private String password;
	private String account;
	private String branchName;
	private String positionName;
	private String status;
	
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
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}