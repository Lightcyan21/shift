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
			pre = con.prepareStatement("insert into admonition (preis) values (?);", Statement.RETURN_GENERATED_KEYS);

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
				adm.setId(result.getLong("admonitionID"));
				adm.setJobID(result.getInt("jobID"));
				adm.setPreis(result.getDouble("preis"));
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
		
		Connection con;
		con = DBUtil.getConnection();

//		ArrayList<Admonition> admList = new ArrayList<>();
		Admonition adm = new Admonition();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from admonition where admonitionID =?;");

			pre.setDouble(1, (int) id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				
				adm.setId(result.getLong("admonitionID"));
				adm.setJobID(result.getInt("jobID"));
				adm.setPreis(result.getDouble("preis"));

//				admList.add(adm);

			}

			// pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adm;
	}

	@Override
	public void persist(Admonition entity) {
		
		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("update admonition SET admonitionID = ?, jobID = ?, preis = ? WHERE admonitionID = " + entity.getId());
			
			pre.setLong(1, entity.getId());
			pre.setInt(2, entity.getJobID());
			pre.setDouble(3, entity.getPreis());

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
	public void reload(Admonition entity) {

	}

	@Override
	public void detach(Admonition entity) {

	}

	@Override
	public void flush() {

	}

}
