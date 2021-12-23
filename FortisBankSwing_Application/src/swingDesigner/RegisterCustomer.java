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



import bus.CheckingAccount;
import bus.CreditAccount;
import bus.CurrencyAccount;
import bus.Customer;

import bus.Enum_AccountType;
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class RegisterCustomer extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCustomerPINNumber;
	private JTextField txtCustomeName;
	private JTextField txtCustNumber;
	private JTextField txtAccNumber;
	static Customer cust = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterCustomer frame = new RegisterCustomer();
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
	public RegisterCustomer() {
		setTitle("Fortis Bank Manager Add Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 418);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcustnb = new JLabel("Enter PIN Number :");
		lblcustnb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblcustnb.setBounds(68, 140, 190, 20);
		contentPane.add(lblcustnb);
		
		txtCustomerPINNumber = new JTextField();
		txtCustomerPINNumber.setColumns(10);
		txtCustomerPINNumber.setBounds(268, 142, 196, 20);
		contentPane.add(txtCustomerPINNumber);
		
		JLabel lblcustname = new JLabel("Enter Customer Full Name :");
		lblcustname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblcustname.setBounds(68, 106, 190, 20);
		contentPane.add(lblcustname);
		
		txtCustomeName = new JTextField();
		txtCustomeName.setColumns(10);
		txtCustomeName.setBounds(268, 108, 196, 20);
		contentPane.add(txtCustomeName);
		
		JLabel lblacctype = new JLabel("Select Account type :");
		lblacctype.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblacctype.setBounds(68, 210, 145, 20);
		contentPane.add(lblacctype);
		
		JComboBox<Enum_AccountType> comboBoxAccounts = new JComboBox<Enum_AccountType>();
		comboBoxAccounts.setEnabled(false);
		comboBoxAccounts.setBounds(268, 211, 196, 20);
		
		comboBoxAccounts.addItem(Enum_AccountType.Checking);
		
		contentPane.add(comboBoxAccounts);
		
		JLabel lbladdCust = new JLabel("ADD CUSTOMER INFORMATION");
		lbladdCust.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbladdCust.setBounds(171, 44, 243, 20);
		contentPane.add(lbladdCust);
		
		txtCustNumber = new JTextField();
		txtCustNumber.setColumns(10);
		txtCustNumber.setBounds(268, 75, 196, 20);
		contentPane.add(txtCustNumber);
		

		txtAccNumber = new JTextField();
		txtAccNumber.setEnabled(false);
		txtAccNumber.setColumns(10);
		txtAccNumber.setBounds(268, 243, 196, 20);
		contentPane.add(txtAccNumber);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.setEnabled(false);
		JButton btnAddCustomer = new JButton("Add Customer");
		JButton btnAddAnotherAccount = new JButton("Add Another Account");
		btnAddAnotherAccount.setEnabled(false);
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String custNumber = txtCustNumber.getText();
					
					Customer aSearchCustomer = null;
					aSearchCustomer=Customer.search(custNumber);
					if(aSearchCustomer==null) {
					
					String cName =txtCustomeName.getText();
					String cPin = txtCustomerPINNumber.getText();
					cust = new Customer(custNumber,cName,cPin,"Active");
				
					if(Customer.add(cust)==true)
					{
						MainWindowForm.cust=cust;
						JOptionPane.showMessageDialog(contentPane,"Customer added sucessfully");
						txtCustNumber.setText("");
						txtCustNumber.setEnabled(false);
						txtCustomeName.setText("");
						txtCustomeName.setEnabled(false);
						txtCustomerPINNumber.setText("");
						txtCustomerPINNumber.setEnabled(false);
						btnAddCustomer.setEnabled(false);
						btnAddAccount.setEnabled(true);
						comboBoxAccounts.setEnabled(true);
						txtAccNumber.setEnabled(true);
						
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Data not added..");
						txtCustNumber.setText("");
						txtCustomeName.setText("");
						txtCustomerPINNumber.setText("");
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"This customer number already exist");
						txtCustNumber.setFocusable(true);
						txtCustNumber.setText("");
						txtCustNumber.setEnabled(true);
						
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				} catch (RaiseException e1) {
					e1.printStackTrace();
				} catch (InputMismatchException e1) {
					e1.printStackTrace();
				} 
			}
		});
		btnAddCustomer.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnAddCustomer.setBounds(187, 171, 168, 23);
		contentPane.add(btnAddCustomer);
		
		
		
		btnAddAccount.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				String accType = "",accNumber="", custNumber=cust.getcId();
				accNumber=txtAccNumber.getText();
				accType = comboBoxAccounts.getSelectedItem().toString();
					
						LocalDate date=LocalDate.now();
						
									CheckingAccount acc = new CheckingAccount();
									CheckingAccount src = new CheckingAccount();
							try {
								try {
									src=CheckingAccount.search(accNumber);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e2) {
								
								e2.printStackTrace();
							}
									if(src==null) {
									acc.setaClientNb(custNumber);
									acc.setaStatus("active");
									acc.setaOpenedDate(date);
									acc.setaType(accType);
									acc.setaNumber(accNumber);
									try {
										if(CheckingAccount.add(acc) == true)
										{
											JOptionPane.showMessageDialog(contentPane,"Account created sucessfully");
											btnAddAccount.setVisible(false);
											btnAddAnotherAccount.setEnabled(true);
											txtAccNumber.setText("");
											txtAccNumber.setFocusable(true);
										}
									} catch (HeadlessException e1) {
											e1.printStackTrace();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									}
									else {
										JOptionPane.showMessageDialog(contentPane,"This Account Number Already exist");
										txtCustNumber.setText("");
										txtCustNumber.setEnabled(false);
										txtCustomeName.setText("");
										txtCustomeName.setEnabled(false);
										txtCustomerPINNumber.setText("");
										txtCustomerPINNumber.setEnabled(false);
										txtAccNumber.setFocusable(true);
										
										
									}
								
						
						//Adding the other account options to the comboBox
						comboBoxAccounts.removeAllItems();
						for (Enum_AccountType type : Enum_AccountType.values())
						{
							comboBoxAccounts.addItem(type);
						}
						contentPane.add(comboBoxAccounts);
				
			}
		});
		btnAddAccount.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnAddAccount.setBounds(68, 288, 168, 23);
		contentPane.add(btnAddAccount);
		
		
		btnAddAnotherAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accType = "",accNumber="", custNumber=cust.getcId();
				accNumber=txtAccNumber.getText();
				accType = comboBoxAccounts.getSelectedItem().toString();
					
						LocalDate date=LocalDate.now();
						if(accNumber!="") {	
						
						switch(accType)
						{
							case "Checking" :
								
									CheckingAccount acc = new CheckingAccount();
									CheckingAccount src = new CheckingAccount();
							try {
								try {
									src=CheckingAccount.search(accNumber);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e2) {
								
								e2.printStackTrace();
							}
									if(src==null) {
									acc.setaClientNb(custNumber);
									acc.setaStatus("active");
									acc.setaOpenedDate(date);
									acc.setaType(accType);
									acc.setaNumber(accNumber);
									try {
										if(CheckingAccount.add(acc) == true)
										{
											JOptionPane.showMessageDialog(contentPane,"Account created sucessfully");
										}
									} catch (HeadlessException e1) {
											e1.printStackTrace();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
									}
									else {
										JOptionPane.showMessageDialog(contentPane,"This Account Number Already exist");
										txtCustNumber.setText("");
										txtCustNumber.setEnabled(false);
										txtCustomeName.setText("");
										txtCustomeName.setEnabled(false);
										txtCustomerPINNumber.setText("");
										txtCustomerPINNumber.setEnabled(false);
										txtAccNumber.setFocusable(true);
										
									}
										
								break;
							case "Saving" :
								SavingAccount sAcc = new SavingAccount();
								SavingAccount srcS = new SavingAccount();
							try {
								try {
									srcS=SavingAccount.search(accNumber);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
								if(srcS==null) {
								sAcc.setaClientNb(custNumber);
								sAcc.setaStatus("active");
								sAcc.setaOpenedDate(date);
								sAcc.setaType(accType);
								sAcc.setaNumber(accNumber);
								try {
									if(SavingAccount.add(sAcc) == true)
									{
										JOptionPane.showMessageDialog(contentPane,"Account created sucessfully");
									}
								} catch (HeadlessException e1) {
									e1.printStackTrace();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								}
								else {
									JOptionPane.showMessageDialog(contentPane,"This Account Number Already exist");
								}
								break;
							case "Credit" :
								CreditAccount cAcc = new CreditAccount();
								CreditAccount srcCd = new CreditAccount();
							try {
								try {
									srcCd=CreditAccount.search(accNumber);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
								if(srcCd==null) {
								cAcc.setaClientNb(custNumber);
								cAcc.setaStatus("active");
								cAcc.setaOpenedDate(date);
								cAcc.setaType(accType);
								cAcc.setaNumber(accNumber);
								try {
									if(CreditAccount.add(cAcc) == true)
									{
										JOptionPane.showMessageDialog(contentPane,"Account created sucessfully");
									}
								} catch (HeadlessException e1) {
									
									e1.printStackTrace();
								} catch (SQLException e1) {
									
									e1.printStackTrace();
								}
								}
								else {
									JOptionPane.showMessageDialog(contentPane,"This Account Number Already exist");
								}
								break;
							case "Currency":
								CurrencyAccount crAcc = new CurrencyAccount();
								CurrencyAccount srcCr = new CurrencyAccount();
							try {
								try {
									srcCr=CurrencyAccount.search(accNumber);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
								if(srcCr==null) {
								crAcc.setaClientNb(custNumber);
								crAcc.setaStatus("active");
								crAcc.setaOpenedDate(date);
								crAcc.setaType(accType);
								crAcc.setaNumber(accNumber);
								try {
									if(CurrencyAccount.add(crAcc) == true)
									{
										JOptionPane.showMessageDialog(contentPane,"Account added sucessfully");
									}
								} catch (HeadlessException e1) {
									e1.printStackTrace();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								}
								else {
									JOptionPane.showMessageDialog(contentPane,"This Account Number Already exist");
								}
								break;	
						}
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"You must enter an account number to procced");
						}
			
			}
		});
		btnAddAnotherAccount.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnAddAnotherAccount.setBounds(296, 288, 168, 23);
		contentPane.add(btnAddAnotherAccount);
		
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExit.setBounds(219, 334, 89, 23);
		contentPane.add(btnExit);
	
		
		JLabel lblcustNumber = new JLabel("Enter Customer Number :");
		lblcustNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblcustNumber.setBounds(68, 73, 190, 20);
		contentPane.add(lblcustNumber);
		
		JLabel lblacNumber = new JLabel("Enter Account Number :");
		lblacNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblacNumber.setBounds(68, 241, 190, 20);
		contentPane.add(lblacNumber);
		
		
		
		
		
		
	}
}
