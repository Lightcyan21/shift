package persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import persistence.DBUtil;
import persistence.dao.AbstractDAO;
import persistence.dao.DAO;
import persistence.entity.impl.Order;

import com.mysql.jdbc.Statement;

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
							"insert into job (wohnungsID, jobName, mieter, betrag, status, statusRechnung, statusBestaetigung, statusWeiterleitung, datum) values (?, ?, ?, ?, ?, ?, ?, ?,?);",
							Statement.RETURN_GENERATED_KEYS);

			pre.setString(1, null);
			pre.setString(2, null);
			pre.setString(3, null);
			pre.setDouble(4, 0);
			pre.setInt(5, 0);
			pre.setBoolean(6, false);
			pre.setBoolean(7, false);
			pre.setBoolean(8, false);
			pre.setString(9, null);

			pre.execute();

			ResultSet rs = pre.getGeneratedKeys();
			if (rs != null && rs.next()) {
				key = rs.getInt(1);
			}
			order.setId((long) key);

		} catch (SQLException e) {
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
				order.setSeen(result.getBoolean("seen"));
				order.setDatum(result.getString("datum"));

				orderList.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (orderList.size() != 0) {
			return orderList;
		} else {
			return null;
		}
	}

	public HashMap<String, List<Order>> getOrderByRequester(List<String> list) {

		Connection con;
		con = DBUtil.getConnection();

		HashMap<String, List<Order>> hm = new HashMap<>();
		List<Order> orderList = new ArrayList<Order>();
		{
		}
		;

		for (String string : list) {
			try {
				PreparedStatement pre;
				pre = con
						.prepareStatement("select * from job where wohnungsID =?;");

				pre.setString(1, string);

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
					order.setSeen(result.getBoolean("seen"));
					order.setDatum(result.getString("datum"));

					orderList.add(order);

					hm.put(string, orderList);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (hm.size() != 0) {
			return hm;
		} else {
			return null;
		}

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
				order.setSeen(result.getBoolean("seen"));
				order.setDatum(result.getString("datum"));

				orderList.add(order);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (order.getId() != 0) {
			return order;
		} else {
			return null;
		}
	}

	public List<Order> getIfStatusNotSeen() {

		Connection con;
		con = DBUtil.getConnection();

		List<Order> orderList = new ArrayList<>();

		try {
			PreparedStatement pre;
			pre = con.prepareStatement("select * from job where seen = false");

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
				order.setSeen(result.getBoolean("seen"));
				order.setDatum(result.getString("datum"));

				orderList.add(order);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (orderList.size() != 0) {
			return orderList;
		} else {
			return null;
		}
	}

	@Override
	public boolean persist(Order entity) {

		boolean rt = false;

		Connection con = DBUtil.getConnection();

		try {
			PreparedStatement pre;
			pre = con
					.prepareStatement("update job SET wohnungsID = ?, jobName = ?, mieter = ?, betrag = ?, status = ?, statusRechnung = ?, statusBestaetigung = ?, statusWeiterleitung = ?, seen = ? , datum = ? WHERE jobID = ?;");
			pre.setString(1, entity.getWohnungsID());
			pre.setString(2, entity.getJobName());
			pre.setString(3, entity.getMieter());
			pre.setDouble(4, entity.getBetrag());
			pre.setInt(5, entity.getStatus());
			pre.setBoolean(6, entity.isStatusRechnung());
			pre.setBoolean(7, entity.isStatusBestaetigung());
			pre.setBoolean(8, entity.isStatusWeiterleitung());
			pre.setBoolean(9, entity.isSeen());
			pre.setString(10, entity.getDatum());
			pre.setLong(11, entity.getId());

			if (entity.getId() != 0 && entity.getWohnungsID() != null
					&& entity.getJobName() != null
					&& entity.getMieter() != null
			// && entity.getStatus() != 0
			) {
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

	public boolean persistAll(List<List<Order>> list) {

		Connection con = DBUtil.getConnection();
		for (List<Order> list1 : list) {
			for (Order entity : list1) {
				try {
					PreparedStatement pre;
					pre = con
							.prepareStatement("update job SET wohnungsID = ?, jobName = ?, mieter = ?, betrag = ?, status = ?, statusRechnung = ?, statusBestaetigung = ?, statusWeiterleitung = ?, seen = ? , datum = ? WHERE jobID = ?;");
					pre.setString(1, entity.getWohnungsID());
					pre.setString(2, entity.getJobName());
					pre.setString(3, entity.getMieter());
					pre.setDouble(4, entity.getBetrag());
					pre.setInt(5, entity.getStatus());
					pre.setBoolean(6, entity.isStatusRechnung());
					pre.setBoolean(7, entity.isStatusBestaetigung());
					pre.setBoolean(8, entity.isStatusWeiterleitung());
					pre.setBoolean(9, entity.isSeen());
					pre.setString(10, entity.getDatum());
					pre.setLong(11, entity.getId());

					if (entity.getId() != 0 && entity.getWohnungsID() != null
							&& entity.getJobName() != null
							&& entity.getMieter() != null
					// && entity.getStatus() != 0
					) {
						pre.executeUpdate();

					} else {
						System.out
								.println("Unvollständisges Objekt, persist nicht ausgeführt.");
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

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
