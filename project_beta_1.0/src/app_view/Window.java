package app_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import app_general.Buttons;
import app_general.FileIO;
import app_iotxt.DataTableModel;
import app_iotxt.VariablesTransformation;
import app_valid.AbstractValidationClass;
import app_valid.CityValidator;
import app_valid.CompanyNameValidation;
import app_valid.CompanyPhoneValidation;
import app_valid.EmailValidator;
import app_valid.FirstNameValidator;
import app_valid.LastNameValidator;
import app_valid.NipValidator;
import app_valid.PersonalPhoneValidation;
import app_valid.SexValidator;
import app_valid.StreetNumberValidator;
import app_valid.StreetValidator;
import app_valid.ZIPCodeValidator;

@SuppressWarnings("serial")

public class Window extends JFrame {
	
    public static ArrayList<AbstractValidationClass> validationVector = new ArrayList<>();
    static Vector<String> columnNames = returnColumnNames();
	private static JPanel tablePanel = new JPanel();
	private static JScrollPane scrollPane;
	public static JTable dataTable;

	public static JTable returnDataTable() {
		return dataTable;
	}
	
///////////////////////////////////OKNO APLIKACJI///////////////////////////////////
//	@SuppressWarnings("static-access")
	public Window() {
		buildValidationVector();
		Buttons buttons = new Buttons();
		add(buttons, BorderLayout.PAGE_START);

		JPanel mainPanel = new JPanel();

		tablePanel.setBorder(BorderFactory.createTitledBorder(
			     BorderFactory.createEtchedBorder(), "My Demo Table", TitledBorder.LEFT,
			     TitledBorder.TOP));
		
		createEmptyTable(false);
		
		tablePanel.add(scrollPane);
		tablePanel.setLayout(new GridLayout(1, 1));
		mainPanel.setLayout(new GridLayout(1, 1));
		
//		mainPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.blue), BorderFactory.createEtchedBorder())); //dodanie ramki tabeli
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.add(tablePanel);
		add(mainPanel);
		//Parametry okna aplikacji
		setTitle("Data processing app.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pack();
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void buildValidationVector() {
		validationVector.add(new FirstNameValidator());
		validationVector.add(new LastNameValidator());
		validationVector.add(new SexValidator());
		validationVector.add(new PersonalPhoneValidation());
		validationVector.add(new CompanyPhoneValidation());
		validationVector.add(new EmailValidator());
		validationVector.add(new CompanyNameValidation());
		validationVector.add(new NipValidator());
		validationVector.add(new CityValidator());
		validationVector.add(new StreetValidator());
		validationVector.add(new StreetNumberValidator());
		validationVector.add(new ZIPCodeValidator());
	}
	
    public static Vector<String> returnColumnNames() {
    	Vector<String> columnNames = new Vector<String>();
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
    	return columnNames;
    }
	
///////////////////////////////////OKNO ALERTU///////////////////////////////////
	public static void runAlertFrame(String alertText) {
		JFrame alertFrame = new JFrame();
		JOptionPane.showMessageDialog(alertFrame, alertText);
		alertFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.getRootFrame().dispose();   
	}

///////////////////////////////////OKNO TABELI///////////////////////////////////
	
	public static void databaseTableCreate() {
        DefaultTableModel model = new DataTableModel(VariablesTransformation.returnList());
        dataTable = new JTable(model)
        {
//        	Implement table cell tool tips.           
    	    public String getToolTipText(MouseEvent e) {
    	        String tip = null;
    	        java.awt.Point p = e.getPoint();
    	        int rowIndex = rowAtPoint(p);
    	        int colIndex = columnAtPoint(p);

    	        try {
    	            tip = getValueAt(rowIndex, colIndex).toString();
    	        } catch (RuntimeException e1) {
    	            //catch null pointer exception if mouse is over an empty line
    	        }

    	        return tip;
    	    }
        };
        //VALIDACJA (cele na czerwono)
        dataTable.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
			@Override
            public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
                Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);;
                    c.setBackground(validationVector.get(column).validate(value) == true ? null : Color.RED);
                    c.setForeground(validationVector.get(column).validate(value)== true ? Color.BLACK : Color.WHITE);
                return c;
            }
        });
        dataTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        dataTable.setFillsViewportHeight(true);
        //Create the scroll pane and add the table to it.
        scrollPane = new JScrollPane(dataTable);
    }
	
	public static void btnDatabaseTableAction() {
	tablePanel.remove(0);
    tablePanel.validate();
    databaseTableCreate();
    tablePanel.add(scrollPane);
    tablePanel.validate();
	}
	
///////////////////////////////PUSTA TABELKA////////////////////////////////////////////////////////
	
	//Stworzenie pustej tabeli
	public static void createEmptyTable(boolean value) {
		if(value == true) {
			tablePanel.remove(0);
		    tablePanel.validate();
		    emptyTableInitialisation();
		    tablePanel.add(scrollPane);
		    tablePanel.validate();
		} else {
			emptyTableInitialisation();
		}
	}
	
	public static void emptyTableInitialisation() {
		Object[] colArray = (Object[]) columnNames.toArray();
		dataTable = new JTable(createInitialTableArray(), colArray)
		{
		//kolorowanie wierszy
		public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component comp = super.prepareRenderer(renderer, row, column);
            Color alternateColor = new Color(220, 255, 255);
            Color whiteColor = Color.WHITE;
            if(!comp.getBackground().equals(getSelectionBackground())) {
               Color c = (row % 2 == 0 ? alternateColor : whiteColor);
               comp.setBackground(c);
               c = null;
            }
	            return comp;
	        }
		};
		
        scrollPane = new JScrollPane(dataTable);
	}
	
	
	//Inicjowanie argumentu tab[][] pustej tabeli.
	public static Object[][] createInitialTableArray() {
		Object[][] dynTab = new String[25][columnNames.size()];
			for(int i=0; i< 25; i++) {
				for(int j=0;j<columnNames.size(); j++) {
					dynTab[i][j] = "";
				}
			}
		return dynTab;
	}
		
	//Drukowanie tabeli do pliku pdf	
	public static void printTable( ) throws PrinterException {
		dataTable.print();
	}
}