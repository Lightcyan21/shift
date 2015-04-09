package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Statement;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.House;

public class HouseDAO extends AbstractDAO<House> implements DAO<House> {

	@Override
	public House create() {

		House house = new House();
		int key = 0;

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement(
							"insert into house (plz, strasse, ort, hausnr, stockwerke, anzahlWohnungen, gartenflaeche, flaeche) values (?, ?, ?, ?, ?, ?, ?, ?);",
							Statement.RETURN_GENERATED_KEYS);

			pre.setString(1, null);
			pre.setString(2, null);
			pre.setString(3, null);
			pre.setInt(4, 0);
			pre.setInt(5, 0);
			pre.setInt(6, 0);
			pre.setDouble(7, 0);
			pre.setDouble(8, 0);

			pre.execute();

			ResultSet rs = pre.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}

			house.setId((long) key);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return house;
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
				house.setSeen(result.getBoolean("seen"));

				houseList.add(house);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (houseList.size() != 0) {
			return houseList;
		} else {
			return null;
		}

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
				house.setSeen(result.getBoolean("seen"));

			}

			// pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (house.getId() != 0) {
			return house;
		} else {
			return null;
		}

	}

	public HashMap<Long, House> getHouseWithEmptyApts(List<Long> list) {

		HashMap<Long, House> houseHM = new HashMap<>();

		Connection con;
		con = DBUtil.getConnection();

		for (Long long1 : list) {

			try {
				PreparedStatement pre;
				pre = con
						.prepareStatement("select * from house where houseID = ?;");

				pre.setLong(1, long1);

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
					house.setSeen(result.getBoolean("seen"));

					houseHM.put(result.getLong("houseID"), house);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (houseHM.size() != 0) {
			return houseHM;
		} else {
			return null;
		}

	}

	public List<House> getIfStatusNotSeen() {

		Connection con;
		con = DBUtil.getConnection();

		List<House> houseList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("select * from house where seen = false;");

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
				house.setSeen(result.getBoolean("seen"));

				houseList.add(house);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (houseList.size() != 0) {
			return houseList;
		} else {
			return null;
		}

	}

	@Override
	public boolean persist(House entity) {

		boolean rt = false;

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update house SET houseID = ?, plz = ?, strasse = ?, ort = ?, hausnr = ?, stockwerke = ?, anzahlWohnungen = ?, gartenflaeche = ?, flaeche = ?, seen = ? WHERE houseID = ?;");

			pre.setLong(1, entity.getId());
			pre.setString(2, entity.getPlz());
			pre.setString(3, entity.getStrasse());
			pre.setString(4, entity.getOrt());
			pre.setString(5, entity.getHausnr());
			pre.setInt(6, entity.getStockwerke());
			pre.setInt(7, entity.getAnzahlWohnungen());
			pre.setDouble(8, entity.getGartenflaeche());
			pre.setDouble(9, entity.getFlaeche());
			pre.setBoolean(10, entity.isSeen());
			pre.setLong(11, entity.getId());

			if (entity.getId() != 0 && entity.getPlz() != null
					&& entity.getStrasse() != null && entity.getOrt() != null
					&& entity.getHausnr() != null
					&& entity.getStockwerke() != 0
					&& entity.getAnzahlWohnungen() != 0
					&& entity.getGartenflaeche() != 0
					&& entity.getFlaeche() != 0) {
				pre.executeUpdate();
				rt = true;
			} else {
				rt = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rt;
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
