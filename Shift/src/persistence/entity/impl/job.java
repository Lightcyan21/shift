package persistence.entity.impl;

// KLASSE MIT JONAS ABSPRECHEN!!!

import persistence.entity.AbstractEntity;

public class job extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private int jobID ;
	private String jobName;
	private int wohnungsID;
	private int status;
	private int statusRechnung;
	private int statusBestätigung;
	private int statusWeiterleitung;
	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
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

	public int getStatusBestätigung() {
		return statusBestätigung;
	}

	public void setStatusBestätigung(int statusBestätigung) {
		this.statusBestätigung = statusBestätigung;
	}

	public int getStatusWeiterleitung() {
		return statusWeiterleitung;
	}

	public void setStatusWeiterleitung(int statusWeiterleitung) {
		this.statusWeiterleitung = statusWeiterleitung;
	}

}
