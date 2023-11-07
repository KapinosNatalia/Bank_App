INSERT INTO MANAGERS (ID, FIRST_NAME, LAST_NAME, STATUS, CREATED_AT, UPDATED_AT)
VALUES
    ('523e4567-e89b-12d3-a456-010000000001', 'James', 'Smith', 'WORKING', '2023-01-10', '2023-01-10'),
    ('523e4567-e89b-12d3-a456-010000000002', 'Carlos', 'Garcia', 'WORKING', '2023-03-12', '2023-03-12'),
    ('523e4567-e89b-12d3-a456-010000000003', 'Anna', 'Lee', 'FIRED', '2022-05-06', '2023-02-28'),
    ('523e4567-e89b-12d3-a456-010000000077', 'Anna', 'Lee', 'FIRED', '2022-05-06', '2023-02-28');

INSERT INTO PRODUCTS (ID, MANAGER_ID, NAME, STATUS, CURRENCY_CODE, INTEREST_RATE, PRODUCT_LIMIT, CREATED_AT, UPDATED_AT)
VALUES
    ('523e4567-e89b-12d3-a456-020000000001', '523e4567-e89b-12d3-a456-010000000001', 'Mortgage up to 200.000 EUR', 'ACTIVE', 'EUR', '5.25', '200000', '2022-12-01', '2023-05-01'),
    ('523e4567-e89b-12d3-a456-020000000002', '523e4567-e89b-12d3-a456-010000000001', 'Mortgage up to 500.000 EUR', 'ACTIVE', 'EUR', '3.50', '500000', '2022-12-01', '2023-05-01'),
    ('523e4567-e89b-12d3-a456-020000000003', '523e4567-e89b-12d3-a456-010000000003', 'Deposit up to 100.000 EUR', 'ACTIVE', 'EUR', '10.0', '1000000', '2023-03-01', '2023-03-01'),
    ('523e4567-e89b-12d3-a456-020000000004', '523e4567-e89b-12d3-a456-010000000002', 'Current account EUR', 'ACTIVE', 'EUR', '0.0', '0', '2022-03-01', '2022-03-01');

INSERT INTO CLIENTS (ID, MANAGER_ID, STATUS, TAX_CODE, FIRST_NAME, LAST_NAME, EMAIL, ADDRESS, PHONE, CREATED_AT, UPDATED_AT)
VALUES
    ('523e4567-e89b-12d3-a456-030000000001', '523e4567-e89b-12d3-a456-010000000001', 'REGULAR', '133/8150/8159', 'Lukas', 'Muller', 'lukas.muller@web.de', 'Lansstrasse 81, D-11179 Berlin, Germany', '+49 30 5684962', '2023-01-20 10:35', '2023-01-20 10:35'),
    ('523e4567-e89b-12d3-a456-030000000002', '523e4567-e89b-12d3-a456-010000000002', 'REGULAR', '513/3081/5081', 'Markus', 'Schmidt', 'markus.schmidt@gmx.de', 'Musterstrasse 17, D-80331 Munchen, Germany', '+49 89 1234567', '2023-02-15 10:48', '2023-04-05 17:23'),
    ('523e4567-e89b-12d3-a456-030000000003', '523e4567-e89b-12d3-a456-010000000001', 'REGULAR', '241/5678/9012', 'Lena', 'Weber', 'lena.weber@yahoo.de', 'Hauptstrasse 25, D-50667 Koln, Germany', '+49 221 9876543', '2023-03-02 14:05', '2023-03-02 14:05'),
    ('523e4567-e89b-12d3-a456-030000000004', '523e4567-e89b-12d3-a456-010000000003', 'VIP',     '312/4927/3045', 'Sara', 'Fischer', 'sara.fischer@outlook.de', 'Schillerstrasse 12, D-70173 Stuttgart, Germany', '+49 711 4567890', '2023-04-01 12:33', '2023-04-24 16:07'),
    ('523e4567-e89b-12d3-a456-030000000005', '523e4567-e89b-12d3-a456-010000000001', 'VIP',     '152/8436/7190', 'Laura', 'Klein', 'laura.klein@gmail.com', 'Goetheplatz 5, D-60311 Frankfurt am Main, Germany', '+49 69 8765432', '2023-04-04 15:23', '2023-04-04 15:23');

INSERT INTO ACCOUNTS (ID, CLIENT_ID, NAME, TYPE, STATUS, BALANCE, CURRENCY_CODE, CREATED_AT, UPDATED_AT)
VALUES
    ('523e4567-e89b-12d3-a456-040000000001', '523e4567-e89b-12d3-a456-030000000001', 'DE89 3704 0044 0532 0130 00', 'CURRENT', 'ACTIVE', '5247.27', 'EUR', '2023-01-20 10:35', '2023-10-05 12:02'),
    ('523e4567-e89b-12d3-a456-040000000002', '523e4567-e89b-12d3-a456-030000000001', 'DE12 1001 1001 2620 6568 90', 'CURRENT', 'ACTIVE', '2503.50', 'EUR', '2023-04-17 10:35', '2023-10-05 12:02'),
    ('523e4567-e89b-12d3-a456-040000000003', '523e4567-e89b-12d3-a456-030000000001', 'DE71 5123 0800 0000 6830 99', 'CURRENT', 'ACTIVE', '0.00', 'EUR', '2022-08-06 10:35', '2022-08-06 10:35'),
    ('523e4567-e89b-12d3-a456-040000000004', '523e4567-e89b-12d3-a456-030000000002', 'DE33 2004 1144 0199 9999 00', 'DEPOSIT', 'ACTIVE', '250000.00', 'EUR', '2023-07-25 10:35', '2023-08-08 14:23'),
    ('523e4567-e89b-12d3-a456-040000000005', '523e4567-e89b-12d3-a456-030000000003', 'DE45 7002 0270 0015 7695 53', 'CURRENT', 'ACTIVE', '153256.00', 'EUR', '2022-11-25 10:35', '2023-03-20 10:35'),
    ('523e4567-e89b-12d3-a456-040000000006', '523e4567-e89b-12d3-a456-030000000003', 'DE98 5001 0517 5407 3249 31', 'CURRENT', 'ACTIVE', '0.00', 'EUR', '2022-12-06 10:35', '2022-12-06 10:35'),
    ('523e4567-e89b-12d3-a456-040000000007', '523e4567-e89b-12d3-a456-030000000004', 'DE16 3706 0193 6001 2345 67', 'CURRENT', 'ACTIVE', '5151.15', 'EUR', '2023-02-14 10:35', '2023-08-08 14:23');

INSERT INTO AGREEMENTS (ID, CLIENT_ID, ACCOUNT_ID, PRODUCT_ID, INTEREST_RATE, STATUS, AMOUNT, CREATED_AT, UPDATED_AT)
VALUES
    ('523e4567-e89b-12d3-a456-050000000001', '523e4567-e89b-12d3-a456-030000000001', '523e4567-e89b-12d3-a456-040000000001', '523e4567-e89b-12d3-a456-020000000004', '0.0', 'ACTIVE', '0', '2023-01-20 10:35', '2023-01-20 10:35'),
    ('523e4567-e89b-12d3-a456-050000000002', '523e4567-e89b-12d3-a456-030000000002', '523e4567-e89b-12d3-a456-040000000004', '523e4567-e89b-12d3-a456-020000000003', '10.0', 'ACTIVE', '250000', '2023-07-25 10:35', '2023-07-25 10:35'),
    ('523e4567-e89b-12d3-a456-050000000003', '523e4567-e89b-12d3-a456-030000000003', '523e4567-e89b-12d3-a456-040000000005', '523e4567-e89b-12d3-a456-020000000004', '0.0', 'ACTIVE', '153256.00', '2022-11-25 10:35', '2023-03-20 10:35');

INSERT INTO TRANSACTIONS (ID, DEBIT_ACCOUNT_ID, CREDIT_ACCOUNT_ID, TYPE, AMOUNT, DESCRIPTION, CREATED_AT)
VALUES
    ('523e4567-e89b-12d3-a456-060000000001', '523e4567-e89b-12d3-a456-040000000001', '523e4567-e89b-12d3-a456-040000000002', 'TRANSFER', '500.00', 'transfer between accounts', '2023-10-05 12:02'),
    ('523e4567-e89b-12d3-a456-060000000002', '523e4567-e89b-12d3-a456-040000000001', '523e4567-e89b-12d3-a456-040000000004', 'PAYMENT', '45.08', 'payment for service', '2023-05-12 08:25'),
    ('523e4567-e89b-12d3-a456-060000000003', '523e4567-e89b-12d3-a456-040000000004', '523e4567-e89b-12d3-a456-040000000007', 'DEPOSIT', '1000.00', 'replenishment of the deposit account', '2023-08-08 14:23');