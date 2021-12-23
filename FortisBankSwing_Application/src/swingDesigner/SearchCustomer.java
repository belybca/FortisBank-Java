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
import javax.swing.border.EmptyBorder;

import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SearchCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCustomerNbSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchCustomer frame = new SearchCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchCustomer() {
		setTitle("Fortis Bank Manager Search Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustNb = new JLabel("Enter Customer Number :");
		lblCustNb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustNb.setBounds(24, 63, 190, 20);
		contentPane.add(lblCustNb);
		
		textCustomerNbSearch = new JTextField();
		textCustomerNbSearch.setColumns(10);
		textCustomerNbSearch.setBounds(205, 64, 196, 20);
		contentPane.add(textCustomerNbSearch);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnSearch.setBounds(423, 63, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblSearccust = new JLabel("SEARCH CUSTOMER");
		lblSearccust.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearccust.setBounds(205, 22, 129, 20);
		contentPane.add(lblSearccust);
		
		JTextArea txtAreaCustomerSearch = new JTextArea();
		txtAreaCustomerSearch.setLineWrap(true);
		txtAreaCustomerSearch.setEditable(false);
		txtAreaCustomerSearch.setBounds(37, 141, 446, 45);
		contentPane.add(txtAreaCustomerSearch);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = textCustomerNbSearch.getText();
				Customer cust = null;
				try {
					try {
						cust = Customer.search(search);
					} catch (RaiseException | InputMismatchException e1) {
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (cust == null)
				{
					txtAreaCustomerSearch.setText("Customer Not Found!!!");
				}
				else
				{
					txtAreaCustomerSearch.setText(cust.toString());
				}
				
			}
		});
		JLabel lblResults = new JLabel("Results :");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResults.setBounds(37, 110, 190, 20);
		contentPane.add(lblResults);
		
		JButton btnSearchAnotherCustomer = new JButton("Search Another Customer");
		btnSearchAnotherCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textCustomerNbSearch.setText("");
				txtAreaCustomerSearch.setText("");
			}
		});
		btnSearchAnotherCustomer.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnSearchAnotherCustomer.setBounds(84, 241, 196, 23);
		contentPane.add(btnSearchAnotherCustomer);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			}
		});
		btnExit.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExit.setBounds(322, 241, 89, 23);
		contentPane.add(btnExit);
	}
}
