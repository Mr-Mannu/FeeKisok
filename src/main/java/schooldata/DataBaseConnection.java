package schooldata;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {

//final static private String myurl = "jdbc:mysql://103.93.17.133:3306/";!
	final static private String mydriver ="com.mysql.jdbc.Driver"; // driver
//
//	final static private String myurl = "jdbc:mysql://localhost:3306/"; // path
//	final static private String mydb = "chalkbox";
//	final static private String pass = "root";
//	final static private String user = "root";

	// NEW IP ADDRESS
	final static private String myurl = "jdbc:mysql://103.145.50.182:3306/"; //Public IP
	final static private String mydb = "cbxpro_latest_hr"; //chalkbox_newcb, chalkbox_db,wwweduca_db
	final static private String pass = "jzCW5ySBXE4FN6Xq";
	final static private String user = "root";// new DB -> root	

	

	public static Connection javaConnection()
	{
		Connection conn = null;
		try
		{
			Class.forName(mydriver).newInstance();
			conn = DriverManager.getConnection(myurl + mydb+"?characterEncoding=UTF-8", user,pass);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


		return conn;
	}

}
