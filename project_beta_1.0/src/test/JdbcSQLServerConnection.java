package test;



import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This program demonstrates how to establish database connection to Microsoft
 * SQL Server.
 * @author www.codejava.net
 *
 */
public class JdbcSQLServerConnection {

	static final String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Nask_3";
	
	public static void main(String[] args) {
		getDbConnection();
	}
		
	
	public static void getDbConnection() {
		String usr = "admin_Nask_3";
		String psw = "admin";
		Connection conn = null;
		java.sql.Statement stmt = null;
		
		try {
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(dbURL,usr, psw); 
	        System.out.println("Connected database successfully...");

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql = "SELECT id, Nazwa, NIP FROM Kontrahenci";
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				   //Retrieve by column name
				int id  = rs.getInt("ID");
				String nazwa = rs.getString("Nazwa");
				String nip = rs.getString("NIP");
				//Display values
				System.out.println("ID: " + id);
				System.out.println("Nazwa: " + nazwa);
				System.out.println("NIP: " + nip);
				System.out.print("\n");
			}
			rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		}//end method
	}//end class