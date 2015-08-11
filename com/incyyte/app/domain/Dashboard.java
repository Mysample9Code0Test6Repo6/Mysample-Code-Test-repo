package com.incyyte.app.domain;

public class Dashboard {

	private int incoming;
	private int sent;
	private int completed;
	private int petitions;
	
	public int getIncoming() {
		return incoming;
	}
	public void setIncoming(int incoming) {
		this.incoming = incoming;
	}
	public int getSent() {
		return sent;
	}
	public void setSent(int sent) {
		this.sent = sent;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	public int getPetitions() {
		return petitions;
	}
	public void setPetitions(int petitions) {
		this.petitions = petitions;
	}
	
	
}
