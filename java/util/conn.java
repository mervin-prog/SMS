package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conn 
{
	public static Connection Connect() throws ClassNotFoundException{	
		Connection con = null;
		try 
		{
	        Class.forName("com.mysql.cj.jdbc.Driver"); //optional
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","josephmervin5522#");
	        if(con!=null) {
	        	System.out.print("connected");
	        }
	    } 
		catch (SQLException e) {
	        e.printStackTrace();
	    }
		return con;
	}
}
