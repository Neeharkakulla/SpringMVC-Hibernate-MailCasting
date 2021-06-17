package com.api.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inbox")
public class InBoxModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String  reciever;
	private String sender;
	private String message;
	private Timestamp date;
	private String subject;
	public InBoxModel(int id, String reciever, String sender, String message, Timestamp date, String subject) {
		super();
		this.id = id;
		this.reciever = reciever;
		this.sender = sender;
		this.message = message;
		this.date = date;
		this.subject = subject;
	}
	public InBoxModel(String reciever, String sender, String message, Timestamp date, String subject) {
		super();
		this.reciever = reciever;
		this.sender = sender;
		this.message = message;
		this.date = date;
		this.subject = subject;
	}
	public InBoxModel() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "InBoxModel [id=" + id + ", reciever=" + reciever + ", sender=" + sender + ", message=" + message
				+ ", date=" + date + ", subject=" + subject + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReciever() {
		return reciever;
	}
	public void setReciever(String reciever) {
		this.reciever = reciever;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
