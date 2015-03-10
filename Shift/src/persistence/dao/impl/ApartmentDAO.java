package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Apartment;

public class ApartmentDAO extends AbstractDAO<Apartment> implements
		DAO<Apartment> {

	@Override
	public Apartment create() {
		
		Apartment apt;
		apt = new Apartment();
		
		
		Connection con;
		con = DBUtil.getConnection();
		
		try {
			PreparedStatement pre;
			pre = con.prepareStatement("insert into appartment (wohnflaeche, zimmeranzahl, mieteranzahl) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			pre.setDouble(1, 0);
			pre.setInt(2, 10);
			pre.setInt(3, 0);
			
			pre.execute();
			
			ResultSet res;
			res = pre.getGeneratedKeys();
			if (res !=  null && res.next()) { 
				apt.setWohnID(res.getInt(1));
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return apt;
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
		Connection con = DBUtil.getConnection();
		
			try {
				PreparedStatement pre;
				pre = con.prepareStatement("update appartment SET WohnID = ?, wohnflaeche = ?, zimmeranzahl = ?, mieteranzahl = ? WHERE wohnID = " + entity.getWohnID());
				
				pre.setInt(1, entity.getWohnID());
				pre.setDouble(2, entity.getWohnflaeche());
				pre.setInt(3, entity.getZimmeranzahl());
				pre.setInt(4, entity.getMieteranzahl());
							
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
