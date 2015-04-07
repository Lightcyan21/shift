package persistence.entity.impl;

import persistence.entity.AbstractEntity;


public class Insurance extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6201175042485254938L;
	private long houseID;
	private double betrag;

	public long getHouseID() {
		return houseID;
	}

	public void setHouseID(long houseID) {
		this.houseID = houseID;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}
	@Deprecated
	@Override
	public Long getId() {
		return null;
	}
	@Deprecated
	@Override
	public void setId(Long id) {
		
	}

}
