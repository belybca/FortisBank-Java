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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ManageCustomerAccountJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCustomerNbSearch;
	String operationtype;
	private JTextField txtAccNumber;
	Customer cust = null;
	CheckingAccount checAcc = null;
	SavingAccount savAcc = null;
	CurrencyAccount currAcc = null;
	CreditAccount crdAcc = null;
	String accountToDel="", acType="";
	private JTextField txtCustName;

	
			

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageCustomerAccountJF frame = new ManageCustomerAccountJF();
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
	public ManageCustomerAccountJF() {
		setTitle("Fortis Bank Manager Manage Accounts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustNb = new JLabel("Enter Customer Number :");
		lblCustNb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCustNb.setBounds(21, 46, 170, 20);
		contentPane.add(lblCustNb);
		
		textCustomerNbSearch = new JTextField();
		textCustomerNbSearch.setColumns(10);
		textCustomerNbSearch.setBounds(202, 47, 196, 20);
		contentPane.add(textCustomerNbSearch);
		
		JButton btnSearch = new JButton("Search");
		
		btnSearch.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnSearch.setBounds(420, 46, 89, 23);
		contentPane.add(btnSearch);
		
		JLabel lblMngCustAcc = new JLabel("MANAGE CUSTOMER ACCOUNTS");
		lblMngCustAcc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMngCustAcc.setBounds(181, 11, 196, 20);
		contentPane.add(lblMngCustAcc);
		
		JLabel lblResults = new JLabel("text");
		lblResults.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResults.setBounds(31, 77, 475, 20);
		contentPane.add(lblResults);
		
		JComboBox<String> comboBoxAccounts = new JComboBox<String>();
		
		JComboBox<String> comboBoxMngOperation = new JComboBox<String>();
		JLabel lblacNumber = new JLabel("Enter Account Number :");
		txtAccNumber = new JTextField();
		JLabel lblcustname = new JLabel("Enter Customer Full Name :");
		txtCustName = new JTextField();
		
		JButton btnAddAccount = new JButton("Add Account");
		JButton btnDeleteAccount = new JButton("Delete Account");
		JButton btnUpdateCustomerName = new JButton("Update Customer Name");
		
		
		
		btnAddAccount.setVisible(false);
		btnDeleteAccount.setVisible(false);
		btnUpdateCustomerName.setVisible(false);
		lblcustname.setVisible(false);
		txtCustName.setVisible(false);
		txtAccNumber.setVisible(false);
		lblacNumber.setVisible(false);
		
		
		btnUpdateCustomerName.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				String customerNewName=txtCustName.getText();
				try {
					cust.setcName(customerNewName);
				} catch (InputMismatchException e1) {
					
					e1.printStackTrace();
				} catch (RaiseException e1) {
					
					e1.printStackTrace();
				}
				try {
					if(cust.update(cust)) {
						JOptionPane.showMessageDialog(contentPane,"The name of the customer "+cust.getcId()+" has been changed");
						txtCustName.setText("");
					}
					else {
						JOptionPane.showMessageDialog(contentPane,"Something went wrong");
					}
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			
			}
		});
		JLabel lblacctype = new JLabel("Select Account type :");
		
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String accType = "",accNumber="", custNumber=cust.getcId();
				accNumber=txtAccNumber.getText();
				accType = comboBoxAccounts.getSelectedItem().toString();
					
						LocalDate date=LocalDate.now();
						
						
						switch(accType)
						{
							case "Checking" :
								
									CheckingAccount acc = new CheckingAccount();
									CheckingAccount src = new CheckingAccount();
							try {
								src=CheckingAccount.search(accNumber);
							} catch (SQLException e2) {
								
								e2.printStackTrace();
							} catch (RaiseException e1) {
								
								e1.printStackTrace();
							} catch (InputMismatchException e1) {
								
								e1.printStackTrace();
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
							
										txtAccNumber.setText("");
										txtAccNumber.setFocusable(true);
										
									}
										
								break;
							case "Saving" :
								SavingAccount sAcc = new SavingAccount();
								SavingAccount srcS = new SavingAccount();
							try {
								srcS=SavingAccount.search(accNumber);
							} catch (SQLException e2) {
								e2.printStackTrace();
							} catch (RaiseException e1) {
								
								e1.printStackTrace();
							} catch (InputMismatchException e1) {
							
								e1.printStackTrace();
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
								srcCd=CreditAccount.search(accNumber);
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (RaiseException e1) {
								
								e1.printStackTrace();
							} catch (InputMismatchException e1) {
								
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
								srcCr=CurrencyAccount.search(accNumber);
							} catch (SQLException e1) {
								e1.printStackTrace();
							} catch (ParseException e1) {
								e1.printStackTrace();
							} catch (RaiseException e1) {
								e1.printStackTrace();
							} catch (InputMismatchException e1) {
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
										JOptionPane.showMessageDialog(contentPane,"Account created sucessfully");
										lblacNumber.setVisible(false);
										txtAccNumber.setVisible(false);
										btnAddAccount.setVisible(false);
										txtAccNumber.setText("");
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
		});
			comboBoxMngOperation.setEnabled(false);
		
			
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = textCustomerNbSearch.getText();
				
				try {
					cust = Customer.search(search);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (RaiseException e1) {
					e1.printStackTrace();
				} catch (InputMismatchException e1) {
					e1.printStackTrace();
				}
				if (cust == null)
				{
					textCustomerNbSearch.setText("");
					JOptionPane.showMessageDialog(contentPane,"Customer Not Found!!!");
				
				}
				else
				{
					lblResults.setVisible(true);
					lblResults.setText("Customer Identification: "+cust.getcId()+"    Customer Name: "+cust.getcName());
					btnSearch.setEnabled(false);
					textCustomerNbSearch.setEnabled(false);
					
					comboBoxMngOperation.setEnabled(true);
				}
				
			}
		});
		
	
		
		comboBoxMngOperation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				operationtype = comboBoxMngOperation.getSelectedItem().toString();
				switch(operationtype) {
				
				case "Add another account":
					
					comboBoxAccounts.setEnabled(true);
					lblacNumber.setVisible(true);
					txtAccNumber.setVisible(true);
					btnAddAccount.setVisible(true);
					btnDeleteAccount.setVisible(false);
					btnUpdateCustomerName.setVisible(false);
					lblcustname.setVisible(false);
					txtCustName.setVisible(false);
					comboBoxAccounts.setVisible(true);
					
					comboBoxAccounts.setBounds(221, 172, 196, 20);
					comboBoxAccounts.removeAllItems();
					for (Enum_AccountType type : Enum_AccountType.values())
					{
						comboBoxAccounts.addItem(type.toString());
					}
					contentPane.add(comboBoxAccounts);
					
					
					
					break;
				
				
				
				case "Delete account":
					lblacNumber.setVisible(false);
					txtAccNumber.setVisible(false);
					btnDeleteAccount.setVisible(true);
					btnAddAccount.setVisible(false);
					btnUpdateCustomerName.setVisible(false);
					lblcustname.setVisible(false);
					txtCustName.setVisible(false);
					comboBoxAccounts.setVisible(true);
					
					comboBoxAccounts.setEnabled(true);
					btnAddAccount.setVisible(false);
					txtAccNumber.setText("");
					comboBoxAccounts.setBounds(221, 172, 196, 20);
					comboBoxAccounts.removeAllItems();
					try {
						checAcc = CheckingAccount.searchAcccountByCustomer(cust.getcId());
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					} catch (RaiseException e1) {
						e1.printStackTrace();
					} catch (InputMismatchException e1) {
						e1.printStackTrace();
					}
					
					try {
						savAcc = SavingAccount.searchAcccountByCustomer(cust.getcId());
					} catch (RaiseException e2) {
						e2.printStackTrace();
					} catch (InputMismatchException e2) {
						e2.printStackTrace();
					}
					try {
						currAcc = CurrencyAccount.searchAcccountByCustomer(cust.getcId());
					} catch (SQLException | ParseException e1) {
						
						e1.printStackTrace();
					} catch (RaiseException e1) {
						e1.printStackTrace();
					} catch (InputMismatchException e1) {
						e1.printStackTrace();
					}
					try {
						crdAcc = CreditAccount.searchAcccountByCustomer(cust.getcId());
					} catch (RaiseException | InputMismatchException e1) {
						e1.printStackTrace();
					}
					
					if (checAcc!=null){
						
						comboBoxAccounts.addItem(checAcc.getaNumber()+" - "+checAcc.getaType());
					
					}
					if (savAcc!=null){
						comboBoxAccounts.addItem(savAcc.getaNumber()+" - "+savAcc.getaType());
					}
				    if (currAcc!=null){
						comboBoxAccounts.addItem(currAcc.getaNumber()+" - "+currAcc.getaType());
					}
					if (crdAcc!=null){
						comboBoxAccounts.addItem(crdAcc.getaNumber()+" - "+crdAcc.getaType());
					}
				
					contentPane.add(comboBoxAccounts);
					
					
					
					comboBoxAccounts.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(comboBoxAccounts.getSelectedItem()!=null) {
							String[] selectedAcc = comboBoxAccounts.getSelectedItem().toString().split(" - ");
							if(selectedAcc.length==2) {
							accountToDel=selectedAcc[0];
							acType=selectedAcc[1];
							}
							}
						}
					
					});
					
					
					
					
			     
					break;
				case "Update customer name":
					btnUpdateCustomerName.setVisible(true);
					btnAddAccount.setVisible(false);
					btnDeleteAccount.setVisible(false);
					lblcustname.setVisible(true);
					txtCustName.setVisible(true);
					txtAccNumber.setVisible(false);
					lblacNumber.setVisible(false);
					lblacctype.setVisible(false);
					comboBoxAccounts.setVisible(false);
					
					
					break;
				
				}
			
			}
		});
		
		
		
		JButton btnSearchAnotherCustomer = new JButton("Search Another Customer");
		btnSearchAnotherCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textCustomerNbSearch.setText("");
				textCustomerNbSearch.setEnabled(true);
				btnAddAccount.setVisible(false);
				btnDeleteAccount.setVisible(false);
				btnUpdateCustomerName.setVisible(false);
				lblcustname.setVisible(false);
				txtCustName.setVisible(false);
				txtAccNumber.setVisible(false);
				lblacNumber.setVisible(false);
				btnSearch.setEnabled(true);
				comboBoxMngOperation.setEnabled(false);
				
			}
		});
		btnSearchAnotherCustomer.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnSearchAnotherCustomer.setBounds(111, 350, 196, 23);
		contentPane.add(btnSearchAnotherCustomer);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			}
		});
		btnExit.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExit.setBounds(349, 350, 89, 23);
		contentPane.add(btnExit);
		
		
		comboBoxMngOperation.setBounds(221, 122, 194, 22);
		comboBoxMngOperation.addItem("");
		comboBoxMngOperation.addItem("Add another account");
		comboBoxMngOperation.addItem("Delete account");
		comboBoxMngOperation.addItem("Update customer name");
						
		
		contentPane.add(comboBoxMngOperation);
		
		JLabel lblSelectOperation = new JLabel("Select Operation :");
		lblSelectOperation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectOperation.setBounds(21, 122, 141, 20);
		contentPane.add(lblSelectOperation);
		
		
		lblacctype.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblacctype.setBounds(21, 171, 145, 20);
		contentPane.add(lblacctype);
		
		
		
		
		
		lblacNumber.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblacNumber.setBounds(21, 202, 190, 20);
		contentPane.add(lblacNumber);
		txtAccNumber.setColumns(10);
		txtAccNumber.setBounds(221, 204, 196, 20);
		contentPane.add(txtAccNumber);
		
		
		btnAddAccount.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnAddAccount.setBounds(21, 276, 168, 23);
		contentPane.add(btnAddAccount);
		
		
		btnDeleteAccount.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
			
				switch(acType) {
				
				case "Checking" :
					try {
						if
						(checAcc.remove(accountToDel)){
							JOptionPane.showMessageDialog(contentPane,"The account "+acType+" was eliminated");
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Something wrong Account not eliminated");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					break;
				case "Saving" :
					try {
						
							if
							(savAcc.remove(accountToDel)){
								JOptionPane.showMessageDialog(contentPane,"The account "+acType+" was eliminated");
							}
							else {
								JOptionPane.showMessageDialog(contentPane,"Something wrong Account not eliminated");
							}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					break;
				case "Currency" :
					try {
						if
						(currAcc.remove(accountToDel)){
							JOptionPane.showMessageDialog(contentPane,"The account "+acType+" was eliminated");
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Something wrong Account not eliminated");
						}
						
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					break;
				case "Credit" :
					try {
						
						if
						(crdAcc.remove(accountToDel)){
							JOptionPane.showMessageDialog(contentPane,"The account "+acType+" was eliminated");
						}
						else {
							JOptionPane.showMessageDialog(contentPane,"Something wrong Account not eliminated");
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					break;
					
				
				}
			
			}
		});
		btnDeleteAccount.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnDeleteAccount.setBounds(202, 276, 168, 23);
		contentPane.add(btnDeleteAccount);
		
		btnUpdateCustomerName.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnUpdateCustomerName.setBounds(380, 276, 168, 23);
		contentPane.add(btnUpdateCustomerName);
		
		
		lblcustname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblcustname.setBounds(21, 233, 190, 20);
		contentPane.add(lblcustname);
		
		txtCustName.setColumns(10);
		txtCustName.setBounds(221, 235, 196, 20);
		contentPane.add(txtCustName);
	}
}
