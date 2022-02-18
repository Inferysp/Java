package app_general;

import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import app_iotxt.VariablesTransformation;
import app_jdbc.JdbcGetData;
import app_jdbc.JdbcSetData;
import app_jdbc.Kontrahent;
import app_valid.AbstractValidationClass;
import app_valid.NipValidator;
import app_view.FindByWindow;
import app_view.ImageIconButtonEx;
import app_view.Window;

@SuppressWarnings({ "serial", "unused" })
public class Buttons extends JPanel implements ActionListener{
	
	private JButton sqlDataBtn;
	private JButton buttonShowTable;
	private JButton buttonExplorer;
	private JButton buttonClearData;
	private JButton saveAsBtn;
	private JButton saveToNewFile;
	private JButton findBtn;
	private JButton buttonAddAllToDB;
	private JButton test;
	private JButton buttonAddNewRecord;
	private boolean clearTableAuxVar = false;
	private boolean buttonExplorerAuxiliary = false;
	private boolean sqlDataBtnAuxiliary = false;
	
	public Buttons() {
		
		sqlDataBtn = new JGradientButton("GET MSSQL DB");
		buttonExplorer = new JButton();
//		buttonClearData = new JButton();
		saveToNewFile = new JButton();
		saveAsBtn = new JButton();
		findBtn = new JButton();
		test = new JButton();
		buttonAddAllToDB = new JButton();
		buttonAddNewRecord = new JButton();
		
//		sqlDataBtn.setText("GET MSSQL DB"); //Not compatible with JGradientButton
		buttonExplorer.setText("GET FROM FILE");
//		buttonClearData.setText("Clear Table!");
		saveToNewFile.setText("Save");
		saveAsBtn.setText("Save As...");
		findBtn.setText("Find record by NIP");
		test.setText("Test");
		buttonAddAllToDB.setText("Add All Data Do DB!!!");
		buttonAddNewRecord.setText("Add new Record!!!");
		
		add(sqlDataBtn);
		add(buttonExplorer);
		add(saveToNewFile);
		add(saveAsBtn);
		add(buttonAddAllToDB);
		add(findBtn);
		add(buttonAddNewRecord);
//		add(buttonClearData);
		add(test);
		
		sqlDataBtn.addActionListener(this);
		buttonExplorer.addActionListener(this);
//		buttonShowTable.addActionListener(this);
//		buttonClearData.addActionListener(this);
		saveToNewFile.addActionListener(this);
		saveAsBtn.addActionListener(this);
		findBtn.addActionListener(this);
		test.addActionListener(this);
		buttonAddAllToDB.addActionListener(this);
		buttonAddNewRecord.addActionListener(this);
	}
	
	public void przywrocNasluchiwanie() {
		if(buttonExplorerAuxiliary == true) {
			buttonExplorer.addActionListener(this);
		} else if(sqlDataBtnAuxiliary == true) {
			sqlDataBtn.addActionListener(this);
		}
	}

	@SuppressWarnings("null")
	public void actionPerformed(ActionEvent a) {
		if(a.getSource() == sqlDataBtn) {
			//Usuniêcie danych
			VariablesTransformation.deleteInputData();
			Window.createEmptyTable(true);
			
			JdbcGetData.jdbcGetData(); //Zwraca listê obiektów z bazy danych sql server
			Window.btnDatabaseTableAction();
			sqlDataBtnAuxiliary = false;
			buttonExplorerAuxiliary = true;
			
		} else if(a.getSource() == buttonExplorer) {
			//Usuniêcie danychs
			VariablesTransformation.deleteInputData();
			Window.createEmptyTable(true);
			
			try {
				FileIO.chooseFile(); //Zwraca listê obiektów z pliku txt
				FileIO.fileToObjectList();
				Window.btnDatabaseTableAction();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buttonExplorerAuxiliary = false;
			sqlDataBtnAuxiliary = true;
			przywrocNasluchiwanie();
				
//		} else if(a.getSource() == buttonClearData) {
//			//Usuniêcie danych
//			VariablesTransformation.deleteInputData();
//			Window.createEmptyTable(true);
//
//			if(buttonExplorerAuxiliary == true){
//				przywrocNasluchiwanie();
//			} else {
//				przywrocNasluchiwanie();
//			}
//			
//			if(sqlDataBtnAuxiliary == true){
//				przywrocNasluchiwanie();
//			} else {
//				przywrocNasluchiwanie();
//			}
			
		} else if(a.getSource() == saveToNewFile) {
			 try {
				FileIO.save();
				Window.runAlertFrame("Save with time success!");
			} catch (FileNotFoundException e) { e.printStackTrace(); }
			 
		} else if(a.getSource() == saveAsBtn) {
			try {
				FileIO.saveFileAs();
				Window.runAlertFrame("Save As... success!");
			} catch (FileNotFoundException e) {
				e.printStackTrace(); }
			 
		}  else if(a.getSource() == buttonAddAllToDB) {
			int i = 0;
			boolean aux = true;
			AbstractValidationClass validacja =  new NipValidator();
			ArrayList<Kontrahent> array = VariablesTransformation.returnList();
			
			while(i < array.size() ) {
				if(validacja.validate(array.get(i).getNip()) == false
						|| (array.get(i).getNip().equals("") == true)) {
					aux = false;
					break;
				}
				i++;
			}
			
			if(aux == true) {
				for(int j=0;j<array.size();j++) {
					String kod = kodReturn();
					JdbcSetData.updateTable(array.get(j), kod);
				}
			} else {
				Window.runAlertFrame("Insert correct nip number!");
			}
			
		} else if(a.getSource() == findBtn) {
			FindByWindow.findByWindow();
//			ImageIconButtonEx.main(null);
			
		} else if(a.getSource() == test) {
//			System.out.println("Wartoœci tabeli.");
//			FileIO.wypiszTabelewKonsoli();
			System.out.println("\n" + "Wartoœci array'a obiektów.");
			VariablesTransformation.ShowKontrahent();
//			System.out.println("\n" + "Wartoœci listy obiektów.");
//			VariablesTransformation.ShowArrayListInConsole();
//			Drukuje do pdf
//			System.out.println("\n" + "Printed Table.");
//			try {
//				Window.printTable();
//			} catch (PrinterException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
	
	public String kodReturn() {
		String tab[] = new String[5];
//		String temp = null;

		int index = 0;
		Random random = new Random();
		String[] letters = {"A", "G", "C", "T", "Z", "Y", "O"};
		for(int n=0;n<5;n++) {
	        index = random.nextInt(letters.length);
	        tab[n] = letters[index];
		}
		String kod = tab[0].concat(tab[1]).concat(tab[2]).concat(tab[3]).concat(tab[4]);
		return kod;
	}
}
