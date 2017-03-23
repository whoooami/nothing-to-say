package com.nothing.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

public class DBOpera {

	Connection con;
	Statement st;
	ResultSet rs;
	
	/**
	 * @param args
	 */
	public DBOpera(){
		/**Just for test sqlserver*/
//		new com.microsoft.sqlserver.jdbc.SQLServerDriver();
		String sqlurl="jdbc:sqlserver://localhost:1433;datebaseName=nothing";
		try {
			con=DriverManager.getConnection(sqlurl,"sa","123456");
			st=con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet exeSelect(String str){
		try {
			rs=st.executeQuery(str);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void exeUpdate(String sql){
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closealllink(){
		try {
			if(st != null)
				st.close();
			if(rs != null)
				rs.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
