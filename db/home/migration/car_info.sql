CREATE TABLE car_info (
                          car_id SMALLINT NOT NULL,
                          brand VARCHAR(255),
                          model VARCHAR(255),
                          year INT,
                          color VARCHAR(50),
                          PRIMARY KEY (car_id)
);

INSERT INTO car_info (car_id, brand, model, year, color)
VALUES (1, 'Toyota', 'Corolla', 2020, 'Red'),
       (2, 'Honda', 'Civic', 2019, 'Blue'),
       (3, 'Ford', 'Mustang', 2021, 'Yellow');