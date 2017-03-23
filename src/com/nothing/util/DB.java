package com.nothing.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mysql.jdbc.Connection;
import com.nothing.object.Dbvo;

public class DB {
	
	private Dbvo dbvo;
	
	public DB(){
		dbvo = PropertiesUtil.read("conf/jdbc.properties");
	    DbUtils.loadDriver(dbvo.getDriver()); 
	}
    
    public Connection getConn(){ 
    	Connection conn = null;
	    try {
			conn =  (Connection) DriverManager.getConnection(dbvo.getUrl(), dbvo.getUsername(), dbvo.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
    
    public List exeQuery(String sql, Class clazz){
    	Connection conn = getConn();
    	List l = null;
    	QueryRunner qr = new QueryRunner();
    	try {
			l = (List) qr.query(conn, sql, new BeanListHandler(clazz));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
    	return l;
    }
    
    public List exeQuery(String sql, String[] params, Class clazz){
    	Connection conn = getConn();
    	List l = null;
    	QueryRunner qr = new QueryRunner();
    	try {
			l = (List) qr.query(conn, sql, params, new BeanListHandler(clazz));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
    	return l;
    }
    
    public int operaData(String sql){
    	Connection conn = getConn();
    	int flag = 0;
    	QueryRunner qr = new QueryRunner();
    	try {
			flag = qr.update(conn, sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return flag;
    }
    
    public int operaData(String sql, Object[] param){
    	Connection conn = getConn();
    	int flag = 0;
    	QueryRunner qr = new QueryRunner();
    	try {
			flag = qr.update(conn, sql, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return flag;
    }
    
	public void closeConn(Connection conn){ 
	    DbUtils.closeQuietly(conn);
	}
}
