package main_package;

import java.awt.EventQueue;

import javax.swing.UIManager;

import app_view.Window;

public class Main {
 	public static void main(String[] args) {
 		EventQueue.invokeLater(new Runnable() {
 			@Override
 			public void run() {
 				try { 
 			        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
 			    } catch(Exception ignored){}
 			    new Window(); //start your application
 			}
 		});
 	}
}
