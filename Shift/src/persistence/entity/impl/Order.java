package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Order extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private long jobID;
	private String newJobID;
	private String jobName;
	private String wohnungsID;
	private String mieter;
	private double betrag;
	private int status;
	private boolean statusRechnung;
	private boolean statusBestaetigung;
	private boolean statusWeiterleitung;
	private boolean seen;

	@Override
	public Long getId() {
		return jobID;
	}

	@Override
	public void setId(Long jobID) {
		this.jobID = jobID;
	}

	public String getNewJobID() {
		return newJobID;
	}

	public void setNewJobID(String newJobID) {
		this.newJobID = newJobID;
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

	public boolean isStatusRechnung() {
		return statusRechnung;
	}

	public void setStatusRechnung(boolean statusRechnung) {
		this.statusRechnung = statusRechnung;
	}

	public boolean isStatusBestaetigung() {
		return statusBestaetigung;
	}

	public void setStatusBestaetigung(boolean statusBestaetigung) {
		this.statusBestaetigung = statusBestaetigung;
	}

	public boolean isStatusWeiterleitung() {
		return statusWeiterleitung;
	}

	public void setStatusWeiterleitung(boolean statusWeiterleitung) {
		this.statusWeiterleitung = statusWeiterleitung;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

}
