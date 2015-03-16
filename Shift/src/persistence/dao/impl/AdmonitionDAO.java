package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Admonition;
import persistence.entity.impl.Apartment;

public class AdmonitionDAO extends AbstractDAO<Admonition> implements
		DAO<Admonition> {

	@Override
	public Admonition create() {
		
		Admonition adm;
		adm = new Admonition();

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("insert into admonition (admonitionID, jobID, price) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			pre.set(1, 0);
			pre.setInt(2, 0);
			pre.setInt(3, 0);

			pre.execute();

			// ResultSet res; //Result/Rückgabewert erforderlich?
			// res = pre.getGeneratedKseys();
			// if (res != null && res.next()) {
			// // TODO bitte ueberarbeiten
			// //apt.setWohnID(res.getInt(1));
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apt;


		return null;
	}

	@Override
	public void delete(Admonition entity) {

	}

	@Override
	public List<Admonition> findAll() {

		return null;
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
