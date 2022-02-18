package app_view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app_iotxt.VariablesTransformation;
import app_jdbc.Kontrahent;
import app_jdbc_update.AbstractUpdateCell;
import app_jdbc_update.CityUpdate;
import app_jdbc_update.CompanyNameUpdate;
import app_jdbc_update.CompanyPhoneUpdate;
import app_jdbc_update.EmailUpdate;
import app_jdbc_update.FirstNameUpdate;
import app_jdbc_update.LastNameUpdate;
import app_jdbc_update.NipUpdate;
import app_jdbc_update.PersonalPhoneUpdate;
import app_jdbc_update.SexUpdate;
import app_jdbc_update.StreetNumberUpdate;
import app_jdbc_update.StreetUpdate;
import app_jdbc_update.ZipCodeUpdate;
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

public class PushToDbView{
	
	public static ArrayList<AbstractUpdateCell> updateVector = new ArrayList<>();
	private static ArrayList<AbstractValidationClass> valVec = Window.validationVector;
	
	private static JTextField firstName = new JTextField("First_name");
	private static JTextField lastName = new JTextField("Last_name");
	private static JTextField sex = new JTextField("Sex");
	private static JTextField personalPhone = new JTextField("Personal_phone_number");
	private static JTextField companyPhone = new JTextField("Company_phone_number");
	private static JTextField email = new JTextField("Email");
	private static JTextField company = new JTextField("Company");
	private static JTextField nip = new JTextField("NIP");
	private static JTextField city = new JTextField("City");
	private static JTextField street = new JTextField("Street");
	private static JTextField streetNumber = new JTextField("Address_Number");
	private static JTextField zipCode= new JTextField("ZIP_code");
	
	private static JLabel firstNameLab = new JLabel("First_name");
	private static JLabel lastNameLab = new JLabel("Last_name");
	private static JLabel sexLab = new JLabel("Sex");
	private static JLabel personalPhoneLab = new JLabel("Personal_phone_number");
	private static JLabel companyPhoneLab = new JLabel("Company_phone_number");
	private static JLabel emailLab = new JLabel("Email");
	private static JLabel companyLab = new JLabel("Company");
	private static JLabel nipLab = new JLabel("NIP");
	private static JLabel cityLab = new JLabel("City");
	private static JLabel streetLab = new JLabel("Street");
	private static JLabel streetNumberLab = new JLabel("Address_Number");
	private static JLabel zipCodeLab = new JLabel("ZIP_code");
	
	private static JLabel titleLabel = new JLabel("Push to DB process - check, edit and send one record to database.");
	
	private static JButton button = new JButton("Push to db!");
	
	private static JPanel component = new JPanel();
	private static JPanel mainPanel = new JPanel();
	private static JFrame frame = new JFrame();
	private String auxText;
	
//	private static int i = 0;

	public static void pushToDB(int row) {

		component.add(firstName);
		component.add(lastName);
		component.add(sex);
		component.add(personalPhone);
		component.add(companyPhone);
		component.add(email);
		component.add(company);
		component.add(nip);
		component.add(city);
		component.add(street);
		component.add(streetNumber);
		component.add(zipCode);
        component.add(button);
        
        fillLabels(row);
        
        Vector<JTextField> labelVec = new Vector<>();
        labelVec.add(firstName);
        labelVec.add(lastName);
        labelVec.add(sex);
        labelVec.add(personalPhone);//nr komórkowy
        labelVec.add(companyPhone);//nr stacjonarny
        labelVec.add(email);
        labelVec.add(company);
        labelVec.add(nip);
        labelVec.add(city);
        labelVec.add(street);
        labelVec.add(streetNumber);
        labelVec.add(zipCode);
        
        Vector<String> labelVectorTxt = new Vector<>();
        labelVectorTxt.add(firstName.getText());
        labelVectorTxt.add(lastName.getText());
        labelVectorTxt.add(sex.getText());
        labelVectorTxt.add(personalPhone.getText());//nr komórkowy
        labelVectorTxt.add(companyPhone.getText());//nr stacjonarny
        labelVectorTxt.add(email.getText());
        labelVectorTxt.add(company.getText());
        labelVectorTxt.add(nip.getText());
        labelVectorTxt.add(city.getText());
        labelVectorTxt.add(street.getText());
        labelVectorTxt.add(streetNumber.getText());
        labelVectorTxt.add(zipCode.getText());
        
//        validAllFields(labelVec, labelVectorTxt);

        
        updateVector.add(new FirstNameUpdate());
        updateVector.add(new LastNameUpdate());
        updateVector.add(new SexUpdate());
        updateVector.add(new PersonalPhoneUpdate());
        updateVector.add(new CompanyPhoneUpdate());
        updateVector.add(new EmailUpdate());
        updateVector.add(new CompanyNameUpdate());
        updateVector.add(new NipUpdate());
        updateVector.add(new CityUpdate());
        updateVector.add(new StreetUpdate());
        updateVector.add(new StreetNumberUpdate());
        updateVector.add(new ZipCodeUpdate());
        
		GroupLayout layout = new GroupLayout(component);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    		.addComponent(firstNameLab)
		    		.addComponent(firstName)
		            .addComponent(button)
		    		)
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    		.addComponent(lastNameLab)
	                .addComponent(lastName)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(sexLab)
	                .addComponent(sex)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(personalPhoneLab)
	                .addComponent(personalPhone)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                .addComponent(companyPhoneLab)
	                .addComponent(companyPhone)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        		  	.addComponent(emailLab)
        		  	.addComponent(email)
        		   )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
        		  	.addComponent(companyLab)
        		  	.addComponent(company)
        		  	)
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            		.addComponent(nipLab)
		            .addComponent(nip)
		            )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    		.addComponent(cityLab)
	                .addComponent(city)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    		.addComponent(streetLab)
	                .addComponent(street)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		    		.addComponent(streetNumberLab)
	                .addComponent(streetNumber)
	                )
              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	  				.addComponent(zipCodeLab)
	                .addComponent(zipCode)
	                )
			);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				  .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
		    		.addComponent(firstNameLab)
		    		.addComponent(lastNameLab)
		    		.addComponent(sexLab)
		    		.addComponent(personalPhoneLab)
		    		.addComponent(companyPhoneLab)
		    		.addComponent(emailLab)
		    		.addComponent(companyLab)
		    		.addComponent(nipLab)
		    		.addComponent(cityLab)
		    		.addComponent(streetLab)
		    		.addComponent(streetNumberLab)
		    		.addComponent(zipCodeLab)
		    		)
	              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            		.addComponent(firstName)
  		    		.addComponent(lastName)
  		    		.addComponent(sex)
  		    		.addComponent(personalPhone)
  		    		.addComponent(companyPhone)
  		    		.addComponent(email)
  		    		.addComponent(company)
  		    		.addComponent(nip)
  		    		.addComponent(city)
  		    		.addComponent(street)
  		    		.addComponent(streetNumber)
  		    		.addComponent(zipCode)
		    		)
	              .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		    .addComponent(button)
	            	)
		     );
		component.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.linkSize(SwingConstants.HORIZONTAL, titleLabel);
		
		component.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.blue), BorderFactory.createEtchedBorder()));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(titleLabel, BorderLayout.PAGE_START);
		mainPanel.add(component, BorderLayout.CENTER);
		frame.add(mainPanel);
        
        frame.pack();
		//Parametry okna aplikacji
        frame.setTitle("Push to DB process.");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
//		findFrame.setSize(600, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
		//aktualizacja w now¹ wartoœæ pola tekstowego email
//		// Listen for changes in the text
        email.getDocument().addDocumentListener(new DocumentListener() {
        	  public void changedUpdate(DocumentEvent e) {
        	    warn();
        	  }
        	  public void removeUpdate(DocumentEvent e) {
        	    warn();
        	  }
        	  public void insertUpdate(DocumentEvent e) {
        	    warn();
        	    System.out.println(email.getText());
        	  }

        	  public void warn() {
        	     if (email.getText().equals(null)){
        	       JOptionPane.showMessageDialog(null,
        	          "Error: Please enter number bigger than 0", "Error Message",
        	          JOptionPane.ERROR_MESSAGE);
        	     }
        	  }
        	});
        
		//Akcja przycisku
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				if(a.getSource() == button) {
//					System.out.println(labelStringVector.size());
			        updateAllFields(row, labelVectorTxt, updateVector);
				}
			}
		});
	}
//	public static void fieldValidation(LabelVec) {
//	    for(int i=0;i<LabelVec.size();i++) {
//	        firstName.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//	
//					String txt = label.getText();
//			        boolean trig = NipValidator.validateString(txt);
//				        if(trig == true) {
//				        	label.addActionListener(this);
//	
//				        	errorMsg.setForeground(label.getBackground());
//							//Nie da siê przejœæ do actionPerformed na statycznym obiekcie
//							//dlatego tworzymy akcjê z klasy anominowej inicjowanej s³owem new.
//							btn.addActionListener(new ActionListener() {
//								public void actionPerformed(ActionEvent a) {
//									if(a.getSource() == btn) {
//										Window.runAlertFrame("Input value is correct!");
//										label.removeActionListener(this);
//										findFrame.dispose();
//										PushToDbView.pushToDB();
//									}
//								}
//							});
//				        } else {
//				        	label.addActionListener(this);
//				        	
//				        	errorMsg.setForeground(Color.RED);
//				        	btn.addActionListener(new ActionListener() {
//								public void actionPerformed(ActionEvent a) {
//									if(a.getSource() == btn) {
//										Window.runAlertFrame("Input value is incorrect!");
//										label.removeActionListener(this);
//									}
//								}
//					    });
//				     }
//				}
//			});
//	    }
//   }
	
	public static void fillLabels(int recordRow) {
		ArrayList<Kontrahent> lista = VariablesTransformation.returnList();
		firstName.setText(lista.get(recordRow).getFirstName());
		lastName.setText(lista.get(recordRow).getLastName());
		sex.setText(lista.get(recordRow).getSex());
		personalPhone.setText(lista.get(recordRow).getPersonalPhone());
		companyPhone.setText(lista.get(recordRow).getCompanyPhone());
		email.setText(lista.get(recordRow).getEmail());
		company.setText(lista.get(recordRow).getCompany());
		nip.setText(lista.get(recordRow).getNip());
		city.setText(lista.get(recordRow).getCity());
		street.setText(lista.get(recordRow).getStreet());
		streetNumber.setText(lista.get(recordRow).getStreetNumber());
		zipCode.setText(lista.get(recordRow).getZipCode());

	}
	
	public static void updateAllFields(int recordRow, @SuppressWarnings("rawtypes") Vector labelStringVector,
			ArrayList<AbstractUpdateCell> updateVector) {
		System.out.println(updateVector.get(5));
		String txt = (String) labelStringVector.get(5);
		System.out.println(txt);
		updateVector.get(5).updateCell(txt, recordRow+1);
		
//		for(int i=0;i<labelStringVector.size();i++) {
//			String txt = (String) labelStringVector.get(i);
//			updateVector.get(i).updateCell(txt, recordRow+1);
//		}
	}
	
//	public static void validAllFields(Vector<JTextField> vec1, Vector<String> labelVectorTxt) {
//		
//		while(i<labelVectorTxt.size()) {
//			Object label = vec1.get(i);
//			((JTextField) label).addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
////					for(int i=0;i<labelVectorTxt.size();i++) {
//						String txt =  (String) labelVectorTxt.get(i);
//						System.out.println(i);
//						boolean pattern = valVec.get(i).validate(txt);
//						System.out.println(valVec.get(i));
//						if(pattern) {
//							((JTextField) label).setBackground(Color.WHITE);
//						} else {
//							((JTextField) label).setBackground(Color.RED);
//						}
////					}
//				}
//			});
//			i++;
//		}
//		i = 0;
//	}
}
