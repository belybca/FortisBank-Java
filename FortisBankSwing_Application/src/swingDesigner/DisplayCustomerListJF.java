/*
Group
Rameswariben Bhoi
You Pan
Albelis Becea
Description: Fortis Bank application
Date: Nov 21 2021


*/
package swingDesigner;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;


import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class DisplayCustomerListJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayCustomerListJF frame = new DisplayCustomerListJF();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws InputMismatchException 
	 * @throws RaiseException 
	 */
	public DisplayCustomerListJF() throws ClassNotFoundException, IOException, NumberFormatException, SQLException, RaiseException, InputMismatchException {
		setTitle("Fortis Bank Manager Display Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 504);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDisplayCust = new JLabel("DISPLAY CUSTOMERS");
		lblDisplayCust.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDisplayCust.setBounds(195, 25, 144, 20);
		contentPane.add(lblDisplayCust);
		
		String[] columnNames= {"CustomerNo","CustomerName","Pin","Status"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		JTable table = new JTable();
		table.setModel(model); 
		table.setBounds(24, 66, 490, 254);
																				
		JScrollPane scrollPane = new JScrollPane(table); 
		scrollPane.setBounds(23, 118, 490, 250);	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		
		//contentPane.add(table);
		model.fireTableDataChanged();
		ArrayList<Customer> listOfCustomer=Customer.getCustomerList();
		while(model.getRowCount() >0) {
			model.removeRow(0);
			
		}
		if(listOfCustomer != null)
		{
			model.addRow(new Object[] {"CustomerNo","CustomerName","Pin","Status"});
			for(Customer cust : listOfCustomer) {
				model.addRow(new Object[] {cust.getcId(),cust.getcName(),cust.getcPin(),cust.getcStatus()});
				
			}
		}
		
		JButton btnExitDisplay = new JButton("EXIT");
		btnExitDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExitDisplay.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExitDisplay.setBounds(405, 408, 89, 23);
		contentPane.add(btnExitDisplay);
		
		JButton btnOrderByName = new JButton("Order By Name");
		btnOrderByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				model.fireTableDataChanged();
				ArrayList<Customer> listOfCustomer = null;
				try {
					listOfCustomer = Customer.sortCustomerListByID();
				} catch (NumberFormatException | SQLException | RaiseException | InputMismatchException e1) {
					
					e1.printStackTrace();
				}
				while(model.getRowCount() >0) {
					model.removeRow(0);
					
				}
				if(listOfCustomer != null)
				{
					model.addRow(new Object[] {"CustomerNo","CustomerName","Pin","Status"});
					for(Customer cust : listOfCustomer) {
						model.addRow(new Object[] {cust.getcId(),cust.getcName(),cust.getcPin(),cust.getcStatus()});
						
					}
				}
			
			
			}
		});
		btnOrderByName.setBackground(Color.ORANGE);
		btnOrderByName.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnOrderByName.setBounds(23, 84, 238, 23);
		contentPane.add(btnOrderByName);
		
		JButton btnOrderByIdentification = new JButton("Order By Identification");
		btnOrderByIdentification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				model.fireTableDataChanged();
				ArrayList<Customer> listOfCustomer = null;
				try {
					listOfCustomer = Customer.sortCustomerListbyName();
				} catch (NumberFormatException | SQLException | RaiseException | InputMismatchException e1) {
				
					e1.printStackTrace();
				}
				while(model.getRowCount() >0) {
					model.removeRow(0);
					
				}
				if(listOfCustomer != null)
				{
					model.addRow(new Object[] {"CustomerNo","CustomerName","Pin","Status"});
					for(Customer cust : listOfCustomer) {
						model.addRow(new Object[] {cust.getcId(),cust.getcName(),cust.getcPin(),cust.getcStatus()});
						
					}
				}
			
			}
		});
		btnOrderByIdentification.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnOrderByIdentification.setBackground(Color.ORANGE);
		btnOrderByIdentification.setBounds(271, 84, 238, 23);
		contentPane.add(btnOrderByIdentification);
		

		
		
	}
}
