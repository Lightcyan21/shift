package persistence.dao;

import java.util.List;

import persistence.entity.IEntity;

public interface DAO<T extends IEntity> {

	public T create();

	public void delete(T entity);
	
	public List<T> findAll();

	public T getById(long id);

	public boolean persist(T entity);

	public void reload(T entity);
	
	public void detach(T entity);

	public void flush();
	
}
