CREATE SEQUENCE IF NOT EXISTS customer_seq START 1;
CREATE TABLE IF NOT EXISTS Customer (
    id BIGINT DEFAULT nextval('customer_seq') PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(10) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS food_seq START 1;
CREATE TABLE IF NOT EXISTS Food(
    id BIGINT DEFAULT nextval('food_seq') PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price REAL NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS payment_seq START 1;

CREATE TABLE IF NOT EXISTS Payment(
    id BIGINT DEFAULT nextval('payment_seq') PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    amount REAL NOT NULL,
    method VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,
    date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)  ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE SEQUENCE IF NOT EXISTS delivery_seq START 1;
CREATE TABLE IF NOT EXISTS Delivery(
    id BIGINT DEFAULT nextval('delivery_seq') PRIMARY KEY,
    status VARCHAR(50) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS order_seq START 1;
CREATE TABLE IF NOT EXISTS Orders(
    id BIGINT DEFAULT nextval('order_seq') PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    payment_id BIGINT NOT NULL,
    delivery_id BIGINT NOT NULL,
    date DATE,
    quantity BIGINT,
    FOREIGN KEY (customer_id) REFERENCES Customer(id)  ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (payment_id) REFERENCES Payment(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (delivery_id) REFERENCES Delivery(id) ON DELETE CASCADE ON UPDATE CASCADE
);

--order_items table as a junction table between order and food
CREATE TABLE IF NOT EXISTS order_foods (
  order_id BIGINT,
  food_id BIGINT,
  PRIMARY KEY (order_id, food_id),
  FOREIGN KEY (order_id) REFERENCES Orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (food_id) REFERENCES Food(id) ON DELETE CASCADE ON UPDATE CASCADE
);