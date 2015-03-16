package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Apartment;

public class ApartmentDAO extends AbstractDAO<Apartment> implements
		DAO<Apartment> {

	@Override
	public Apartment create() {

		return null;
	}

	public Apartment createNew(String id) {

		Apartment apt;
		apt = new Apartment();

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("insert into apartment (wohnflaeche, zimmeranzahl, mieteranzahl, wohnungsid) values (?, ?, ?, ?);");

			pre.setDouble(1, 0);
			pre.setInt(2, 0);
			pre.setInt(3, 0);
			pre.setString(4, id);

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apt;

	}

	@Override
	public void delete(Apartment entity) {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("delete from apartment where wohnungsID = ?;");

			pre.setString(1, entity.getAptID());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Apartment> findAll() {

		Connection con;
		con = DBUtil.getConnection();

		List<Apartment> aptList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from apartment");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Apartment apt = new Apartment();
				apt.setAptID(result.getString("wohnungsID"));
				// apt.setMieteranzahl(result.getInt("mieteranzahl")); //falls
				// n�tig
				// apt.setZimmeranzahl(result.getInt("zimmeranzahl")); // *
				// apt.setWohnflaeche(result.getDouble("wohnflaeche")); // *

				aptList.add(apt);
			}

			// pre.execute(); //notwendig? oder doppelt?

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return aptList;
	}

	@Override
	public Apartment getById(long id) {
		// nicht nutzbar, da ApartmentID ein String ist. Vgl. getApartment()
		return null;
	}

	public Apartment getApartment(String id) {
		// Objekt aus Datenbank laden

		// Attribute einem neuem Apartmentobjekt zuweisen

		Connection con;
		con = DBUtil.getConnection();

		ArrayList<Apartment> aptList = new ArrayList<>();

		Apartment apt = new Apartment();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("select * from apartment where wohnungsID = ?;");

			pre.setString(1, id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				apt.setAptID(result.getString("wohnungsID"));
				apt.setMieteranzahl(result.getInt("mieteranzahl")); // falls
																	// n�tig
				apt.setZimmeranzahl(result.getInt("zimmeranzahl")); // *
				apt.setWohnflaeche(result.getDouble("wohnflaeche")); // *

				aptList.add(apt);
			}

			// apt = aptList[1]; //falsch

			// pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return apt;
	}

	@Override
	public void persist(Apartment entity) {
		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update appartment SET WohnID = ?, wohnflaeche = ?, zimmeranzahl = ?, mieteranzahl = ? WHERE wohnID = "
							+ entity.getAptID());
			// TODO ueberarbeiten da wohnid (aptid) String sein muss (Form:
			// "1.1.1")
			pre.setString(1, entity.getAptID());
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
