package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnectionFactory {
	
	
	static { 
        try {
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Static block has failed me");
        }
    }

	public static String url = "jdbc:postgresql://"
		+System.getenv("DB_URL")+"/ersDB";
	public static String username= System.getenv("DB_USERNAME");
	public static String password=System.getenv("DB_PASSWORD");
	
	public MyConnectionFactory() {
		
	}
	
	
	public MyConnectionFactory(String _url, String _username, String _password) {
		url = _url;
		username = _username;
		password = _password;
	}


	public void h2InitDao() {
        try(Connection conn=DriverManager.getConnection(url,username, password))
        {
            String sql= ""+
            "DROP TABLE IF EXISTS reimbur; " + 
            "DROP TABLE IF EXISTS users; " + 
            "DROP TABLE IF EXISTS roles; " + 
            "DROP TABLE IF EXISTS reimbur_status; " + 
            "DROP TABLE IF EXISTS reimbur_type; " + 
            "CREATE TABLE roles( " + 
            	"role_id INTEGER PRIMARY KEY, " + 
            	"user_role VARCHAR(20) NOT NULL); " + 
            	
            "CREATE TABLE reimbur_status( " + 
            	"status_id INTEGER PRIMARY KEY, " + 
            	"reimbur_status VARCHAR(10) NOT NULL); " + 
            	
            "CREATE TABLE reimbur_type( " + 
            	"type_id INTEGER PRIMARY KEY, " + 
            	"reimbur_type VARCHAR(10) NOT NULL);" +
            
            "CREATE TABLE users( " + 
            	"user_id SERIAL PRIMARY KEY, " + 
            	"username VARCHAR(50) UNIQUE NOT NULL, " + 
            	"pw VARCHAR(50) NOT NULL, " + 
            	"first_name VARCHAR(100) NOT NULL, " + 
            	"last_name VARCHAR(100) NOT NULL, " + 
            	"email VARCHAR(150) UNIQUE NOT NULL, " + 
            	"fk_role_id INTEGER NOT NULL, " + 
            	"FOREIGN KEY(fk_role_id) REFERENCES roles(role_id));" + 
            	
            "CREATE TABLE reimbur( " + 
            	"reimbur_id SERIAL PRIMARY KEY, " + 
            	"amount NUMERIC NOT NULL, " + 
            	"submitted TIMESTAMP NOT NULL, " + 
            	"resolved TIMESTAMP, " + 
            	"description VARCHAR(250), " + 
            	"receipt BYTEA, " + 
            	"fk_author INTEGER NOT NULL, " + 
            	"fk_resolver INTEGER, " + 
            	"fk_status_id INTEGER NOT NULL, " + 
            	"fk_type_id INTEGER NOT NULL, " + 
            	"FOREIGN KEY(fk_author) REFERENCES users(user_id), " + 
            	"FOREIGN KEY(fk_resolver) REFERENCES users(user_id), " + 
            	"FOREIGN KEY(fk_status_id) REFERENCES reimbur_status(status_id), " + 
            	"FOREIGN KEY(fk_type_id) REFERENCES reimbur_type(type_id));" + 
            	
            "INSERT INTO reimbur_type VALUES(1,'LODGING'); " + 
            "INSERT INTO reimbur_type VALUES(2,'TRAVEL'); " + 
            "INSERT INTO reimbur_type VALUES(3,'FOOD'); " + 
            "INSERT INTO reimbur_type VALUES(4,'OTHER'); " + 

            "INSERT INTO reimbur_status VALUES(1,'PENDING'); " + 
            "INSERT INTO reimbur_status VALUES(2,'APPROVED'); " + 
            "INSERT INTO reimbur_status VALUES(3,'DENIED'); " + 

            "INSERT INTO roles VALUES(1,'Employees'); " + 
            "INSERT INTO roles VALUES(2,'Finance Managers'); " + 

            "INSERT INTO users (username,pw,first_name,last_name,email,fk_role_id) " + 
            			"VALUES('daviles','test','Diego','Aviles','davil022@fiu.edu',1); " + 
            "INSERT INTO users (username,pw,first_name,last_name,email,fk_role_id) " + 
            			"VALUES('admin','admin','Lebron','James','jordanGOAT@gmail.com',2); " + 
            "INSERT INTO users (username,pw,first_name,last_name,email,fk_role_id) " + 
            			"VALUES('elon','sus','Elon','Musket','tesla>edison@gmail.com',1); " + 
            
            "INSERT INTO reimbur(amount,submitted,description,fk_author,fk_status_id,fk_type_id) " + 
            			"VALUES(17.38, '2020-12-11 11:14:00','testing tables',1,1,2); " + 
            "INSERT INTO reimbur(amount,submitted,description,fk_author,fk_status_id,fk_type_id) " + 
            			"VALUES(683.82, '2020-12-23 10:19:00','blah blah blah',1,1,4); " + 
            "INSERT INTO reimbur(amount,submitted,description,fk_author,fk_status_id,fk_type_id) " + 
            			"VALUES(20.50, '2020-12-23 12:14:00','mac n cheese',1,1,3); " + 
            "INSERT INTO reimbur(amount,submitted,description,fk_author,fk_status_id,fk_type_id) " + 
            			"VALUES(3899.99, '2020-12-23 12:14:00','cybertruck',3,1,4);";
            
            Statement state = conn.createStatement();
            state.execute(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }
		
	}


	public void h2DestroyDao() {
		
		try(Connection conn= DriverManager.getConnection(url,username, password))
        {
            String sql= ""+
            "DROP TABLE reimbur; " + 
            "DROP TABLE users; " + 
            "DROP TABLE roles; " +
            "DROP TABLE reimbur_status; " + 
            "DROP TABLE reimbur_type;";
            
            Statement state = conn.createStatement();
            state.execute(sql);
        }catch(SQLException e) {
            e.printStackTrace();
        }	
	}
	
	
}
