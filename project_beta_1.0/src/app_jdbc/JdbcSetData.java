package app_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSetData {
	
	static final String DB_URL = "jdbc:sqlserver://localhost:51092;databaseName=Nask_3";
    static final String USER = "AdminNask_3";
    static final String PASS = "1234";
    static Connection conn = null;
    static Statement stmt = null;

	public static void updateTable(Kontrahent arg, String randStr) {
	
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	        System.out.println("Connected to SQL Server successfully...");
            stmt = conn.createStatement();
            //Zmienne z wartoœciami z bazy danych;
//            ArrayList<String> nipList = new ArrayList();
            Integer hostValue = null;
            Integer maxHostValue = null;

//			Integer insertID = null;
//
//            //Odczytanie id ostatniego rekordu bazy danych
//            String sqlMaxVal = "DECLARE @MaxHostValue INT;\r\n"
//            		+ "SELECT @MaxHostValue = MAX(host)\r\n"
//            		+ "from Kontrahenci\r\n"
//            		+ "WHERE HostType = 'Kontrahenci' AND Typ = 1;\r\n"
//            		+ "select @MaxHostValue as 'max_host_value';";
//            
//            ResultSet rsp = stmt.executeQuery(sqlMaxVal);
//            while(rsp.next()) {
//            	maxHostValue = rsp.getInt("max_host_value");
//            	insertID = maxHostValue + 1;
//            }
//            rsp.close();
            
            //Odczytanie Id i/lub Host dla danego Nip
            String sqlHostValue = "DECLARE @nipValue nvarchar(13);\r\n"
            		+ "SET @nipValue = '" + arg.getNip() + "';\r\n"
            		+ "select ID 'Id_Dla_Nip' From Kontrahenci WHERE NIP = @nipValue;";
            
            ResultSet res = stmt.executeQuery(sqlHostValue);
            while(res.next()) {
            	hostValue = res.getInt("Id_Dla_Nip");
            }
            res.close();
            
            if (hostValue == null) {
	            //INSERT
	         	String sqlInsert = ""
	         			+ "INSERT INTO Kontrahenci (Nazwa, NIP, Guid, Kod, SposobZaplaty, FormaPrawna) VALUES ('"
	         			+ arg.getCompany() + "', '"
	         			+ arg.getNip()
	         			+ "', NEWID(), '"
	         			+ randStr
	         			+ "', 1, 1);\r\n"
	         			+ "DECLARE @identity INT\r\n"
	         			+ "SET @identity = @@identity\r\n"
	         			+ "INSERT INTO Adresy (AdresTelefon, AdresPoczta, AdresUlica, AdresNrDomu, AdresKodPocztowy, Host, HostType, Typ) VALUES ('"
	         			+ arg.getCompanyPhone() + "', '" 
	         			+ arg.getCity() + "', '"
	         			+ arg.getStreet() + "', '"
	         			+ arg.getStreetNumber() + "', '"
	         			+ arg.getZipCode() + "', @identity"
	         			+ ", 'Kontrahenci', 1);"
	         			+ "SET IDENTITY_INSERT KontaktyOsoby ON;\r\n"
	         			+ "INSERT INTO KontaktyOsoby (ID, Imie, Nazwisko, KontaktTelefonKomorkowy, KontaktEMAIL, Guid) VALUES (@identity, '"
	         			+ arg.getFirstName() + "', '"
	         			+ arg.getLastName() + "', '"
	         			+ arg.getPersonalPhone() + "', '"
	         			+ arg.getEmail()
	         			+ "', NEWID());\r\n"
	         			+ "SET IDENTITY_INSERT KontaktyOsoby OFF;\r\n";
	            System.out.println(sqlInsert);
	            stmt.executeUpdate(sqlInsert);

            } else {
            //UPDATE
            	String sqlUpdate = "UPDATE KontaktyOsoby SET Imie = '"
            			+ arg.getFirstName() + "' where ID = " + hostValue 	+ ";\r\n"
        				+ "UPDATE KontaktyOsoby SET Nazwisko = '"
        				+ arg.getLastName() + "' where ID = " + hostValue 		+ ";\r\n"
        				+ "UPDATE KontaktyOsoby SET KontaktTelefonKomorkowy = '" + arg.getPersonalPhone() 	+ "' where ID = " + hostValue 		+ ";\r\n"
        				+ "UPDATE KontaktyOsoby SET KontaktEMAIL = '"
        				+ arg.getEmail() + "' where ID = " + hostValue + ";\r\n"
        				+ "UPDATE Kontrahenci SET Nazwa = '"
        				+ arg.getCompany() + "' where ID = " + hostValue + ";\r\n"
        				+ "UPDATE Adresy SET AdresTelefon = '"
        				+ arg.getCompanyPhone() + "' where Host = " + hostValue + " AND HostType = 'Kontrahenci' AND Typ = 1;\r\n"
        				+ "UPDATE Adresy SET AdresPoczta = '"
        				+ arg.getCity() + "' where Host = " + hostValue + " AND HostType = 'Kontrahenci' AND Typ = 1;\r\n"
        				+ "UPDATE Adresy SET AdresUlica = '"
        				+ arg.getStreet() + "' where Host = " + hostValue + " AND HostType = 'Kontrahenci' AND Typ = 1;\r\n"
        				+ "UPDATE Adresy SET AdresNrDomu = "
        				+ arg.getStreetNumber() + " where Host = " + hostValue + " AND HostType = 'Kontrahenci' AND Typ = 1;\r\n"
        				+ "UPDATE Adresy SET AdresKodPocztowy = "
        				+ arg.getZipCode() + " where Host = " + hostValue + " AND HostType = 'Kontrahenci' AND Typ = 1;"
        				+ "";
            stmt.executeUpdate(sqlUpdate);
            }
            
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
