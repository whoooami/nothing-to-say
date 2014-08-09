package com.nothing.Lab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nothing.util.DBOpera;

public class TDb {
	
	Connection con;
	static ResultSet rs;
	Statement st;
	public static void main(String[] args){
		new TDb().testDb();
	}
	
	public void testDb(){
		DBOpera db = new DBOpera();
		/*Logger logger = Logger.getLogger(TDb.class.getName());
		logger.debug("------------------------");*/
		rs = db.exeSelect("select * from b");
		try {
			while(rs.next())
				System.out.println(rs.getString("uid"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			logger.debug("------------------------");
			e.printStackTrace();
		}
	}
}
