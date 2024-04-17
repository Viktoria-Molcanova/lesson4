CREATE TABLE Customer (
                           customer_id SMALLINT NOT NULL,
                           first_name VARCHAR(255),
                           last_name VARCHAR(255),
                           phone_number VARCHAR(255),
                           district VARCHAR(255),
                           street VARCHAR(255),
                           house VARCHAR(255),
                           apartment VARCHAR(255),
                           payment_method VARCHAR(255),
                           credit BOOLEAN,
                           car_brand VARCHAR(255),
                           PRIMARY KEY (customer_id)
);

INSERT INTO Customer (first_name, last_name, phone_number, district, street, house, apartment, credit, car_brand)
VALUES ('John', 'Doe', '1234567890', 'District A', 'Street 1', 'House 123', 'Apartment 456', true, 'Toyota');
('Иванов', 'Иван', '1234567891', 'District A', 'Street 1', 'House 124', 'Apartment 43', true, 'Toyota');
('Петров', 'Пётр', '1234567810', 'District A', 'Street 1', 'House 113', 'Apartment 45', true, 'Toyota');
;
