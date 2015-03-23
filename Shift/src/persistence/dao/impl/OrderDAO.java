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
import persistence.entity.impl.Order;

public class OrderDAO extends AbstractDAO<Order> implements DAO<Order> {

	@Override
	public Order create() {

		Order order = new Order();
		int key = 0;

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement(
							"insert into job (wohnungsID, jobName, mieter, betrag, status, statusRechnung, statusBestaetigung, statusWeiterleitung, jobID) values (?, ?, ?, ?, ?, ?, ?, ?, ?);",
							Statement.RETURN_GENERATED_KEYS);

			pre.setString(1, null);
			pre.setString(2, null);
			pre.setString(3, null);
			pre.setDouble(4, 0);
			pre.setInt(5, 0);
			pre.setBoolean(6, false);
			pre.setBoolean(7, false);
			pre.setBoolean(8, false);
			pre.setInt(9, 0);

			pre.execute();

			ResultSet rs = pre.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}
			order.setId((long) key);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void delete(Order entity) {

		Connection con;
		con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("delete from job where jobID = ?;");

			pre.setLong(1, entity.getId());

			pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Order> findAll() {

		Connection con;
		con = DBUtil.getConnection();

		List<Order> orderList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from job");

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Order order = new Order();
				order.setId(result.getLong("jobID"));
				order.setWohnungsID(result.getString("wohnungsID"));
				order.setJobName(result.getString("jobName"));
				order.setMieter(result.getString("mieter"));
				order.setBetrag(result.getDouble("betrag"));
				order.setStatus(result.getInt("status"));
				order.setStatusRechnung(result.getBoolean("statusRechnung"));
				order.setStatusBestaetigung(result
						.getBoolean("statusBestaetigung"));
				order.setStatusWeiterleitung(result
						.getBoolean("statusWeiterleitung"));

				orderList.add(order);
			}

			// pre.execute(); //notwendig? oder doppelt?

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public List<Order> getOrderByRequester(String id) {

		Connection con;
		con = DBUtil.getConnection();

		List<Order> orderList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from job where wohnungsID =?;");
			
			pre.setString(1, id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				Order order = new Order();
				order.setId(result.getLong("jobID"));
				order.setWohnungsID(result.getString("wohnungsID"));
				order.setJobName(result.getString("jobName"));
				order.setMieter(result.getString("mieter"));
				order.setBetrag(result.getDouble("betrag"));
				order.setStatus(result.getInt("status"));
				order.setStatusRechnung(result.getBoolean("statusRechnung"));
				order.setStatusBestaetigung(result
						.getBoolean("statusBestaetigung"));
				order.setStatusWeiterleitung(result
						.getBoolean("statusWeiterleitung"));

				orderList.add(order);
			}

			// pre.execute(); //notwendig? oder doppelt?

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	@Override
	public Order getById(long id) {

		Connection con;
		con = DBUtil.getConnection();

		ArrayList<Order> orderList = new ArrayList<>();
		Order order = new Order();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from job where jobID =?;");

			pre.setDouble(1, (int) id);

			ResultSet result = pre.executeQuery();

			while (result.next()) {

				order.setId(result.getLong("jobID"));
				order.setWohnungsID(result.getString("wohnungsID"));
				order.setJobName(result.getString("jobName"));
				order.setMieter(result.getString("mieter"));
				order.setBetrag(result.getDouble("betrag"));
				order.setStatus(result.getInt("status"));
				order.setStatusRechnung(result.getBoolean("statusRechnung"));
				order.setStatusBestaetigung(result
						.getBoolean("statusBestaetigung"));
				order.setStatusWeiterleitung(result
						.getBoolean("statusWeiterleitung"));

				orderList.add(order);

			}

			// pre.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return order;
	}

	@Override
	public void persist(Order entity) {

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update job SET jobID = ?, wohnungsID = ?, jobName = ?, mieter = ?, betrag = ?, status = ?, statusRechnung = ?, statusBestaetigung = ?, statusWeiterleitung = ? WHERE jobID = "
							+ entity.getId());
			pre.setLong(1, entity.getId());
			pre.setString(2, entity.getWohnungsID());
			pre.setString(3, entity.getJobName());
			pre.setString(4, entity.getMieter());
			pre.setDouble(5, entity.getBetrag());
			pre.setInt(6, entity.getStatus());
			pre.setBoolean(7, entity.isStatusRechnung());
			pre.setBoolean(8, entity.isStatusBestaetigung());
			pre.setBoolean(9, entity.isStatusWeiterleitung());

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
	public void reload(Order entity) {

	}

	@Override
	public void detach(Order entity) {

	}

	@Override
	public void flush() {

	}

}
