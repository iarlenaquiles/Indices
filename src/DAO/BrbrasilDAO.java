package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.ConnectionFactory;

public class BrbrasilDAO {

	private Connection connection;

	public BrbrasilDAO(boolean indice) throws SQLException {

		connection = ConnectionFactory.getConnection();
		if (indice) {
			this.connection.prepareStatement("set enable_bitmapscan=off;").execute();
			this.connection.prepareStatement("set enable_seqscan=off;").execute();
			this.connection.prepareStatement("create index index1 on ocorrenciapessoa using hash (opeid)").execute();
		} else {
			this.connection.prepareStatement("drop index index1").execute();
		}

	}

	public long executar() throws SQLException {
		PreparedStatement smt = this.connection.prepareStatement("");
		smt.setInt(1, 18253321);
		long inicial = System.currentTimeMillis();
		smt.executeQuery();
		long t_final = System.currentTimeMillis();
		return (t_final - inicial);
	}

	public String getPlano() throws SQLException {
		PreparedStatement smt = this.connection
				.prepareStatement("explain analyse select * from ocorrenciapessoa where opeid = ?");
		smt.setInt(1, 18253321);
		ResultSet rs = smt.executeQuery();

		rs.next();
		return rs.getString(1);
	}

	public void setHash() throws SQLException {

		PreparedStatement smt = this.connection
				.prepareStatement("create index index1 on ocorrenciapessoa using hash (opeid)");
		smt.executeQuery();

	}

	public void destroyHash() throws SQLException {
		PreparedStatement smt = this.connection.prepareStatement("drop index index1");
		smt.execute();
	}

	public void setTree() throws SQLException {
		PreparedStatement smt = this.connection
				.prepareStatement("CREATE INDEX indextree ON ocorrenciapessoa USING btree (opeid);");
		smt.executeQuery();
	}

	public void destroyTree() throws SQLException {
		PreparedStatement smt = this.connection.prepareStatement("drop index indextree");
		smt.execute();
	}

}
