package beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int postingId;
	private String body;
	private Date date;
	private int userId;

	public int getPostingId() {
		return postingId;
	}

	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getdate() {
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
	
}