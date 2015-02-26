package persistence.entity.impl;

import java.util.Date;

import persistence.entity.AbstractEntity;

public class Message extends AbstractEntity{


	private static final long serialVersionUID = 563557762359145825L;
	
	Long msg_id;
	Date createDate;
	String message;
	String author;
	String recipient;

	@Override
	public Long getId() {
		return msg_id;
	}

	@Override
	public void setId(Long id) {
		this.msg_id = id;
	}

	public Long getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(Long msg_id) {
		this.msg_id = msg_id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
