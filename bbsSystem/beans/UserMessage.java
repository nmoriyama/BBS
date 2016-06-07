package bbsSystem.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int postingId;
	private int branchId;
	private String subject;
	private String body;
	private String category;
	private Date date;
	private Date firstDate;
	private Date lastDate;
	private int userId;
	private String account;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
<<<<<<< HEAD
	public Date getFirstDate() {
		return firstDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	public Date getLastDate() {
		return lastDate;
	}
=======
	
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
