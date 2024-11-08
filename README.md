# User-Access-Management-System

Requestries:
  - PostgresSQL
  - Apache Tomcat
  - java

Creating database and tables :
    -Use these below queries in your postgresSQL 
    Step 1 (Create database):
      CREATE DATABASE useraccess;      
    Step 2 (create tables):
      CREATE TABLE users (
          id SERIAL PRIMARY KEY,
          username TEXT UNIQUE NOT NULL,
          password TEXT NOT NULL,
          role TEXT CHECK (role IN ('Employee', 'Manager', 'Admin')) NOT NULL
      );
      
      CREATE TABLE software (
          id SERIAL PRIMARY KEY,
          name TEXT NOT NULL,
          description TEXT,
          access_levels TEXT CHECK (access_levels IN ('Read', 'Write', 'Admin')) NOT NULL
      );
      
      CREATE TABLE requests (
          id SERIAL PRIMARY KEY,
          user_id INTEGER REFERENCES users(id),
          software_id INTEGER REFERENCES software(id),
          access_type TEXT CHECK (access_type IN ('Read', 'Write', 'Admin')) NOT NULL,
          reason TEXT,
          status TEXT CHECK (status IN ('Pending', 'Approved', 'Rejected')) NOT NULL
      );

    Step 3(create Users):
      INSERT INTO users (username, password, role) 
      VALUES ('john', '0000', 'Employee');
      INSERT INTO users (username, password, role) 
      VALUES ('kelen', '0000', 'Admin');
      INSERT INTO users (username, password, role) 
      VALUES ('kartik', '0000', 'Manager');



      
