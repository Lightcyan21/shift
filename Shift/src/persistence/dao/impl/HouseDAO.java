package persistence.dao.impl;

import java.util.List;

import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.House;

public class HouseDAO extends AbstractDAO<House> implements DAO<House> {

	@Override
	public House create() {
		return null;
	}

	@Override
	public void delete(House entity) {
	}

	@Override
	public List<House> findAll() {
		return null;
	}

	@Override
	public House getById(long id) {
		return null;
	}

	@Override
	public void persist(House entity) {
	}

	@Override
	public void reload(House entity) {
	}

	@Override
	public void detach(House entity) {
	}

	@Override
	public void flush() {

	}

}
