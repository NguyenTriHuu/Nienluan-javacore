/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.Customer;

/**
 *
 * @author user
 */
public class CustomerDao {

    public static void save(Customer cus) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            String query = "insert into khachhang (ten_kh,sdt_kh) values('" + cus.getTen_kh() + "','" + cus.getSdt_kh() + "')";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getIdBySdt(String sdt) throws ClassNotFoundException {
        int id = 0;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from khachhang where sdt_kh like '" + sdt + "' ");
            while (rs.next()) {
                id = rs.getInt("ma_kh");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    public static Customer getAllRecordById(int id) throws ClassNotFoundException {
        Customer cus =new Customer();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from khachhang where ma_kh ='" + id + "' ");
            while (rs.next()) {
                cus.setMa_kh(id);
                cus.setTen_kh(rs.getString("ten_kh")) ;
                cus.setSdt_kh(rs.getString("sdt_kh"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cus;
    }
    
}
