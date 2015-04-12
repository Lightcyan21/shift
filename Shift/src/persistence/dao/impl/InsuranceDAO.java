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
import persistence.entity.impl.Insurance;

public class InsuranceDAO extends AbstractDAO<Insurance> implements
		DAO<Insurance> {
	@Deprecated
	@Override
	public Insurance create() {

		Insurance insu = new Insurance();
		int key = 0;

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("insert into insurance (houseID, betrag) values (?, ?);");

			pre.setString(1, null);
			pre.setDouble(2, 0);

			pre.execute();

			insu.setId((long) key);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insu;
	}

	public Insurance createNew(Long houseid) {

		Insurance insu = new Insurance();
		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("insert into insurance (houseID, betrag) values (?, ?);");

			pre.setLong(1, houseid);
			pre.setDouble(2, 0);

			pre.execute();

			insu.setId((long) houseid);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insu;
	}

	@Override
	public void delete(Insurance entity) {
		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("delete from insurance where houseID = ?;");

			pre.setLong(1, entity.getId());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Insurance> findAll() {

		Connection con;
		con = DBUtil.getConnection();

		List<Insurance> insuList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from insurance");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Insurance insu = new Insurance();
				insu.setId(result.getLong("houseID"));

				insuList.add(insu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (insuList.size() != 0) {
			return insuList;
		} else {
			return null;
		}
	}

	@Deprecated
	@Override
	public Insurance getById(long id) {
		// wird nicht verwendet - Substitut = houseID
		return null;
	}

	public Insurance getByHouseID(long id) {

		Connection con;
		con = DBUtil.getConnection();

		ArrayList<Insurance> insuList = new ArrayList<>();

		Insurance insu = new Insurance();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("select * from insurance where houseID = ?;");

			pre.setLong(1, id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				insu.setId(result.getLong("houseID"));
				insu.setBetrag(result.getDouble("betrag"));

				insuList.add(insu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (insu.getId() != 0) {
			return insu;
		} else {
			return null;
		}

	}

	@Override
	public boolean persist(Insurance entity) {

		boolean rt = false;

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update insurance SET houseID = ?, betrag = ? WHERE houseID = ?;");

			pre.setLong(1, entity.getId());
			pre.setDouble(2, entity.getBetrag());
			pre.setLong(3, entity.getId());

			if (entity.getId() != 0 ) {
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
	public void reload(Insurance entity) {

	}

	@Override
	public void detach(Insurance entity) {

	}

	@Override
	public void flush() {

	}

}
