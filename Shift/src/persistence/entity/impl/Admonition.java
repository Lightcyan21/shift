package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Admonition extends AbstractEntity {

	private static final long serialVersionUID = 8429005451293689378L;
	private long admonitionID;

	@Override
	public Long getId() {
		return admonitionID;
	}

	@Override
	public void setId(Long id) {
		admonitionID = id;
	}

}
