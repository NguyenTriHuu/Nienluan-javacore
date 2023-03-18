/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Statement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;

/**
 *
 * @author user
 */
public class BillDao {

    public static String getId() throws ClassNotFoundException {
        int id = 1;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            java.sql.Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select max(ma_hd)ma_hd from hoadon");
            if (rs.next()) {
                id = rs.getInt("ma_hd");
                id++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return String.valueOf(id);
    }

    public static void save(Bill bill) throws ClassNotFoundException {
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            String query = "insert into hoadon (ma_nv,ma_kh,ngaylap,tongtien) values ('"+bill.getMa_nv()+"','"+bill.getMa_kh()+"','"+bill.getNgaylap()+"','"+bill.getTongtien()+"')";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Hóa đơn được tạo thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Bill> getAllRecordByInc(String date) throws ClassNotFoundException{
        ArrayList<Bill> list=new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try(java.sql.Connection con1 =DriverManager.getConnection(dbURL)) {
            Statement st =con1.createStatement();
            ResultSet rs;
            rs=st.executeQuery("select * from hoadon where ngaylap like '%"+date+"%'");
            while(rs.next()){
                Bill bill= new Bill();
                bill.setMa_hd(rs.getInt("ma_hd"));
                bill.setMa_kh(rs.getInt("ma_kh"));
                bill.setMa_nv(rs.getInt("ma_nv"));
                bill.setNgaylap(rs.getString("ngaylap"));
                bill.setTongtien(rs.getString("tongtien"));
                list.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Bill> getAllRecordByDesc(String date) throws ClassNotFoundException{
        ArrayList<Bill> list=new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try(java.sql.Connection con1 =DriverManager.getConnection(dbURL)) {
            Statement st =con1.createStatement();
            ResultSet rs;
            rs=st.executeQuery("select * from hoadon where ngaylap like '%"+date+"%' order by ma_hd DESC");
            while(rs.next()){
                Bill bill= new Bill();
                bill.setMa_hd(rs.getInt("ma_hd"));
                bill.setMa_kh(rs.getInt("ma_kh"));
                bill.setMa_nv(rs.getInt("ma_nv"));
                bill.setNgaylap(rs.getString("ngaylap"));
                bill.setTongtien(rs.getString("tongtien"));
                list.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
