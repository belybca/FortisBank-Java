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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bus.Customer;
import bus.InputMismatchException;
import bus.RaiseException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DeleteCustomerJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static JPanel contentPane;
	private JTextField textCustomerNbDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCustomerJF frame = new DeleteCustomerJF();
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
	public DeleteCustomerJF() throws ClassNotFoundException, IOException, NumberFormatException, SQLException, RaiseException, InputMismatchException {
		setTitle("Fortis Bank Manager Search Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustName = new JLabel("Enter Customer Number :");
		lblCustName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustName.setBounds(24, 63, 190, 20);
		contentPane.add(lblCustName);
		
		textCustomerNbDelete = new JTextField();
		textCustomerNbDelete.setColumns(10);
		textCustomerNbDelete.setBounds(205, 64, 196, 20);
		contentPane.add(textCustomerNbDelete);
		
		JButton btnDelete = new JButton("Delete");
		
		btnDelete.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnDelete.setBounds(423, 63, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel lbldeleteCustomer = new JLabel("DELETE CUSTOMER");
		lbldeleteCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbldeleteCustomer.setBounds(205, 22, 129, 20);
		contentPane.add(lbldeleteCustomer);
		
		JLabel lblResults = new JLabel("Results :");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblResults.setBounds(24, 110, 190, 20);
		contentPane.add(lblResults);
		
		JButton btnDeleteAnotherCustomer = new JButton("Delete Another Customer");
		btnDeleteAnotherCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCustomerNbDelete.setText("");
			}
		});
		btnDeleteAnotherCustomer.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnDeleteAnotherCustomer.setBounds(84, 241, 196, 23);
		contentPane.add(btnDeleteAnotherCustomer);
		
		JButton btnExitDel = new JButton("EXIT");
		btnExitDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExitDel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExitDel.setBounds(322, 241, 89, 23);
		contentPane.add(btnExitDel);
		findTableData();
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String custId= textCustomerNbDelete.getText();
				try {
					if(Customer.remove(custId) == true)
					{
						 JOptionPane.showMessageDialog(contentPane,custId+" Removed sucessfully");
						 try {
							findTableData();
						} catch (ClassNotFoundException | IOException e1) {
						
							e1.printStackTrace();
						} catch (NumberFormatException e1) {
							
							e1.printStackTrace();
						} catch (RaiseException e1) {
							
							e1.printStackTrace();
						} catch (InputMismatchException e1) {
							
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Delete can not be processed.");
					}
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	}
	
	public static void findTableData() throws ClassNotFoundException, IOException, NumberFormatException, SQLException, RaiseException, InputMismatchException
	{
		String[] columnNames= {"CustomerNo","CustomerName","Pin","Status"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		JTable table = new JTable();
		table.setModel(model); 
		table.setBounds(24, 140, 482, 86);
		JScrollPane scrollPane = new JScrollPane(table); 
		scrollPane.setBounds(30, 141, 482, 86);	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		//contentPane.add(table);
		ArrayList<Customer> listOfCustomer=Customer.getCustomerList();
		model.addRow(new Object[] {"CustomerNo","CustomerName","Pin","Status"});
		if(listOfCustomer != null)
		{
			for(Customer cust : listOfCustomer) {
				model.addRow(new Object[] {cust.getcId(),cust.getcName(),cust.getcPin(),cust.getcStatus()});
				
			}
		}
		//model.fireTableDataChanged();
	}
}
