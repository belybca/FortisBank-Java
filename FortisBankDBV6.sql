--Fortis BAnk Script

connect sys/sys as sysdba;

DROP USER fortisBankDB CASCADE;

CREATE USER fortisBankDB identified by 123;
GRANT connect , resource, create view to fortisBankDB;
connect fortisBankDB/123;

CREATE TABLE CUSTOMER(cust_id NUMBER(6),
cust_number VARCHAR2(10) NOT NULL,
cust_name VARCHAR2(50) NOT NULL,
cust_pin VARCHAR2(10),
status VARCHAR(15),
CONSTRAINT customer_cust_id_pk PRIMARY KEY(cust_id));

CREATE SEQUENCE seq_customer
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;


CREATE TABLE CheckingAccount
(chk_acc_id NUMBER(6),
chk_acc_no VARCHAR2(10) NOT NULL,
chk_acc_type VARCHAR2(15) NOT NULL,
chk_acc_open_date DATE, 
chk_acc_balance NUMBER(15,2),
chk_acc_status VARCHAR2(15),
transaction_fees NUMBER(3),
cust_id NUMBER(6),
CONSTRAINT checking_acc_id_pk PRIMARY KEY (chk_acc_id),
CONSTRAINT acc_cust_id_fk FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE);

CREATE SEQUENCE seq_checking_acc
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE CreditAccount
(cr_acc_id NUMBER(6),
cr_acc_no VARCHAR2(10) NOT NULL,
cr_acc_type VARCHAR2(15) NOT NULL,
cr_acc_open_date DATE, 
cr_acc_balance NUMBER(15,2),
cr_acc_status VARCHAR2(15),
interest_rate NUMBER(3),
cust_id NUMBER(6),
CONSTRAINT credit_acc_id_pk PRIMARY KEY (cr_acc_id),
CONSTRAINT cdit_cust_id_fk FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE);

CREATE SEQUENCE seq_credit_acc
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE CurrencyAccount
(curr_acc_id NUMBER(6),
curr_acc_no VARCHAR2(10) NOT NULL,
curr_acc_type VARCHAR2(15) NOT NULL,
curr_acc_open_date DATE, 
curr_acc_balance NUMBER(15,2),
curr_acc_status VARCHAR2(15),
comision NUMBER(3),
convertion_rate NUMBER(4,2),
cust_id NUMBER(6),
CONSTRAINT currency_acc_id_pk PRIMARY KEY (curr_acc_id),
CONSTRAINT curr_cust_id_fk FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE);

CREATE SEQUENCE seq_currency_acc
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;


CREATE TABLE SavingAccount
(saving_acc_id NUMBER(6),
saving_acc_no VARCHAR2(10) NOT NULL,
saving_acc_type VARCHAR2(15) NOT NULL,
saving_acc_open_date DATE, 
saving_acc_balance NUMBER(15,2),
saving_acc_status VARCHAR2(15),
anualInterest Number(3),
anualGain Number(3,2),
extraFees Number(3,2),
cust_id NUMBER(6),
CONSTRAINT saving_acc_id_pk PRIMARY KEY (saving_acc_id),
CONSTRAINT sav_cust_id_fk FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE);

CREATE SEQUENCE seq_saving_acc
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;




CREATE TABLE TRANSACTION(
trans_id NUMBER(10),
trans_desc VARCHAR2(30),
trans_date DATE,
trans_type VARCHAR2(30),
trans_amount NUMBER(10,2),
trans_accType VARCHAR2(30),
cust_id NUMBER(6),
cust_number VARCHAR2(10),
CONSTRAINT trans_trans_id_pk PRIMARY KEY(trans_id),
CONSTRAINT tran_cust_id_fk FOREIGN KEY (cust_id) REFERENCES customer(cust_id) ON DELETE CASCADE);

CREATE SEQUENCE seq_transaction
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;




--- insert statements for customer and accounts

insert into customer values(seq_customer.nextval,'cl001','Bill Gates','windows','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac001','Checking',to_date('11/30/1989', 'mm/dd/yyyy'),820000.0,'active',0.21,seq_customer.currval);
insert into SavingAccount values(seq_saving_acc.nextval,'ac008','Saving',to_date('11/3/1980', 'mm/dd/yyyy'),850000.0,'active',0.21,0.18,0.05,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl002','Steve Jobs','apple','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac002','Checking',to_date('11/3/1980', 'mm/dd/yyyy'),340000.0,'active',0.21,seq_customer.currval);
insert into CreditAccount values(seq_credit_acc.nextval,'ac0021','Credit',to_date('11/13/1970', 'mm/dd/yyyy'),650000.0,'active',0.25,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl003','You Pan','poutine','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac003','Checking',to_date('5/4/1988', 'mm/dd/yyyy'),40000.0,'active',0.21,seq_customer.currval);
insert into SavingAccount values(seq_saving_acc.nextval,'ac009','Saving',to_date('5/4/1988', 'mm/dd/yyyy'),38000.0,'active',0.21,0.18,0.05,seq_customer.currval);
insert into CurrencyAccount values(seq_currency_acc.nextval,'ac0013','Currency',to_date('5/4/1998', 'mm/dd/yyyy'),38000.0,'active',0.075,0.18,seq_customer.currval);
insert into CreditAccount values(seq_credit_acc.nextval,'ac0018','Credit',to_date('11/3/1999', 'mm/dd/yyyy'),150000.0,'active',0.25,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl004','Ana Gonzalez','anita','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac004','Checking',to_date('12/05/2000', 'mm/dd/yyyy'),96000.0,'active',0.21,seq_customer.currval);
insert into CurrencyAccount values(seq_currency_acc.nextval,'ac0012','Currency',to_date('01/02/2001', 'mm/dd/yyyy'),150000.0,'active',0.075,0.18,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl005','Romulo Gallegos','barbara','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac005','Checking',to_date('2/25/2000', 'mm/dd/yyyy'),30000.0,'active',0.21,seq_customer.currval);
insert into CurrencyAccount values(seq_currency_acc.nextval,'ac0014','Currency',to_date('11/13/1970', 'mm/dd/yyyy'),100000.0,'active',0.075,0.18,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl006','Rames Bhoi','rameswari','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac006','Checking',to_date('11/30/2008', 'mm/dd/yyyy'),5000.0,'active',0.21,seq_customer.currval);
insert into SavingAccount values(seq_saving_acc.nextval,'ac0011','Saving',to_date('2/25/2000', 'mm/dd/yyyy'),500000.0,'active',0.21,0.18,0.05,seq_customer.currval);
insert into CreditAccount values(seq_credit_acc.nextval,'ac0020','Credit',to_date('5/4/1998', 'mm/dd/yyyy'),87000.0,'active',0.25,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl006','Albelis Becea','albelis','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac007','Checking',to_date('11/30/2010', 'mm/dd/yyyy'),8000.0,'active',0.21,seq_customer.currval);
insert into SavingAccount values(seq_saving_acc.nextval,'ac0010','Saving',to_date('11/13/1990', 'mm/dd/yyyy'),100000.0,'active',0.21,0.18,0.05,seq_customer.currval);

insert into customer values(seq_customer.nextval,'cl006','Andres Fuentes','fuent','active');
insert into CheckingAccount values(seq_checking_acc.nextval,'ac009','Checking',to_date('11/30/2010', 'mm/dd/yyyy'),88000.0,'active',0.21,seq_customer.currval);
insert into CreditAccount values(seq_credit_acc.nextval,'ac0021','Credit',to_date('5/4/1998', 'mm/dd/yyyy'),57000.0,'active',0.25,seq_customer.currval);
commit;








