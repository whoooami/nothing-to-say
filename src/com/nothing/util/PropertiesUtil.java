package com.nothing.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.nothing.object.Dbvo;

public class PropertiesUtil {

	public static Dbvo read(String name){
		Properties prop = new Properties();
		Dbvo db = new Dbvo();
		try {
			InputStream is = ClassLoader.getSystemResourceAsStream(name);
			prop.load(is);
			is.close();
			db.setDriver(prop.getProperty("jdbc.driverClassName"));
			db.setUrl(prop.getProperty("jdbc.url"));
			db.setUsername(prop.getProperty("jdbc.username"));
			db.setPassword(prop.getProperty("jdbc.password"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}
}
