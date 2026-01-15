INSERT INTO Customer (first_name, last_name) VALUES ('John', 'Doe');
INSERT INTO Customer (first_name, last_name) VALUES ('Jane', 'Smith');

INSERT INTO Contact_Mech (customer_id, street_address, city, state, postal_code, phone_number, email)
VALUES (1, '1600 Amphitheatre Parkway', 'Mountain View', 'CA', '94043', '(650) 253-0000', 'john.doe@example.com');

INSERT INTO Contact_Mech (customer_id, street_address, city, state, postal_code, phone_number, email)
VALUES (1, '1 Infinite Loop', 'Cupertino', 'CA', '95014', '(408) 996-1010', 'john.doe@work.com');

INSERT INTO Contact_Mech (customer_id, street_address, city, state, postal_code, phone_number, email)
VALUES (2, '350 Fifth Avenue', 'New York', 'NY', '10118', '(212) 736-3100', 'jane.smith@example.com');

INSERT INTO Product (product_name, color, size) VALUES ('T-Shirt', 'Red', 'M');
INSERT INTO Product (product_name, color, size) VALUES ('Jeans', 'Blue', '32');
INSERT INTO Product (product_name, color, size) VALUES ('Sneakers', 'White', '9');
INSERT INTO Product (product_name, color, size) VALUES ('Jacket', 'Black', 'L');
INSERT INTO Product (product_name, color, size) VALUES ('Hat', 'Green', 'One Size');