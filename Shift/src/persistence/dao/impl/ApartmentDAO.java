package persistence.dao.impl;

import java.util.List;

import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Apartment;

public class ApartmentDAO extends AbstractDAO<Apartment> implements
		DAO<Apartment> {
	private static long counter = 1L;

	@Override
	public Apartment create() {
		Apartment apartment = new Apartment();
		apartment.setId(++counter);
		return apartment;
	}

	@Override
	public void delete(Apartment entity) {

	}

	@Override
	public List<Apartment> findAll() {

		return null;
	}

	@Override
	public Apartment getById(long id) {

		return null;
	}

	@Override
	public void persist(Apartment entity) {

	}

	@Override
	public void reload(Apartment entity) {

	}

	@Override
	public void detach(Apartment entity) {

	}

	@Override
	public void flush() {

	}

}
