package app_view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import app_iotxt.VariablesTransformation;
import app_jdbc.Kontrahent;
import app_jdbc_update.EmailUpdate;
import app_valid.NipValidator;

public class FindByWindow {
	
	private static JFrame findFrame = new JFrame("Find Record!");
	private static JTextField label = new JTextField("Jakiœ takst!");
	private static String errorMsgText = "Enter Nip!";
	private static JLabel errorMsg = new JLabel(errorMsgText);
	private static JButton btn = new JButton("Check!");
	private static JPanel panel = new JPanel();
	private static JPanel mainPanel = new JPanel();

	
	public static void findByWindow() {
		
		errorMsg.setForeground(Color.BLACK);
		label.setPreferredSize(new Dimension( 200, 24));
		
		GroupLayout layout = new GroupLayout(panel);

		layout.setHorizontalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    		.addComponent(label)
		                .addComponent(btn)
		                )
			    );
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    		.addComponent(label))
			    	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
		                .addComponent(errorMsg)
		                .addComponent(btn)
		                )
			    );
		
		layout.linkSize(SwingConstants.HORIZONTAL, errorMsg);
		
        panel.add(label);
        panel.add(btn);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(errorMsg, BorderLayout.PAGE_START);
		mainPanel.add(panel, BorderLayout.CENTER);

		findFrame.add(mainPanel);
		
		label.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String txt = label.getText();
		        boolean trig = NipValidator.validateString(txt);
			        if(trig == true) {
			        	label.addActionListener(this);
			        	errorMsgText = "Correct input value.";
			        	errorMsg.setText(errorMsgText);
			        	errorMsg.setForeground(Color.BLUE);
						Integer temp = returnRecordNr(txt);
						//Nie da siê przejœæ do actionPerformed na statycznym obiekcie
						//dlatego tworzymy akcjê z klasy anominowej inicjowanej s³owem new.
						btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent a) {
								if(a.getSource() == btn) {
									label.removeActionListener(this);
									findFrame.dispose();
									if(temp != null) {
										PushToDbView.pushToDB(temp);
									} else {
										Window.runAlertFrame("There is no such NIP number!");
									}
								}
							}
						});
			        } else {
			        	label.addActionListener(this);
			        	errorMsgText = "Invalid input value.";
			        	errorMsg.setText(errorMsgText);
			        	errorMsg.setForeground(Color.RED);
			        	btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent a) {
								if(a.getSource() == btn) {
									Window.runAlertFrame("Input value is incorrect!");
									label.removeActionListener(this);
								}
							}
						});
			        }
				}
		});
		
		//trig == true ? (errorMsg.setForeground(label.getBackground())) : (errorMsg.setForeground(Color.RED));

		findFrame.setTitle("Push to DB process.");
		//Parametry okna aplikacji
		findFrame.setTitle("Data processing app.");
		findFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		findFrame.pack();
//		findFrame.setSize(600, 300);
		findFrame.setLocationRelativeTo(null);
		findFrame.setVisible(true);
	}
	
	public static Integer returnRecordNr(String arg) {
		Integer temp = 0;
		Integer recordRow = null;
		ArrayList<Kontrahent> lista = VariablesTransformation.returnList();
		while(temp<lista.size()) {
			if(lista.get(temp).getNip().equals(arg) ){
				recordRow = temp;
			}
			temp++;
		}
		return recordRow;
	}

}
