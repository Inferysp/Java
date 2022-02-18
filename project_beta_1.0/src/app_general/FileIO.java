package app_general;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;

import app_jdbc.Kontrahent;
import app_iotxt.VariablesTransformation;
import app_view.Window;

public class FileIO {
	
    public static String filePath;
    static Vector<String> columnNames = Window.returnColumnNames();
	static int columnNumber = Window.returnColumnNames().size();
	static JTable table = Window.dataTable;
	public static String returnFilePath() {
		return filePath;
	}

	//Tworzenie Explorera pobierania pliku
	public static void chooseFile() throws FileNotFoundException {
		JFrame chooserComponent = new JFrame();
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));

		int result = chooser.showDialog(chooserComponent , "Otwórz");
		if(result == JFileChooser.APPROVE_OPTION)
		{
			filePath = chooser.getSelectedFile().getPath();
			File file = new File(filePath);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			String[] tab = scanner.nextLine().split(" ,/. ");
			if(tab.length != columnNumber) {
				Window.runAlertFrame("Nieprawid³owy plik - Wczytaj ponownie!");
			}
		}
	}
	
	//I/O File methods.
	public static void fileToObjectList() throws IOException {

		File file = new File(filePath);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(file);
		int i = 0;
		while (scanner.hasNextLine())
		{
			i++;
	        String[] tab = scanner.nextLine().split(" ,/. ");

	        if(tab.length == columnNumber) {
				Kontrahent kontrahenci = new Kontrahent (
					tab[0], tab[1], tab[2], tab[3],
					tab[4], tab[5], tab[6], tab[7],
					tab[8], tab[9], tab[10], tab[11]
					);
		        VariablesTransformation.list.add(kontrahenci);	                
	        } else if(tab.length != columnNumber) {
	        	Window.runAlertFrame("Nieprawid³owy format wiersza!" + i);
	        }
		}

	}
		
	//Zapisz
	public static void save() throws FileNotFoundException {
		Date dateBeforeJava8 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("_dd.mm.YYYY_HH_mm");
        PrintWriter zapis = new PrintWriter(filePath.substring(0 , filePath.length()-4) + simpleDateFormat.format(dateBeforeJava8) + ".txt");
        saveProcess(zapis);
//        Object[][] outputArray = VariablesTransformation.listToArray();
//	    int rowNumber = outputArray.length;
////	    System.out.println(obj [2][4]);
//		for(int i=0; i<rowNumber; i++) {
//			for(int j=0;j<columnNumber;j++) {
////				System.out.println(outputArray [i][j]);
//				String auxiliaryString = outputArray[i][j].equals("") ? "Empty_Cell ,/. " : outputArray[i][j] + " ,/. ";
////				System.out.println(auxiliaryString);
//				zapis.print(auxiliaryString);
//			}
//			zapis.print("\n");
//		}
//		zapis.close();
	}

	//Zapisz Jako.
	public static void saveFileAs() throws FileNotFoundException {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Specify a file to save");   
		 
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		 
		if (userSelection == JFileChooser.APPROVE_OPTION) {
		    File fileToSave = fileChooser.getSelectedFile();
	        PrintWriter zapis = new PrintWriter(fileToSave);
	        saveProcess(zapis);
//			Object[][] outputArray = VariablesTransformation.listToArray();
//		    int rowNumber = outputArray.length;
////		    System.out.println(obj [2][4]);
//			for(int i=0; i<rowNumber; i++) {
//				for(int j=0;j<columnNumber;j++) {
////					System.out.println(outputArray [i][j]);
//					String auxiliaryString = outputArray[i][j].equals("") ? "Empty_Cell ,/. " : outputArray[i][j] + " ,/. ";
////					System.out.println(auxiliaryString);
//					zapis.print(auxiliaryString);
//				}
//				zapis.print("\n");
//			}
//			zapis.close();
		}
	}
	
//	Metoda pomocnicza zapisu
	public static void saveProcess(PrintWriter zapis) {
		Object[][] outputArray = VariablesTransformation.listToArray();
	    int rowNumber = outputArray.length;
	    SaveAuxiliaryMetchod(zapis, outputArray, rowNumber, columnNumber);
	}
//	Metoda pomocnicza do zapisu.
	public static void SaveAuxiliaryMetchod(PrintWriter var, Object[][] obj, int nr, int colNr ) {
		System.out.println(obj [0][0]);
		for(int i=0; i<nr; i++) {
			for(int j=0;j<colNr;j++) {
				System.out.println(obj [i][j]);
				String auxiliaryString = obj[i][j].equals("") ? "Empty_Cell ,/. " : obj[i][j] + " ,/. ";
				System.out.println(auxiliaryString);
				var.print(auxiliaryString);
			}
			var.print("\n");
		}
		var.close();
	}
	
////umo¿liwienie odczytu wartoœci tabeli po sortowaniu i edycji (Przekazane do metod zapisu)/////////////
//	Zwraca Array[][] tabeli aplikacji 
	public static Object[][] readTableToArray() {
		Object[][] rowsArr;
		int rowsNr = table.getRowCount();
		int colNr = table.getColumnCount();
		rowsArr = new Object[rowsNr][colNr];
		for(int i=0;i<rowsNr;i++) {
			for(int j=0;j<colNr;j++) {
				rowsArr[i][j] = table.getValueAt(i, j);
			}
		}
		return rowsArr;
	}
	
//	Wypisanie danych w konsoli
	public static void wypiszTabelewKonsoli() {
		Object[][] arrayArrayList = readTableToArray();
		System.out.println(arrayArrayList.length);
		for(int i=0; i<arrayArrayList.length; i++) {
			for(int j=0; j<columnNames.size(); j++) {
				System.out.print(arrayArrayList[i][j].equals("")? "Empty_Cell ; " : (arrayArrayList[i][j] + " ; "));
			}
		System.out.println("\n");
		}
	}
}
