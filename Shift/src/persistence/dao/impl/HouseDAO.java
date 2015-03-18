package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Asset;
import persistence.entity.impl.House;

public class HouseDAO extends AbstractDAO<House> implements DAO<House> {

	@Override
	public House create() {
		
		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("insert into house (plz, strasse, ort, hausnr, stockwerke, anzahlWohnungen, gartenflaeche, flaeche) values (?, ?, ?, ?, ?, ?, ?, ?);");

			pre.setString(1, null);
			pre.setString(2, null);
			pre.setString(3, null);
			pre.setInt(4, 0);
			pre.setInt(5, 0);
			pre.setInt(6, 0);
			pre.setDouble(7, 0);
			pre.setDouble(8, 0);

			pre.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void delete(House entity) {
		
		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("delete from house where houseID = ?;");

			pre.setLong(1, entity.getId());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<House> findAll() {
		
		Connection con;
		con = DBUtil.getConnection();

		List<House> houseList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from house");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				House house = new House();
				house.setId(result.getLong("houseID"));
				house.setPlz(result.getString("plz"));
				house.setStrasse(result.getString("strasse"));
				house.setOrt(result.getString("ort"));
				house.setHausnr(result.getString("hausnr"));
				house.setStockwerke(result.getInt("stockwerke"));
				house.setAnzahlWohnungen(result.getInt("anzahlWohnungen"));
				house.setGartenflaeche(result.getDouble("gartenflaeche"));
				house.setFlaeche(result.getDouble("flaeche"));

				houseList.add(house);
			}

			// pre.execute(); //notwendig? oder doppelt?

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return houseList;
	}

	@Override
	public House getById(long id) {
		
		Connection con;
		con = DBUtil.getConnection();

		House house = new House();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from house where houseID =?;");

			pre.setDouble(1, (int) id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				
				house.setId(result.getLong("houseID"));
				house.setPlz(result.getString("plz"));
				house.setStrasse(result.getString("strasse"));
				house.setOrt(result.getString("ort"));
				house.setHausnr(result.getString("hausnr"));
				house.setStockwerke(result.getInt("stockwerke"));
				house.setAnzahlWohnungen(result.getInt("anzahlWohnungen"));
				house.setGartenflaeche(result.getDouble("gartenflaeche"));
				house.setFlaeche(result.getDouble("flaeche"));

			}

			// pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return house;
	}

	@Override
	public void persist(House entity) {
		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("update house SET houseID = ?, plz = ?, strasse = ?, ort = ?, hausnr = ?, stockwerke = ?, anzahlWohnungen = ?, gartenflaeche = ?, flaeche = ? WHERE assetID = " + entity.getId());
			
			pre.setLong(1, entity.getId());
			pre.setString(2, entity.getPlz());
			pre.setString(3, entity.getStrasse());
			pre.setString(4, entity.getOrt());
			pre.setString(4, entity.getHausnr());
			pre.setInt(4, entity.getStockwerke());
			pre.setInt(4, entity.getAnzahlWohnungen());
			pre.setDouble(4, entity.getGartenflaeche());
			pre.setDouble(4, entity.getFlaeche());
			
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
	public void reload(House entity) {
	}

	@Override
	public void detach(House entity) {
	}

	@Override
	public void flush() {

	}

}
