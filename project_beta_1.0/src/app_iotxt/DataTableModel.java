package app_iotxt;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import app_jdbc.Kontrahent;
import app_view.Window;

@SuppressWarnings("serial")
public class DataTableModel extends DefaultTableModel {
	
	Object[] columnClass = new Object[] {
    		String.class, String.class, String.class, String.class, String.class
    		, String.class, String.class, String.class, String.class, String.class,
    		String.class, String.class
        };
	
	private int modelRows = VariablesTransformation.returnList().size();
	private Vector<String> modelColName = Window.returnColumnNames();
	private int modelCol = Window.returnColumnNames().size();
	
	public ArrayList<Kontrahent> list;
	
	public DataTableModel(ArrayList<Kontrahent> arrayList) {
		this.list = arrayList;
	}

   @Override
    public String getColumnName(int columnIndex){
         return modelColName.get(columnIndex);
    }
   
	@Override
	public int getRowCount() {
		return modelRows;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return modelCol;
	}

	@Override
    public Object getValueAt(int rowIndex, int columnIndex) {
		Kontrahent si = list.get(rowIndex);
        switch (columnIndex) {
            case 0: 
                return si.getFirstName();
            case 1:
                return si.getLastName();
            case 2:
                return si.getSex();
            case 3:
                return si.getPersonalPhone();
            case 4:
                return si.getCompanyPhone();
            case 5:
                return si.getEmail();
            case 6:
                return si.getCompany(); 
            case 7:
                return si.getNip(); 
            case 8:
                return si.getCity(); 
            case 9:
            	return si.getStreet();
            case 10:
            	return si.getStreetNumber();
            case 11:
            	return si.getZipCode();
           }
           return null;
	}
	
    @Override
    public Class<?> getColumnClass(int column) {
    	return (Class<?>) columnClass[column];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
    	if(columnIndex == -1) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Kontrahent row = list.get(rowIndex);
        if(0 == columnIndex) {
            row.setFirstName((String) aValue);
        }
        else if(1 == columnIndex) {
            row.setLastName((String) aValue);
        }
        else if(2 == columnIndex) {
            row.setSex((String) aValue);
        }
        else if(3 == columnIndex) {
            row.setPersonalPhone((String) aValue);
        }
        else if(4 == columnIndex) {
            row.setCompanyPhone((String) aValue);
        }
        else if(5 == columnIndex) {
            row.setEmail((String) aValue);
        }
        else if(6 == columnIndex) {
            row.setCompany((String) aValue);
        }
        else if(7 == columnIndex) {
            row.setNip((String) aValue);
        }
        else if(8 == columnIndex) {
            row.setCity((String) aValue);
        }
        else if(9 == columnIndex) {
            row.setStreet((String) aValue);
        }
        else if(10 == columnIndex) {
            row.setStreetNumber((String) aValue);
        }
        else if(11 == columnIndex) {
            row.setZipCode((String) aValue);
        }
    }

	public Object getStatus(int row) {
		// TODO Auto-generated method stub
		return null;
	}
}
