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
		// TODO Auto-generated method stub

	}

	@Override
	public List<Apartment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Apartment getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persist(Apartment entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reload(Apartment entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void detach(Apartment entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

}
