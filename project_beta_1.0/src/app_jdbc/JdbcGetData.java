package app_jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import app_iotxt.VariablesTransformation;
import app_view.Window;


public class JdbcGetData {
	
//	private static ArrayList<KontrahentMSSQL> listMSSQL = new ArrayList<>();
	static Vector<String> columnNames = Window.returnColumnNames();
     // JDBC driver name and database URL
	static final String DB_URL = "jdbc:sqlserver://localhost:51092;databaseName=Nask_3";
    // Database credentials
    static final String USER = "AdminNask_3";
    static final String PASS = "1234";
    static Connection conn = null;
    static Statement stmt = null;
//    static Vector<Vector<String>> data = new Vector<Vector<String>>();

    public static void jdbcGetData() {

         columnNames = new Vector<String>();
         columnNames.add("First_name");
         columnNames.add("Last_name");
         columnNames.add("Sex");
         columnNames.add("Personal_phone_number");//nr komórkowy
         columnNames.add("Company_phone_number");//nr stacjonarny
         columnNames.add("Email");
         columnNames.add("Company");
         columnNames.add("NIP");
         columnNames.add("City");
         columnNames.add("Street");
         columnNames.add("Address_Number");
         columnNames.add("ZIP_code");


      String query = "SELECT C.Imie as 'First_name',\r\n"
      		+ "       C.Nazwisko as 'Last_name',\r\n"
      		+ "       C.KontaktTelefonKomorkowy as 'Personal_phone_number',\r\n"
      		+ "       B.AdresTelefon as 'Company_phone_number',\r\n"
      		+ "       C.KontaktEMAIL as 'Email',\r\n"
      		+ "       A.Nazwa as 'Company',\r\n"
      		+ "       A.NIP as 'NIP',\r\n"
      		+ "       B.AdresPoczta AS 'City',\r\n"
      		+ "       B.AdresUlica AS 'Street',\r\n"
      		+ "       B.AdresNrDomu AS 'Address_Number',\r\n"
      		+ "       B.AdresKodPocztowy as 'ZIP_code'\r\n"
      		+ "FROM dbo.Kontrahenci A\r\n"
      		+ "Join Adresy B ON A.ID = B.Host\r\n"
      		+ "Join KontaktyOsoby C ON C.ID = A.ID\r\n"
      		+ "WHERE B.HostType = 'Kontrahenci' AND B.Typ = 1";


        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	
	        	Kontrahent kontrahenciMSSQL = new Kontrahent(rs.getString("First_name"),
														               rs.getString("Last_name"),
														               "male",
														               rs.getString("Personal_phone_number"),
														               rs.getString("Company_phone_number"),
														               rs.getString("Email"),
														               rs.getString("Company"),
														               rs.getString("NIP"),
														               rs.getString("City"),
														               rs.getString("Street"),
														               rs.getString("Address_Number"),
														               rs.getString("ZIP_code")
														                );
		        VariablesTransformation.list.add(kontrahenciMSSQL);	
	        }
	        
	        System.out.println("Connected to SQL Server");
	        rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
   		         ex.printStackTrace();
//                    Logger.getLogger(FlashBuilderGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /////////////////////////////////////Input-->||METODY POŒREDNIE||-->Output//////////////////////////////////////////////
	public static Vector<String> kontrahentMSSQLToArray(Integer i) {
	Vector<String> KontrahentSqlVector = new Vector<>();
	
	KontrahentSqlVector.add(VariablesTransformation.list.get(i).getFirstName());
		KontrahentSqlVector.add(VariablesTransformation.list.get(i).getLastName());
			KontrahentSqlVector.add(VariablesTransformation.list.get(i).getSex());
				KontrahentSqlVector.add(VariablesTransformation.list.get(i).getPersonalPhone());
					KontrahentSqlVector.add(VariablesTransformation.list.get(i).getCompanyPhone());
						KontrahentSqlVector.add(VariablesTransformation.list.get(i).getEmail());
							KontrahentSqlVector.add(VariablesTransformation.list.get(i).getCompany());
								KontrahentSqlVector.add(VariablesTransformation.list.get(i).getNip());
									KontrahentSqlVector.add(VariablesTransformation.list.get(i).getCity());
										KontrahentSqlVector.add(VariablesTransformation.list.get(i).getStreet());
											KontrahentSqlVector.add(VariablesTransformation.list.get(i).getStreetNumber());
												KontrahentSqlVector.add(VariablesTransformation.list.get(i).getZipCode());
	return KontrahentSqlVector;
	}
    
	public static Vector<Vector<String>> getDataObject() {
    	Vector<String> temp;
    	Vector<Vector<String>> vectorSQL = new Vector<Vector<String>>();
		for(int i=0; i<VariablesTransformation.list.size(); i++) {
				temp = kontrahentMSSQLToArray(i);
				vectorSQL.addElement(temp);
		}
		return vectorSQL;
    }
    

}