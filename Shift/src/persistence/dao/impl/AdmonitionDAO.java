package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Admonition;

public class AdmonitionDAO extends AbstractDAO<Admonition> implements
		DAO<Admonition> {

	@Override
	public Admonition create() {

		Admonition adm = new Admonition();
		int key = 0;

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement(
					"insert into admonition (preis) values (?);",
					Statement.RETURN_GENERATED_KEYS);

			pre.setDouble(1, 0);
			
			pre.execute();

			ResultSet rs = pre.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}

			adm.setId((long) key);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adm;

	}

	@Override
	public void delete(Admonition entity) {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("delete from admonition where admonitionID = ?;");

			pre.setLong(1, entity.getId());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Admonition> findAll() {

		Connection con;
		con = DBUtil.getConnection();

		Admonition adm = new Admonition();
		List<Admonition> admList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from admonition");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				adm.setId(result.getLong("admonitionID"));
				adm.setJobID(result.getInt("jobID"));
				adm.setPreis(result.getDouble("preis"));
				adm.setSeen(result.getBoolean("seen"));

				admList.add(adm);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (admList.size() != 0) {
			return admList;
		} else {
			return null;
		}

	}

	@Override
	public Admonition getById(long id) {

		Connection con;
		con = DBUtil.getConnection();

		Admonition adm = new Admonition();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("select * from admonition where admonitionID =?;");

			pre.setDouble(1, (int) id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				adm.setId(result.getLong("admonitionID"));
				adm.setJobID(result.getInt("jobID"));
				adm.setPreis(result.getDouble("preis"));
				adm.setSeen(result.getBoolean("seen"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (adm.getId() != 0) {
			return adm;
		} else {
			return null;
		}

	}

	@Override
	public boolean persist(Admonition entity) {

		boolean rt = false;

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update admonition SET admonitionID = ?, jobID = ?, preis = ?, seen =? WHERE admonitionID = ?;");

			pre.setLong(1, entity.getId());
			pre.setLong(2, entity.getJobID());
			pre.setDouble(3, entity.getPreis());
			pre.setLong(4, entity.getId());
			pre.setBoolean(5, entity.isSeen());

			if (entity.getId() != 0 && entity.getJobID() != 0
					&& entity.getPreis() != 0) {
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
	public void reload(Admonition entity) {

	}

	@Override
	public void detach(Admonition entity) {

	}

	@Override
	public void flush() {

	}

}
