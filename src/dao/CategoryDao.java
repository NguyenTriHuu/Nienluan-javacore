/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Category;

/**
 *
 * @author user
 */
public class CategoryDao {

    public static void Save(Category category) throws ClassNotFoundException {
        String query = "insert into loaihang (ten_loaihang) values (N'" + category.getTen_lh() + "')";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Loại hàng đã được thêm thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Category> getAllRecords() throws ClassNotFoundException {
        ArrayList<Category> arrayList = new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try(java.sql.Connection con1=DriverManager.getConnection(dbURL)) {
           Statement st =con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from loaihang");
            while(rs.next()){
              Category category =new Category();
              category.setMa_lh(rs.getString("ma_loaihang"));
              category.setTen_lh(rs.getString("ten_loaihang"));
              arrayList.add(category);
            }  
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static void delete(String id) throws ClassNotFoundException{
        String query ="delete from loaihang where ma_loaihang='"+id+"'";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Loại hàng đã được xóa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
