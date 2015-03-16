package persistence.dao.impl;

import java.util.List;

import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Order;

public class OrderDAO extends AbstractDAO<Order> implements DAO<Order> {

	@Override
	public Order create() {
		return null;
	}

	@Override
	public void delete(Order entity) {
		
	}

	@Override
	public List<Order> findAll() {
		return null;
	}

	@Override
	public Order getById(long id) {
		return null;
	}

	@Override
	public void persist(Order entity) {
		
	}

	@Override
	public void reload(Order entity) {
		
	}

	@Override
	public void detach(Order entity) {
		
	}

	@Override
	public void flush() {
		
	}

}
