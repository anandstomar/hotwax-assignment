CREATE TABLE Customer (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE Contact_Mech (
    contact_mech_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL,
    street_address VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100),
    CONSTRAINT fk_customer 
        FOREIGN KEY (customer_id) 
        REFERENCES Customer(customer_id)
);

CREATE TABLE Product (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    color VARCHAR(30),
    size VARCHAR(10)
);

CREATE TABLE Order_Header (
    order_id SERIAL PRIMARY KEY,
    order_date DATE NOT NULL,
    customer_id INT NOT NULL,
    shipping_contact_mech_id INT NOT NULL,
    billing_contact_mech_id INT NOT NULL,
    CONSTRAINT fk_order_customer 
        FOREIGN KEY (customer_id) 
        REFERENCES Customer(customer_id),
    CONSTRAINT fk_shipping_mech 
        FOREIGN KEY (shipping_contact_mech_id) 
        REFERENCES Contact_Mech(contact_mech_id),
    CONSTRAINT fk_billing_mech 
        FOREIGN KEY (billing_contact_mech_id) 
        REFERENCES Contact_Mech(contact_mech_id)
);

CREATE TABLE Order_Item (
    order_item_seq_id SERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_order_item_header 
        FOREIGN KEY (order_id) 
        REFERENCES Order_Header(order_id) 
        ON DELETE CASCADE,
    CONSTRAINT fk_order_item_product 
        FOREIGN KEY (product_id) 
        REFERENCES Product(product_id)
);