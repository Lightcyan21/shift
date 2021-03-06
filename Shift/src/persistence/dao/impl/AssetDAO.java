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
import persistence.entity.impl.Asset;

public class AssetDAO extends AbstractDAO<Asset> implements DAO<Asset> {

	@Override
	public Asset create() {

		Asset asset = new Asset();
		int key = 0;

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement(
							"insert into asset (einzelpreis, bezeichnung) values (?, ?);",
							Statement.RETURN_GENERATED_KEYS);

			pre.setDouble(1, 0);
			pre.setString(2, null);

			pre.execute();

			ResultSet rs = pre.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}

			asset.setId((long) key);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return asset;
	}

	@Override
	public void delete(Asset entity) {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("delete from asset where assetID = ?;");

			pre.setLong(1, entity.getId());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Asset> findAll() {

		Connection con;
		con = DBUtil.getConnection();

		List<Asset> assetList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from asset");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Asset asset = new Asset();
				asset.setId(result.getLong("assetID"));
				asset.setBillID(result.getInt("billID"));
				asset.setEinzelpreis(result.getDouble("einzelpreis"));
				asset.setBezeichnung(result.getString("bezeichnung"));

				assetList.add(asset);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (assetList.size() != 0) {
			return assetList;
		} else {
			return null;
		}

	}

	@Override
	public Asset getById(long id) {

		Connection con;
		con = DBUtil.getConnection();

		Asset asset = new Asset();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from asset where assetID =?;");

			pre.setDouble(1, (int) id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				asset.setId(result.getLong("assetID"));
				asset.setBillID(result.getInt("billID"));
				asset.setEinzelpreis(result.getDouble("einzelpreis"));
				asset.setBezeichnung(result.getString("bezeichnung"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (asset.getId() != 0) {
			return asset;
		} else {
			return null;
		}

	}

	@Override
	public boolean persist(Asset entity) {

		boolean rt = false;

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update asset SET assetID = ?, billID = ?, einzelpreis = ?, bezeichnung = ? WHERE assetID = ?;");

			pre.setLong(1, entity.getId());
			pre.setInt(2, entity.getBillID());
			pre.setDouble(3, entity.getEinzelpreis());
			pre.setString(4, entity.getBezeichnung());
			pre.setLong(5, entity.getId());

			if (entity.getId() != 0 && entity.getBillID() != 0
					&& entity.getEinzelpreis() != 0
					&& entity.getBezeichnung() != null) {
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
	public void reload(Asset entity) {

	}

	@Override
	public void detach(Asset entity) {

	}

	@Override
	public void flush() {

	}

}
