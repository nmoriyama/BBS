package bbsSystem.beans;

import java.io.Serializable;
import java.util.Date;


public class Posting implements Serializable{
private static final long serialVersionUID = 1L;

    private int id;
	private String subject;
	private String body;
	private String category;
	private Date date;
	private int userId;
	private String account;
	private String fromDate;
	private String toDate;
	private String surchCategory;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	public String getSurchCategory() {
		return surchCategory;
	}
	
	public void setSurchCategory(String surchCategory) {
		this.surchCategory = surchCategory;
	}
	

}
