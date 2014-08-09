package com.nothing.Lab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.nothing.object.Users;
  
public class DbUtilsUseBeanMySQL {   
  public static void main(String[] args) {   
    Connection conn = null;   
    
    String url = "jdbc:mysql://localhost:3306/nothing";
	String driver = "com.mysql.jdbc.Driver";
	String user = "root";
	String password = "lincoln";
  
    try {   
      DbUtils.loadDriver(driver);   
      conn = DriverManager.getConnection(url, user, password);   
  
      QueryRunner qRunner = new QueryRunner();   
      List beans = (List) qRunner.query(conn, "select uID, uNickName from users",   
          new BeanListHandler(Users.class));
      System.out.println(beans.size());
      for (int i = 0; i < beans.size(); i++) {   
        Users bean = (Users) beans.get(i);   
        System.out.println(bean.getuNickName());
      }   
    } catch (SQLException e) {   
      // handle the exception   
      e.printStackTrace();   
    } finally {   
      DbUtils.closeQuietly(conn);   
    }   
  }   
} 