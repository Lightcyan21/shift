package persistence.entity.impl;


import persistence.entity.AbstractEntity;

public class Job extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private int jobID ;
	private String jobName;
	private int wohnungsID;
	private int status;
	private int statusRechnung;
	private int statusBestaetigung;
	private int statusWeiterleitung;
	

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {
		
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getWohnungsID() {
		return wohnungsID;
	}

	public void setWohnungsID(int wohnungsID) {
		this.wohnungsID = wohnungsID;
	}

	public int getStatusRechnung() {
		return statusRechnung;
	}

	public void setStatusRechnung(int statusRechnung) {
		this.statusRechnung = statusRechnung;
	}

	public int getStatusBestaetigung() {
		return statusBestaetigung;
	}

	public void setStatusBestaetigung(int statusBestaetigung) {
		this.statusBestaetigung = statusBestaetigung;
	}

	public int getStatusWeiterleitung() {
		return statusWeiterleitung;
	}

	public void setStatusWeiterleitung(int statusWeiterleitung) {
		this.statusWeiterleitung = statusWeiterleitung;
	}

}
