package app_iotxt;

import java.util.ArrayList;
import app_jdbc.Kontrahent;
import app_view.Window;

public class VariablesTransformation {
	
	public static ArrayList<Kontrahent> list = new ArrayList<>();
	private static int ILOŒÆ_KOLUMN = Window.returnColumnNames().size();
	
	public static ArrayList<Kontrahent> returnList() {
		return list;
	}
	
	public static void deleteInputData() {
		list.removeAll(list);
	}
	
	public static void ShowKontrahent() {
		for (Kontrahent temp: list) temp.dataReturn();
	}
	
	public static void ShowArrayListInConsole() {
		Object[][] arrayArrayList = listToArray();
			for(int i=0; i<arrayArrayList.length; i++) {
				for(int j=0; j<ILOŒÆ_KOLUMN; j++) {
					System.out.print(arrayArrayList[i][j] + " ; ");
				}
			System.out.println("\n");
			}
		}
	
	public static Object[][] listToArray() {
		Object[] temp;
		Object[][] test = new Object[list.size()][ILOŒÆ_KOLUMN];
		for(int i=0; i<list.size(); i++) {
				temp = kontrahenciToArray(i);
			for(int j=0; j<ILOŒÆ_KOLUMN;j++) {
				test[i][j] = temp[j];
			}
		}
		return test;
	}
	
	public static String[] kontrahenciToArray(Integer i) {
	String[] arrayObject = {list.get(i).getFirstName(),     list.get(i).getLastName(),     list.get(i).getSex(),
							list.get(i).getPersonalPhone(), list.get(i).getCompanyPhone(), list.get(i).getEmail(),
							list.get(i).getCompany(),       list.get(i).getNip(),          list.get(i).getCity(),
							list.get(i).getStreet(),        list.get(i).getStreetNumber(), list.get(i).getZipCode()
							};
	return arrayObject;
	}
}