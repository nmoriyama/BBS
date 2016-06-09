package beans;
import java.io.Serializable;

public class UserComments implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int postingId;
	private int branchId;
	private String body;
	private int userId;
	private String account;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostingId() {
		return postingId;
	}

	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}
	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
