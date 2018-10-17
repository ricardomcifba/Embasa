/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author E127787
 */
public abstract class GenericDAO {
    
    private static final String URI = "jdbc:postgresql://localhost:5432/sd";
    private static final String USER = "postgres";	
    private static final String PWD = "postgres";
	
    private Connection conn = null;
	
    protected Connection getConnection() throws SQLException{
           
	if(this.conn != null)
            return this.conn;
        DriverManager.registerDriver(new org.postgresql.Driver());
	this.conn = DriverManager.getConnection(URI,USER,PWD);
	return this.conn;
                
    }
    
}
