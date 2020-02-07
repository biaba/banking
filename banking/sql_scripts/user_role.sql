--
-- Table structure for table `user`
--

USE bank_db;

CREATE TABLE user(
id int(10) NOT NULL AUTO_INCREMENT ,
user_name varchar(43) NOT NULL,
password char(80) NOT NULL,
first_name varchar(43) NOT NULL,
last_name varchar(43) NOT NULL,
personal_id int(10) NOT NULL,
email varchar(43),

PRIMARY KEY (id));
--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: test123
--
INSERT INTO user (user_name, password, first_name, last_name, personal_id, email)
VALUES
('adina', '$2a$10$nyYO.HPfkjd3lDkvgpr8y.APwcxXzUX6/96EeUTdY8tTH35k3Sp8a', 'adina', 'mio', 355, 'adina@inbox.lv'), 
('rene', '$2a$10$nyYO.HPfkjd3lDkvgpr8y.APwcxXzUX6/96EeUTdY8tTH35k3Sp8a', 'rene', 'katko', 460, 'rene@inbox.lv');

--
-- Table structure for table `role`
--

CREATE TABLE role(
id int(10) NOT NULL AUTO_INCREMENT,
name varchar(43) NOT NULL,

PRIMARY KEY (id));

--
-- Dumping data for table `roles`
--

INSERT INTO role (name)
VALUES
('ROLE_CUSTOMER'), ('ROLE_EMPLOYEE');

--
-- Table structure for table `users_roles`
--
CREATE TABLE users_roles(
user_id int(10) NOT NULL,
role_id int(10) NOT NULL,

PRIMARY KEY (user_id, role_id)

CONSTRAINT user_fk
FOREIGN KEY (user_id) REFERENCES user(id)
ON DELETE NO ACTION ON UPDATE NO ACTION,

CONSTRAINT role_fk
FOREIGN KEY (role_id) REFERENCES role(id)
ON DELETE NO ACTION ON UPDATE NO ACTION,);
--
-- Dumping data for table `users_roles`
--
INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 2), (2,1);