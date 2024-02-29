package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Crypto;

public class CryptoDao extends DAO {
	public CryptoDao() {
		super();
		connectToDatabase();
	}

	public void dispose() {
		close();
	}

	public boolean insert(Crypto crypto) {
	    boolean status = false;

	    try {
	        Statement st = connection.createStatement();
	        String sql = "INSERT INTO crypto (id, rede, symbol, price) VALUES (" + crypto.getId() + ", '" +
	                crypto.getRede() + "', '" + crypto.getSymbol() + "', " + crypto.getPrice() + ");";

	        System.out.println(sql);
	        st.executeUpdate(sql);
	        st.close();

	        status = true;

	    } catch (SQLException u) {
	        throw new RuntimeException(u);
	    }

	    return status;
	}

	public Crypto getById(int id) {
		Crypto crypto = null;

		try {

			Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM crypto WHERE id = " + id;

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				crypto = new Crypto(rs.getInt("id"), rs.getString("rede"), rs.getString("symbol"),
						rs.getDouble("price"));
			}
			st.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return crypto;
	}

	public List<Crypto> getAllCrypto() {
	    List<Crypto> cryptoList = new ArrayList<>();

	    try {
	        Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        String sql = "SELECT * FROM crypto ORDER BY id ASC";

	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);

	        while (rs.next()) {
	            Crypto crypto = new Crypto(rs.getInt("id"), rs.getString("rede"), rs.getString("symbol"),
	                    rs.getDouble("price"));
	            cryptoList.add(crypto);
	        }
	        st.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return cryptoList;
	}

	public boolean update(Crypto crypto) {
	    boolean status = false;
	    try {
	        Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        String sql = "UPDATE crypto SET rede = '" + crypto.getRede() + "', symbol = '" + crypto.getSymbol() + "', price = " 
	        + crypto.getPrice() + " WHERE id = " + crypto.getId();

	        System.out.println(sql);
	        st.executeUpdate(sql);

	        st.close();
	        status = true;

	    } catch (SQLException u) {
	        throw new RuntimeException(u);
	    }
	    return status;
	}

	public boolean delete(int id) {
	    boolean status = false;
	    try {  
	        Statement st = connection.createStatement();
	        String sql = "DELETE FROM crypto WHERE id = " + id;
	        System.out.println(sql);
	        st.executeUpdate(sql);
	        st.close();
	        status = true;
	    } catch (SQLException u) {  
	        throw new RuntimeException(u);
	    }
	    return status;
	}

}
