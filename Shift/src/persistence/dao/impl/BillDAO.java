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
import persistence.entity.impl.Bill;

import com.mysql.jdbc.Statement;

public class BillDAO extends AbstractDAO<Bill> implements DAO<Bill> {

	@Override
	public Bill create() {

		Bill bill = new Bill();
		int key = 0;

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement(
							"insert into bill (rechnungssteller, rechnungsEmpfaenger, gesamtbetrag, verwendungszweck, rechnungsdatum, zahlungsdatum) values (?, ?, ?, ?, ?, ?);",
							Statement.RETURN_GENERATED_KEYS);

			pre.setString(1, null);
			pre.setString(2, null);
			pre.setDouble(3, 0);
			pre.setString(4, null);
			pre.setString(5, null);
			pre.setString(6, null);

			pre.execute();

			ResultSet rs = pre.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}

			bill.setId((long) key);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bill;
	}

	@Override
	public void delete(Bill entity) {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("delete from bill where billID = ?;");

			pre.setLong(1, entity.getId());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Bill> findAll() {

		Connection con;
		con = DBUtil.getConnection();

		List<Bill> billList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from bill");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Bill bill = new Bill();
				bill.setId(result.getLong("billID"));
				bill.setRechnungssteller(result.getString("rechnungssteller"));
				bill.setRechnungsEmpfaenger(result
						.getString("rechnungsempfaenger"));
				bill.setBetrag(result.getDouble("gesamtbetrag"));
				bill.setRechnungsdatum(result.getString("rechnungsdatum"));
				bill.setZahlungsdatum(result.getString("zahlungsdatum"));
				bill.setVerwendungszweck(result.getString("verwendungszweck"));

				billList.add(bill);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (billList.size() != 0) {
			return billList;
		} else {
			return null;
		}

	}

	@Override
	public Bill getById(long id) {

		Connection con;
		con = DBUtil.getConnection();

		Bill bill = new Bill();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from bill where billID =?;");

			pre.setDouble(1, (int) id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				bill.setId(result.getLong("billID"));
				bill.setRechnungssteller(result.getString("rechnungssteller"));
				bill.setRechnungsEmpfaenger(result
						.getString("rechnungsempfaenger"));
				bill.setBetrag(result.getDouble("gesamtbetrag"));
				bill.setVerwendungszweck(result.getString("verwendungszweck"));
				bill.setRechnungsdatum(result.getString("rechnungsdatum"));
				bill.setZahlungsdatum(result.getString("zahlungsdatum"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (bill.getId() != 0) {
			return bill;
		} else {
			return null;
		}
	}

	public Bill getByVerwendungszweck(String vz) {
		Connection con;
		con = DBUtil.getConnection();

		Bill bill = new Bill();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("select * from bill where verwendungszweck =?;");

			pre.setString(1, vz);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				bill.setId(result.getLong("billID"));
				bill.setRechnungssteller(result.getString("rechnungssteller"));
				bill.setRechnungsEmpfaenger(result
						.getString("rechnungsempfaenger"));
				bill.setBetrag(result.getDouble("gesamtbetrag"));
				bill.setVerwendungszweck(result.getString("verwendungszweck"));
				bill.setRechnungsdatum(result.getString("rechnungsdatum"));
				bill.setZahlungsdatum(result.getString("zahlungsdatum"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (bill.getVerwendungszweck() != null) {
			return bill;
		} else {
			return null;
		}
	}

	@Override
	public boolean persist(Bill entity) {

		boolean rt = false;

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update bill SET rechnungssteller = ?, rechnungsempfaenger = ?, gesamtbetrag = ?, verwendungszweck = ?, rechnungsdatum = ?, zahlungsdatum = ? WHERE billID = ?;");
			pre.setString(1, entity.getRechnungssteller());
			pre.setString(2, entity.getRechnungsEmpfaenger());
			pre.setDouble(3, entity.getBetrag());
			pre.setString(4, entity.getVerwendungszweck());
			pre.setString(5, entity.getRechnungsdatum());
			pre.setString(6, entity.getZahlungsdatum());
			pre.setLong(7, entity.getId());

			if (
			// entity.getId() != 0 && entity.getRechnungssteller() != null
			// && entity.getRechnungsEmpfaenger() != null
			// && entity.getBetrag() != 0 &&
			entity.getVerwendungszweck() != null) {
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
	public void reload(Bill entity) {

	}

	@Override
	public void detach(Bill entity) {

	}

	@Override
	public void flush() {

	}

}
