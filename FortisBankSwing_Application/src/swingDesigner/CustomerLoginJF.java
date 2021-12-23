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
import bus.InputMismatchException;
import bus.RaiseException;
import bus.SavingAccount;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.ParseException;

import java.awt.event.ActionEvent;

public class CustomerLoginJF extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textClientNumber;
	private JTextField textField_1;
	private static CustomerLoginJF frame = new CustomerLoginJF();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	public CustomerLoginJF() {
		setBackground(Color.ORANGE);
		setForeground(Color.ORANGE);
		setTitle("Fortis Bank");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 374, 281);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblclientnb = new JLabel("Client Number :");
		lblclientnb.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		lblclientnb.setBounds(29, 77, 109, 27);
		contentPane.add(lblclientnb);
		
		JLabel lblUserPin = new JLabel("User PIN :");
		lblUserPin.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 14));
		lblUserPin.setBounds(29, 133, 97, 27);
		contentPane.add(lblUserPin);
		
		JLabel lblWelcomeToFortis = new JLabel("WELCOME TO FORTIS BANK");
		lblWelcomeToFortis.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 15));
		lblWelcomeToFortis.setBounds(74, 24, 206, 27);
		contentPane.add(lblWelcomeToFortis);
		
		textClientNumber = new JTextField();
		textClientNumber.setBounds(148, 80, 159, 20);
		contentPane.add(textClientNumber);
		textClientNumber.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(148, 136, 159, 20);
		contentPane.add(textField_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId=textClientNumber.getText();
				String password = textField_1.getText();
				if(userId == "" || userId.compareTo("")==0 || password == "" ||password.compareTo("")==0)
				{
					JOptionPane.showMessageDialog(contentPane,"Username or password should not be null!!");
				}
				else {
					Customer cust = null;
					try {
						cust = Customer.Login(userId,password);
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(contentPane,e2);
					} catch (RaiseException e1) {
					
						e1.printStackTrace();
					} catch (InputMismatchException e1) {
						
						e1.printStackTrace();
					}
					if (cust == null)
					{
						JOptionPane.showMessageDialog(contentPane,"Invalid usedname or password!!");
						
					}
					else
					{
						System.out.println(cust.getcName());
						MainWindowForm.lblUsername.setText(cust.getcName());
						MainWindowForm.cust=cust;
						try {
							CheckingAccount chkAcc = CheckingAccount.searchAcccountByCustomer(userId);
							
							if(chkAcc != null)
							{
								System.out.println(chkAcc.toString());
								MainWindowForm.comboBoxAccounts.addItem(chkAcc.getaType());
								MainWindowForm.checAcc=chkAcc;
							}
							CurrencyAccount currAcc = CurrencyAccount.searchAcccountByCustomer(userId);
							if(currAcc != null)
							{
								System.out.println(currAcc.toString());
								MainWindowForm.comboBoxAccounts.addItem(currAcc.getaType());
								MainWindowForm.currAcc=currAcc;
							}
							SavingAccount savAcc = SavingAccount.searchAcccountByCustomer(userId);
							if(savAcc != null)
							{
								System.out.println(savAcc.toString());
								MainWindowForm.comboBoxAccounts.addItem(savAcc.getaType());
								MainWindowForm.savAcc=savAcc;
							}
							CreditAccount creditAcc = CreditAccount.searchAcccountByCustomer(userId);
							if(creditAcc != null)
							{
								System.out.println(creditAcc.toString());
								MainWindowForm.comboBoxAccounts.addItem(creditAcc.getaType());
								MainWindowForm.crdAcc=creditAcc;
							}
							
						} catch (SQLException | ParseException e1) {
						
							e1.printStackTrace();
						} catch (RaiseException e1) {
							
							e1.printStackTrace();
						} catch (InputMismatchException e1) {
							
							e1.printStackTrace();
						}
//						
						dispose();
						
						
					}
				}
				
			}
		});
		btnLogin.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnLogin.setBounds(191, 194, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		btnCancel.setBounds(62, 194, 89, 23);
		contentPane.add(btnCancel);
	}
}
