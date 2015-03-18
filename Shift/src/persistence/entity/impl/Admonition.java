package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Admonition extends AbstractEntity {

	private static final long serialVersionUID = 8429005451293689378L;
	private long admonitionID;
	private int jobID;
	private double preis;

	@Override
	public Long getId() {
		return admonitionID;
	}

	@Override
	public void setId(Long id) {
		admonitionID = id;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public int getJobID() {
		return jobID;
	}

	public void setJobID(int jobID) {
		this.jobID = jobID;
	}

}
