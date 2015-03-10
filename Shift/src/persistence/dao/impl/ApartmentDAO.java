package persistence.dao.impl;

import java.util.List;

import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Apartment;

public class ApartmentDAO extends AbstractDAO<Apartment> implements
		DAO<Apartment> {

	@Override
	public Apartment create() {
		// Apartment apartment = new Apartment();
		// apartment.setId(++counter);
		return new Apartment();
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
		// nicht nutzbar, da ApartmentID ein String ist. Vgl. getApartment()
		return null;
	}

	public Apartment getApartment(String id) {
		// Objekt aus Datenbank laden
		
		// Attribute einem neuem Apartmentobjekt zuweisen
		
		return new Apartment();
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
