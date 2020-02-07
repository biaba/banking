--
-- Table structure for table 'transaction' 
--


CREATE TABLE transaction (
id int(11) NOT NULL,
credit_account_id int(11) NOT NULL,
debit_account_id int(11) NOT NULL,
amount int(11) NOT NULL,
date date NOT NULL,
status varchar(43) NOT NULL,
PRIMARY KEY (id),
CONSTRAINT 
FOREIGN KEY (credit_account_id)
REFERENCES account (id),
FOREIGN KEY (debit_account_id)
REFERENCES account (id)

);

--
-- Table structure for table `account`
--
	
CREATE TABLE account (
  id int(11) NOT NULL AUTO_INCREMENT,
  type varchar(50) NOT NULL,
  balance int(11),
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `user_account`
--
CREATE TABLE user_account(
user_id int(10) NOT NULL,
account_id int(10) NOT NULL,

PRIMARY KEY (user_id, account_id)

CONSTRAINT 
FOREIGN KEY (user_id) REFERENCES user(id)
ON DELETE NO ACTION ON UPDATE NO ACTION,

CONSTRAINT
FOREIGN KEY (account_id) REFERENCES account(id)
ON DELETE NO ACTION ON UPDATE NO ACTION,);