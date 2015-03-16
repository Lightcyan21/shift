package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private long jobID;
	private String jobName;
	private String wohnungsID;
	private String mieter;
	private double betrag;
	private int status;
	private int statusRechnung;
	private int statusBestaetigung;
	private int statusWeiterleitung;

	@Override
	public Long getId() {
		return jobID;
	}

	@Override
	public void setId(Long jobID) {
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

	public String getWohnungsID() {
		return wohnungsID;
	}

	public void setWohnungsID(String wohnungsID) {
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

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	public String getMieter() {
		return mieter;
	}

	public void setMieter(String mieter) {
		this.mieter = mieter;
	}

}
