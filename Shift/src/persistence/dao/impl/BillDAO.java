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
import persistence.entity.impl.Bill;

public class BillDAO extends AbstractDAO<Bill> implements DAO<Bill> {

	@Override
	public Bill create() {
		Bill bill;
		bill = new Bill(); // Daten werden als Objekt geliefert (Entities) und
							// aus ihnen in die
							// DB übertragen

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement(
							"insert into bill (rechnungssteller, rechnungsEmpfaenger, betrag, verwendungszweck) values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

			pre.setString(1, null);
			pre.setString(2, null);
			pre.setDouble(3, 0);
			pre.setString(4, null);

			pre.execute();

			ResultSet res;
			res = pre.getGeneratedKeys();
			if (res != null && res.next()) {
				bill.setBillID(res.getString(res.getString(1)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void delete(Bill entity) {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("delete from bill where billID values (?);");

			pre.setString(1, entity.getBillID());

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
				bill.setBillID(result.getString("billID"));
				bill.setRechnungssteller(result.getString("rechnungssteller"));
				bill.setRechnungsEmpfaenger(result.getString("rechnungsempfaenger")); 
				bill.setBetrag(result.getDouble("gesamtbetrag"));

				billList.add(bill);
			}

			// pre.execute(); //notwendig? oder doppelt?

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return billList;
	}

	@Override
	public Bill getById(long id) { //long oder int? (in DB ist int, in Konzept String, in Methode double...)
		
		Connection con;
		con = DBUtil.getConnection();
		
		Bill bill = new Bill();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("select * from bill where billID values (?);");

			pre.setDouble(1, id);

			ResultSet result = pre.executeQuery();
			
			bill.setBillID(result.getString("billID"));
			bill.setRechnungssteller(result.getString("rechnungssteller"));
			bill.setRechnungsEmpfaenger(result.getString("rechnungsempfaenger")); 
			bill.setBetrag(result.getDouble("gesamtbetrag"));
			bill.setVerwendungszweck(result.getString("verwendungszweck"));

//			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return bill;
	}

	@Override
	public void persist(Bill entity) {
		
		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update bill SET billID = ?, rechnungssteller = ?, rechnungsempfaenger = ?, gesamtbetrag = ?, verwendungszweck = ? WHERE billID = "
							+ entity.getBillID());
			pre.setString(1, entity.getBillID());
			pre.setString(2, entity.getRechnungssteller());
			pre.setString(3, entity.getRechnungssteller());
			pre.setDouble(4, entity.getBetrag());
			pre.setString(5, entity.getVerwendungszweck());

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
	public void reload(Bill entity) {

	}

	@Override
	public void detach(Bill entity) {

	}

	@Override
	public void flush() {

	}

}
