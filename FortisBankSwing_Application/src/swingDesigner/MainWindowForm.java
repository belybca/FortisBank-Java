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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bus.CheckingAccount;
import bus.CreditAccount;
import bus.CurrencyAccount;
import bus.Customer;
import bus.Enum_AccountType;
import bus.Enum_TransactionType;
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;
import bus.Transaction;
import javax.swing.JTable;

import javax.swing.SwingConstants;

public class MainWindowForm {

	public static JFrame frmWelcomeToThe;
	private JTextField txtAmount;
	static CustomerLoginJF login = new CustomerLoginJF();
	static String LoginName="";
	static JLabel lblUsername;
	static JComboBox<String> comboBoxAccounts;
	static Customer cust;
	Transaction transc;
	static CheckingAccount checAcc = null;
	static SavingAccount savAcc = null;
	static CurrencyAccount currAcc = null;
	static CreditAccount crdAcc = null;
	static Transaction trsc = null;
	ArrayList<Transaction> transList = new ArrayList<Transaction>();
	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					
					MainWindowForm window = new MainWindowForm();
					window.frmWelcomeToThe.setVisible(true);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @throws InputMismatchException 
	 * @throws RaiseException 
	 */
	public MainWindowForm() throws ClassNotFoundException, IOException, NumberFormatException, SQLException, RaiseException, InputMismatchException {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void SwitchPanel(JLayeredPane layeredPane,JPanel Jpanel)
	{
		layeredPane.removeAll();
		layeredPane.add(Jpanel);
		layeredPane.repaint();
		layeredPane.validate();
	}
	private void initialize() throws ClassNotFoundException, IOException, NumberFormatException, SQLException, RaiseException, InputMismatchException {
		CustomerLoginJF login = new CustomerLoginJF();
		RegisterCustomer regCus = new RegisterCustomer();
		DisplayCustomerListJF dispCus = new DisplayCustomerListJF();
		SearchCustomer searcCus = new SearchCustomer();
		DeleteCustomerJF delCus = new DeleteCustomerJF();
		ManageCustomerAccountJF manaAcc = new ManageCustomerAccountJF();
		JButton btnTransactions = new JButton("Transaction");
		JButton btnCloseDeleteAccount = new JButton("Close Delete Account");
		
		JLabel lblBalance = new JLabel("balance");
		
		
		lblBalance.setVisible(false);
		btnTransactions.setVisible(false);
		btnCloseDeleteAccount.setVisible(false);
		
		frmWelcomeToThe = new JFrame();
		frmWelcomeToThe.setTitle("Welcome To the Fortis Bank");
		frmWelcomeToThe.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frmWelcomeToThe.setResizable(false);
		frmWelcomeToThe.setBounds(100, 100, 665, 734);
		frmWelcomeToThe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcomeToThe.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(24, 66, 1137, 617);
		frmWelcomeToThe.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel ManagerPanel = new JPanel();
		ManagerPanel.setBackground(Color.ORANGE);
		layeredPane.add(ManagerPanel, "name_338310053725900");
		ManagerPanel.setLayout(null);
		
		JButton btnAddCustomer = new JButton("Add new Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				regCus.setVisible(true);
			
			}
		});
		btnAddCustomer.setFont(new Font("Microsoft Tai Le", Font.BOLD, 13));
		btnAddCustomer.setBounds(109, 121, 185, 70);
		ManagerPanel.add(btnAddCustomer);
		
		JLabel lblManagerOptions = new JLabel("MANAGER OPTIONS");
		lblManagerOptions.setFont(new Font("Microsoft Tai Le", Font.BOLD | Font.ITALIC, 15));
		lblManagerOptions.setBounds(236, 55, 150, 20);
		ManagerPanel.add(lblManagerOptions);
		
		JButton btnDisplayCusList = new JButton("Display Customer List");
		btnDisplayCusList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispCus.setVisible(true);
			
			}
		});
		btnDisplayCusList.setFont(new Font("Microsoft Tai Le", Font.BOLD, 13));
		btnDisplayCusList.setBounds(350, 121, 185, 70);
		ManagerPanel.add(btnDisplayCusList);
		
		JButton btnSearchCustomer = new JButton("Search Customer");
		btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searcCus.setVisible(true);
				
				
			
			}
		});
		btnSearchCustomer.setFont(new Font("Microsoft Tai Le", Font.BOLD, 13));
		btnSearchCustomer.setBounds(109, 340, 185, 70);
		ManagerPanel.add(btnSearchCustomer);
		
		JButton btnDeleteCustomer = new JButton("Delete Customer");
		btnDeleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				delCus.setVisible(true);
			}
		});
		btnDeleteCustomer.setFont(new Font("Microsoft Tai Le", Font.BOLD, 13));
		btnDeleteCustomer.setBounds(350, 340, 185, 70);
		ManagerPanel.add(btnDeleteCustomer);
		
		JButton btnExit_1 = new JButton("EXIT");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWelcomeToThe.setVisible(false);
				frmWelcomeToThe.dispose();
			}
		});
		btnExit_1.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExit_1.setBounds(422, 480, 93, 36);
		ManagerPanel.add(btnExit_1);
		
		JButton btnManageCustAcc = new JButton("Manage Customer Accounts");
		btnManageCustAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				manaAcc.setVisible(true);
			}
		});
		btnManageCustAcc.setFont(new Font("Microsoft Tai Le", Font.BOLD, 13));
		btnManageCustAcc.setBounds(215, 234, 225, 70);
		ManagerPanel.add(btnManageCustAcc);
		
		//CUSTOMER PANEL
		
		JPanel CustomerPanel = new JPanel();
		
		CustomerPanel.setBackground(Color.ORANGE);
		layeredPane.add(CustomerPanel, "name_338315393757800");
		CustomerPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Microsoft Tai Le", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(80, 11, 82, 20);
		CustomerPanel.add(lblNewLabel);
		
		lblUsername = new JLabel("Client name here");
		lblUsername.setFont(new Font("Microsoft Tai Le", Font.BOLD | Font.ITALIC, 15));
		lblUsername.setBounds(172, 11, 142, 20);
		lblUsername.setText("Hello");
		CustomerPanel.add(lblUsername);
		
		JLabel lblSelectAc = new JLabel("Select your Account :");
		lblSelectAc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectAc.setBounds(80, 102, 145, 20);
		CustomerPanel.add(lblSelectAc);
		String[] columnNames= {"transNumber","transDesc","transDate","transType","transAmount","Account Type"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		//model.addRow(new Object[] {"transNumber","transDesc","transDate","transType","transAmount"});
		JTable table = new JTable();
		table.setModel(model);
		table.setBounds(80, 408, 466, 159);
		JScrollPane scrollPane = new JScrollPane(table); 
		scrollPane.setBounds(80, 408, 466, 159);	
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		CustomerPanel.add(scrollPane);

		
		comboBoxAccounts = new JComboBox<String>();
		
		
		comboBoxAccounts.setBounds(235, 103, 188, 20);
		CustomerPanel.add(comboBoxAccounts);
		
		JLabel lblSelectOperation = new JLabel("Select Operation :");
		lblSelectOperation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSelectOperation.setBounds(80, 178, 132, 20);
		CustomerPanel.add(lblSelectOperation);
		JLabel lblOperationType = new JLabel("The selected operation type will be displayed in this label");
		lblOperationType.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnOtherOperation = new JButton("Transaction");
		JComboBox<Enum_TransactionType> comboBoxOperations = new JComboBox<Enum_TransactionType>();
		JButton btnManageAccounts = new JButton("Manage Accounts");
		
		JLabel lblEnterAmont = new JLabel("Enter Amount :");
		txtAmount = new JTextField();
	
		
		btnManageAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				lblSelectOperation.setVisible(false);
				comboBoxOperations.setVisible(false);
				lblSelectOperation.setVisible(false);
				lblOperationType.setVisible(false);
				btnOtherOperation.setVisible(false);
				lblEnterAmont.setVisible(false);
				txtAmount.setVisible(false);
				btnCloseDeleteAccount.setVisible(true);
				
				
				
				btnTransactions.setVisible(true);
			}
		});
		
		
		
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSelectOperation.setVisible(true);
				comboBoxOperations.setVisible(true);
				lblSelectOperation.setVisible(true);
				lblOperationType.setVisible(true);
				btnOtherOperation.setVisible(true);
				lblEnterAmont.setVisible(true);
				txtAmount.setVisible(true);
				btnCloseDeleteAccount.setVisible(false);
				btnTransactions.setVisible(false);
				
			}
		});
		btnTransactions.setBackground(Color.ORANGE);
		btnTransactions.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnTransactions.setBounds(303, 42, 110, 20);
		CustomerPanel.add(btnTransactions);
		
		
		btnManageAccounts.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnManageAccounts.setBackground(Color.ORANGE);
		btnManageAccounts.setBounds(444, 43, 142, 20);
		CustomerPanel.add(btnManageAccounts);
		
		
		comboBoxOperations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblOperationType.setText(comboBoxOperations.getSelectedItem().toString());
				btnOtherOperation.setText(comboBoxOperations.getSelectedItem().toString());
			
			}
		});
		comboBoxOperations.setBounds(235, 179, 188, 20);
		for (Enum_TransactionType type : Enum_TransactionType.values())
		{
			comboBoxOperations.addItem(type);
		}
		CustomerPanel.add(comboBoxOperations);
		
		
		
		lblOperationType.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOperationType.setBounds(217, 213, 188, 20);
		CustomerPanel.add(lblOperationType);
		lblOperationType.setText(comboBoxOperations.getSelectedItem().toString());
		
	
		lblEnterAmont.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterAmont.setBounds(80, 243, 132, 20);
		CustomerPanel.add(lblEnterAmont);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(235, 244, 156, 20);
		CustomerPanel.add(txtAmount);
		
		
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBalance.setBounds(80, 292, 343, 20);
		CustomerPanel.add(lblBalance);
		
		JLabel lblTransactions = new JLabel("Transactions");
		lblTransactions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTransactions.setBounds(80, 377, 275, 20);
		CustomerPanel.add(lblTransactions);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWelcomeToThe.setVisible(false);
				frmWelcomeToThe.dispose();
			
			}
		});
		btnExit.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnExit.setBounds(356, 583, 89, 23);
		CustomerPanel.add(btnExit);
		
		
		btnOtherOperation.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				
				lblBalance.setVisible(true);
				boolean deposit;
				
				Double amt;


				String accType = comboBoxAccounts.getSelectedItem().toString();
				String operationtype=comboBoxOperations.getSelectedItem().toString();
				amt=Double.parseDouble(txtAmount.getText());
				LocalDate date=LocalDate.now();
				int withdraw;			
				
				trsc = new Transaction();
				switch(accType)
				{
					case "Checking" :
						if(operationtype=="Deposit") {
						deposit=checAcc.Deposit(amt);
						
						if(deposit) {
							
						try {
							checAcc.update(checAcc);
							trsc.setTransDesc(operationtype+" for "+amt.toString());
							trsc.setTransDate(date);
							trsc.setTransType(operationtype);
							trsc.setTransAmount(amt);
							trsc.setAccountType(Enum_AccountType.Checking.toString());
							trsc.setclienNb(cust.getcId());
							try {
								Transaction.add(trsc);
							} catch (RaiseException | InputMismatchException e1) {
							
								e1.printStackTrace();
							}
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit susccecfull");
						lblBalance.setText("New account Balance is : "+checAcc.getaBalance()+" CAD");
						
							}
						else {
							JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit refuse");
						}
						}
						else {
							withdraw=checAcc.Withdraw(amt);
							if(withdraw==2) {
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount bigger than 20 CAD");
								}
								else if(withdraw==1)
								{
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount lower than 500 CAD");

								}
								else if(withdraw==-1)
								{
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter a multiple of 20");

								}
								else if(withdraw==-2)
								{
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Insuficient Balance");
								}
								else {
									try {
										checAcc.update(checAcc);
										trsc.setTransDesc(operationtype+" for "+amt.toString());
										trsc.setTransDate(date);
										trsc.setTransType(operationtype);
										trsc.setTransAmount(amt);
										trsc.setAccountType(Enum_AccountType.Checking.toString());
										trsc.setclienNb(cust.getcId());
										try {
											Transaction.add(trsc);
										} catch (RaiseException | InputMismatchException e1) {
											e1.printStackTrace();
										}
									} catch (SQLException e1) {
										
										e1.printStackTrace();
									}
								lblBalance.setText("New account Balance is : "+checAcc.getaBalance()+" CAD");


								}
							
						}
						
						
								
						break;
					case "Saving" :
						if(operationtype=="Deposit") {
							deposit=savAcc.Deposit(amt);
							
							if(deposit) {
								
							try {
								savAcc.update(savAcc);
								trsc.setTransDesc(operationtype+" for "+amt.toString());
								trsc.setTransDate(date);
								trsc.setTransType(operationtype);
								trsc.setTransAmount(amt);
								trsc.setAccountType(Enum_AccountType.Saving.toString());
								trsc.setclienNb(cust.getcId());
								try {
									Transaction.add(trsc);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
							
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit susccecfull");
							lblBalance.setText("New account Balance is : "+savAcc.getaBalance()+" CAD");
							
								}
							else {
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit refuse");
							}
							}
							else {
								withdraw=savAcc.Withdraw(amt);
								if(withdraw==2) {
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount bigger than 20 CAD");
									}
									else if(withdraw==1)
									{
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount lower than 500 CAD");

									}
									else if(withdraw==-1)
									{
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter a multiple of 20");

									}
									else if(withdraw==-2)
									{
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Insuficient Balance");
									}
									else {
									lblBalance.setText("New account Balance is : "+savAcc.getaBalance()+" CAD");
									try {
										savAcc.update(savAcc);
										savAcc.update(savAcc);
										trsc.setTransDesc(operationtype+" for "+amt.toString());
										trsc.setTransDate(date);
										trsc.setTransType(operationtype);
										trsc.setTransAmount(amt);
										trsc.setAccountType(Enum_AccountType.Saving.toString());
										trsc.setclienNb(cust.getcId());
										try {
											Transaction.add(trsc);
										} catch (RaiseException | InputMismatchException e1) {
											e1.printStackTrace();
										}
									} catch (SQLException e1) {
										
										e1.printStackTrace();
									}

									}
								
							}
							
							
							
						break;
					case "Credit" :
						if(operationtype=="Deposit") {
							deposit=crdAcc.Deposit(amt);
							
							if(deposit) {
								
							try {
								crdAcc.update(crdAcc);
								trsc.setTransDesc(operationtype+" for "+amt.toString());
								trsc.setTransDate(date);
								trsc.setTransType(operationtype);
								trsc.setTransAmount(amt);
								trsc.setAccountType(Enum_AccountType.Credit.toString());
								trsc.setclienNb(cust.getcId());
								try {
									Transaction.add(trsc);
								} catch (RaiseException | InputMismatchException e1) {
									e1.printStackTrace();
								}
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit susccecfull");
							lblBalance.setText("New account Balance is : "+crdAcc.getaBalance()+" CAD");
							
								}
							else {
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit refuse");
							}
							}
							else {
								withdraw=crdAcc.Withdraw(amt);
								if(withdraw==2) {
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount bigger than 20 CAD");
									}
									else if(withdraw==1)
									{
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount lower than 500 CAD");

									}
									else if(withdraw==-1)
									{
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter a multiple of 20");

									}
									else if(withdraw==-2)
									{
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Insuficient Balance");
									}
									else {
									lblBalance.setText("New account Balance is : "+crdAcc.getaBalance()+" CAD");
									try {
										
										crdAcc.update(crdAcc);
										trsc.setTransDesc(operationtype+" for "+amt.toString());
										trsc.setTransDate(date);
										trsc.setTransType(operationtype);
										trsc.setTransAmount(amt);
										trsc.setAccountType(Enum_AccountType.Credit.toString());
										trsc.setclienNb(cust.getcId());
										try {
											Transaction.add(trsc);
										} catch (RaiseException | InputMismatchException e1) {
											e1.printStackTrace();
										}
									} catch (SQLException e1) {
										
										e1.printStackTrace();
									}

									}
							}
							case "Currency" :
								if(operationtype=="Deposit") {
									deposit=currAcc.Deposit(amt);
									
									if(deposit) {
										
									try {
									
										currAcc.update(currAcc);
										trsc.setTransDesc(operationtype+" for "+amt.toString());
										trsc.setTransDate(date);
										trsc.setTransType(operationtype);
										trsc.setTransAmount(amt);
										trsc.setAccountType(Enum_AccountType.Currency.toString());
										trsc.setclienNb(cust.getcId());
										try {
											Transaction.add(trsc);
										} catch (RaiseException | InputMismatchException e1) {
											e1.printStackTrace();
										}
									} catch (SQLException e1) {
									
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit susccecfull");
									lblBalance.setText("New account Balance is : "+currAcc.getaBalance()+" CAD");
									
										}
									else {
										JOptionPane.showMessageDialog(frmWelcomeToThe,"Deposit refuse");
									}
									}
									else {
										withdraw=currAcc.Withdraw(amt);
										if(withdraw==2) {
											JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount bigger than 20 CAD");
											}
											else if(withdraw==1)
											{
											JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter amount lower than 500 CAD");

											}
											else if(withdraw==-1)
											{
											JOptionPane.showMessageDialog(frmWelcomeToThe,"Please Enter a multiple of 20");

											}
											else if(withdraw==-2)
											{
											JOptionPane.showMessageDialog(frmWelcomeToThe,"Insuficient Balance");
											}
											else {
											lblBalance.setText("New account Balance is : "+currAcc.getaBalance()+" CAD");
											try {
												currAcc.update(currAcc);
												trsc.setTransDesc(operationtype+" for "+amt.toString());
												trsc.setTransDate(date);
												trsc.setTransType(operationtype);
												trsc.setTransAmount(amt);
												trsc.setAccountType(Enum_AccountType.Currency.toString());
												trsc.setclienNb(cust.getcId());
												try {
													Transaction.add(trsc);
												} catch (RaiseException | InputMismatchException e1) {
													e1.printStackTrace();
												}
											} catch (SQLException e1) {
												
												e1.printStackTrace();
											}

											}
								
							
							
							
							
						break;	
				}
	
				}
				
				txtAmount.setText("");
				
				}
		});
		btnOtherOperation.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnOtherOperation.setBounds(401, 244, 110, 20);
		CustomerPanel.add(btnOtherOperation);
		
		JButton btnDisplayTransactions = new JButton("Display Transactions");
		btnDisplayTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try {
					transList=Transaction.searchbyCustomerNumber(cust.getcId());
					
					if(transList.size()==0) {
					
					lblTransactions.setText("There is not transactions to show");
					}
					else if(transList.size()==1){
						lblTransactions.setText("You have one transaction");
					}
					else {
						lblTransactions.setText("You have "+ transList.size() +" transactions");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
				model.fireTableDataChanged();
				while(model.getRowCount() >0) {
					model.removeRow(0);
					
				}
				if(transList != null)
				{
					
					
					for(Transaction tr : transList) {
						model.addRow(new Object[] {tr.getTransNumber(),tr.getTransDesc(),tr.getTransDate(),tr.getTransType(),tr.getTransAmount(),tr.getAccountType()});
						
					}
				}
				
				
			}
		});
		btnDisplayTransactions.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnDisplayTransactions.setBounds(205, 346, 232, 20);
		CustomerPanel.add(btnDisplayTransactions);
		
		JLabel lblWelcoming = new JLabel("What do you want to do today?");
		lblWelcoming.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcoming.setBounds(80, 43, 213, 20);
		CustomerPanel.add(lblWelcoming);
		
		
		btnCloseDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String accType = comboBoxAccounts.getSelectedItem().toString();
				double balance;
				switch(accType)
				{
					case "Checking" :
						JOptionPane.showMessageDialog(frmWelcomeToThe,"Sorry!, this account can only be removed by one of our representatives");
						break;
					case "Saving" :
						balance=savAcc.getaBalance();
						//JOptionPane.showMessageDialog(frmWelcomeToThe,balance);
						if(balance==0) {
							try {
								SavingAccount.remove(savAcc.getaNumber());
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Account Deleted");
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(frmWelcomeToThe,"Careful! you must whitdraw all your money before closing");
						}
						
						break;
					case "Credit" :
						
						balance=crdAcc.getaBalance();
						if(balance==0) {
							try {
								SavingAccount.remove(crdAcc.getaNumber());
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Account Deleted");
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(frmWelcomeToThe,"Careful! you must whitdraw all your money before closing");
						}
						
						break;
					case "Currency" :
						
						balance=currAcc.getaBalance();
						if(balance==0) {
							try {
								SavingAccount.remove(currAcc.getaNumber());
								JOptionPane.showMessageDialog(frmWelcomeToThe,"Account Deleted");
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showMessageDialog(frmWelcomeToThe,"Careful! you must whitdraw all your money before closing");
						}
						
							
						break;	
				}
	
			
			}
		});
		btnCloseDeleteAccount.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnCloseDeleteAccount.setBounds(247, 148, 161, 20);
		CustomerPanel.add(btnCloseDeleteAccount);
	
		
		
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SwitchPanel(layeredPane,CustomerPanel);
				login.setResizable(false);
				login.setVisible(true);
				login.setDefaultCloseOperation(0);
				login.setMinimumSize(null);
				
			}
		});
		btnCustomer.setBackground(Color.BLACK);
		btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCustomer.setForeground(Color.WHITE);
		btnCustomer.setBounds(238, 28, 213, 38);
		frmWelcomeToThe.getContentPane().add(btnCustomer);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwitchPanel(layeredPane,ManagerPanel);
			}
		});
		btnManager.setForeground(Color.WHITE);
		btnManager.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnManager.setBackground(Color.BLACK);
		btnManager.setBounds(24, 28, 213, 38);
		frmWelcomeToThe.getContentPane().add(btnManager);
	}
}
