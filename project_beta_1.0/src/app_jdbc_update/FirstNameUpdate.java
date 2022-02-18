package app_jdbc_update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstNameUpdate extends AbstractUpdateCell {
	
	static final String DB_URL = "jdbc:sqlserver://localhost:51092;databaseName=Nask_3";
    static final String USER = "AdminNask_3";
    static final String PASS = "1234";
    static Connection conn = null;
    static Statement stmt = null;

	public void updateCell(String arg, int value) {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        System.out.println("Connected to SQL Server successfully...");
            stmt = conn.createStatement();
            //Update
            String sql = "UPDATE KontaktyOsoby SET KontaktEmail = '"
	            		+ arg + "' WHERE ID = "
	            		+ value + ";";
            ResultSet rs = stmt.executeQuery(sql);
            rs.close();
		} catch(SQLException se) {
		    //Handle errors for JDBC
		    se.printStackTrace();
		} catch(Exception e) {
		    //Handle errors for Class.forName
		    e.printStackTrace();
		} finally {
		   //finally block used to close resources
			try {
			   if(stmt!=null) conn.close();
			} catch(SQLException se) {
			}// do nothing
			try{
				if(conn!=null) conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}//end finally try
		}//end try
		 System.out.println("Goodbye!");
	}//end main
}//end JDBCExample
