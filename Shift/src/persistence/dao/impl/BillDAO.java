package persistence.dao.impl;

import java.util.List;

import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Bill;

public class BillDAO extends AbstractDAO<Bill> implements DAO<Bill> {

	@Override
	public Bill create() {

		return null;
	}

	@Override
	public void delete(Bill entity) {

	}

	@Override
	public List<Bill> findAll() {

		return null;
	}

	@Override
	public Bill getById(long id) {

		return null;
	}

	@Override
	public void persist(Bill entity) {

	}

	@Override
	public void reload(Bill entity) {

	}

	@Override
	public void detach(Bill entity) {

	}

	@Override
	public void flush() {

	}

}
