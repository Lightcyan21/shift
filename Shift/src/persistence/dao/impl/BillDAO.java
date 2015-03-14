package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Bill;

public class BillDAO extends AbstractDAO<Bill> implements DAO<Bill> {

	@Override
	public Bill create() {
		Bill bill;
		bill = new Bill();   // Daten werden als Objekt geliefert (Entities) und aus ihnen in die 
							// DB übertragen
		
		Connection con;
		con = DBUtil.getConnection();
		
		try {
			PreparedStatement pre;
			pre = con.prepareStatement("insert into bill (rechnungssteller, rechnungsEmpfaenger, betrag, verwendungszweck) values (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			pre.setString(1, bill.getRechnungssteller());		//geht das so?
			pre.setString(2, bill.getRechnungsEmpfaenger());
			pre.setDouble(3, bill.getBetrag());
			pre.setString(4, bill.getVerwendungszweck());
			
			pre.execute();
			
			ResultSet res;
			res = pre.getGeneratedKeys();
			if (res !=  null && res.next()) { 
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

	}

	@Override
	public List<Bill> findAll() {

		return null;
	}

	@Override
	public Bill getById(long id) {

		return null;
	}

	@Override
	public void persist(Bill entity) {

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
