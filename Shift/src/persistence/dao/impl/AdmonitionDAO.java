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
import persistence.entity.impl.Admonition;
import persistence.entity.impl.Order;

public class AdmonitionDAO extends AbstractDAO<Admonition> implements
		DAO<Admonition> {

	@Override
	public Admonition create() {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("insert into admonition (preis) values (?);");

			pre.setDouble(1, 0);

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void delete(Admonition entity) {
		
		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("delete from admonition where admonitionID = ?;");

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

		List<Admonition> admList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from admonition");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Admonition adm = new Admonition();
				adm.setId(result.getLong("jobID"));
				//ergänzen. In Admonition.java ausimpementieren
				
				

				admList.add(adm);
			}

			// pre.execute(); //notwendig? oder doppelt?

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admList;

	}

	@Override
	public Admonition getById(long id) {

		return null;
	}

	@Override
	public void persist(Admonition entity) {

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
