package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Admontion extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8429005451293689378L;
	private long admontionID;

	@Override
	public Long getId() {

		return admontionID;
	}

	@Override
	public void setId(Long id) {
		admontionID = id;

	}
	/**
	 * 
	 */

}
