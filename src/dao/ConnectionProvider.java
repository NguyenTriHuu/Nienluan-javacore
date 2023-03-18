/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.sun.jdi.connect.spi.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class ConnectionProvider {
    public static Connection getCon() throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman;user=sa;password=123";
		try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
		return (Connection) con1;
		}
		catch (Exception e){
			return null;
		}
    }
}
