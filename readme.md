# Bank Project [Backend]

This is a small educational project, a banking REST API application. It implements simple operations for obtaining and changing data on managers, clients, accounts and client agreements. To access the full functionality of the application, authorization is required.
As the DBMS was selected PostgreSQL.
___

* [ApiDoc Link](http://localhost:8080/swagger-ui/index.html) 
* [JACOCO Link](http://localhost:63342/BankProj/target/site/jacoco/index.html)
___
## Database structure

### Table Account (Bank's accounts table)

| Column name   | Type          | Description                                   |
|---------------|---------------|-----------------------------------------------|
| id            | uuid          | id key of row - unique, not null, primary key |
| client_id     | uuid          | client id                                     |         
| name          | varchar(32)   | a name of account                             |                              
| type          | varchar(32)   | account type                                  |                                   
| status        | varchar(32)   | status of tne account                         |                          
| balance       | numeric(15,2) | balance of the account in currency            | 
| currency_code | varchar(3)    | account currency code                         |                          
| created_at    | timestamp     | timestamp of row creation                     |
| updated_at    | timestamp     | timestamp of last update                      |


### Table Agreement (Bank's - Client's  Agreement table)

| Column name   | Type          | Description                                   |
|---------------|---------------|-----------------------------------------------|
| id            | uuid          | id key of row - unique, not null, primary key |
| account_id    | uuid          | client's account                              | 
| product_id    | uuid          | product id (table product)                    | 
| interest_rate | numeric(6,4)	 | current interest rate of agreement            | 
| status        | varchar(32)   | agreement's status                            | 
| amount        | numeric(15,2) | amount of agreement                           | 
| created_at    | timestamp     | timestamp of row creation                     | 
| updated_at    | timestamp     | timestamp of last update                      | 


### Table Client ( Bank's Clients table )

| Column name | Type         | Description                                   |
|-------------|--------------|-----------------------------------------------|
| id          | uuid         | id key of row - unique, not null, primary key | 
| manager_id  | uuid         | manager id                                    |
| status      | varchar(32)  | client's status                               |
| tax_code    | varchar(32)  | client's TAX code (external ID)               |
| first_name  | varchar(32)  | client's name                                 |
| last_name   | varchar(32)  | client's surname                              |
| email       | varchar(32)  | client's e-mail                               |
| pass        | varchar(60)  | client's password                             |
| address     | varchar(128) | client's address                              |
| phone       | varchar(32)  | client's phone                                |                                
| created_at  | timestamp    | timestamp of row creation                     |
| updated_at  | timestamp    | timestamp of last update                      |


### Table Manager (Bank's managers )

| Column name  | Type         | Description                                   |
|--------------|--------------|-----------------------------------------------|
| 	id          | uuid         | id key of row - unique, not null, primary key | 
| 	first_name  | varchar(32)  | manager's name                                | 
| 	last_name   | varchar(32)  | manager's surname                             | 
| 	status      | varchar(32)  | manager's status                              | 
| 	created_at  | timestamp    | timestamp of row creation                     |
| 	updated_at  | timestamp    | timestamp of last update                      |


### Table Product ( Sets of Bank's available Products)

| Column name   | Type          | Description                                                              |
|---------------|---------------|--------------------------------------------------------------------------|
| id            | uuid          | id key of row - unique, not null, primary key                            |
| manager_id    | uuid          | manager id                                                               |
| name          | varchar(32)   | product's name                                                           |
| status        | varchar(32)   | product's status                                                         |
| currency_code | varchar(3)    | currency of product                                                      |
| interest_rate | numeric(19,4) | interest rate of product                                                 |
| product_limit | numeric(15,2) | limit of credit a product ( 0 - no limit, 0 < - limit which can be used) |
| created_at    | timestamp     | timestamp of row creation                                                |
| updated_at    | timestamp     | timestamp of last update                                                 |


 ### Table Transaction (Bank's Product table) 

| Column name        | Type          | Description                                   |
|--------------------|---------------|-----------------------------------------------|
| 	id                | uuid          | id key of row - unique, not null, primary key | 
| 	debit_account_id  | uuid          | transaction's debit account                   | 
| 	credit_account_id | uuid          | transaction's credit account                  | 
| 	type              | varchar(32)   | transaction type                              | 
| 	amount            | numeric(15,2) | transaction amount in the account currency    | 
| 	description       | varchar(256)  | description of transaction                    | 
| 	created_at        | timestamp     | timestamp of row creation                     | 
