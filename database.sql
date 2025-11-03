create database houserentalsystem;
USE houserentalsystem;

CREATE TABLE register (
    name VARCHAR(100) NOT NULL,
    datebirth DATE,
    email VARCHAR(100) PRIMARY KEY,
    phone VARCHAR(15),
    house VARCHAR(100),
    locality VARCHAR(100),
    street VARCHAR(100),
    city VARCHAR(100),
    state VARCHAR(100),
    pincode VARCHAR(10),
    aadhar VARCHAR(20),
    pan VARCHAR(20),
    pass VARCHAR(50)
);

CREATE TABLE user_login (
    username VARCHAR(100) PRIMARY KEY,
    password VARCHAR(50),
    FOREIGN KEY (username) REFERENCES register(email)
);

CREATE TABLE house_sell (
    username VARCHAR(100),               -- landlord's email
    location VARCHAR(100),
    dimensions VARCHAR(50),
    rooms INT,
    bathrooms INT,
    description TEXT,
    rent DECIMAL(10,2),
    status BOOLEAN,                      -- true = available, false = rented
    FOREIGN KEY (username) REFERENCES register(email)
);

CREATE TABLE buyer_detail (
    location VARCHAR(100),
    dimension VARCHAR(50),
    room INT,
    bathroom INT,
    description TEXT,
    rent DECIMAL(10,2),
    sold_by VARCHAR(100),               -- landlord's email
    purchase_by VARCHAR(100),           -- tenant's email
    FOREIGN KEY (sold_by) REFERENCES register(email),
    FOREIGN KEY (purchase_by) REFERENCES register(email)
);
