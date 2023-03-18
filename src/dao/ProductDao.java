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
import model.Product;
import model.User;

/**
 *
 * @author user
 */
public class ProductDao {

    public static void save(Product product) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        int ma_loaihang = 0;
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {

            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from loaihang where ten_loaihang='" + product.getTen_loaihang() + "'");
            while (rs.next()) {
                ma_loaihang = rs.getInt("ma_loaihang");
            }
            String query = "insert into hanghoa(ma_loaihang,ten_hh,gia_hh) values ('" + ma_loaihang + "',N'" + product.getTen_hh() + "','" + product.getGia_hh() + "')";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Thêm thành công hàng hóa '"+ product.getTen_hh() +"'! ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static ArrayList<Product> getAllRecords() throws ClassNotFoundException{
        ArrayList<Product> arrayList= new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        int ma_loaihang=0;
        try(java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
           Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT  hanghoa.ma_hh , hanghoa.ma_loaihang , hanghoa.ten_hh , hanghoa.gia_hh ,loaihang.ten_loaihang FROM hanghoa INNER JOIN loaihang ON hanghoa.ma_loaihang = loaihang.ma_loaihang");
            while (rs.next()) {
                Product product = new Product();
                product.setMa_hh(rs.getInt("ma_hh"));
                product.setTen_hh(rs.getString("ten_hh"));
                product.setGia_hh(rs.getString("gia_hh"));
                product.setTen_loaihang(rs.getString("ten_loaihang"));
                arrayList.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
        
    }
    
    public static void update(Product product) throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        int ma_loaihang = 0;
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {

            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from loaihang where ten_loaihang='" + product.getTen_loaihang() + "'");
            while (rs.next()) {
                ma_loaihang = rs.getInt("ma_loaihang");
            }
            String query = "update hanghoa set ten_hh=N'"+product.getTen_hh()+"',gia_hh='"+product.getGia_hh()+"',ma_loaihang='"+ma_loaihang+"' where ma_hh ='"+product.getMa_hh()+"'";
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Thêm thành công hàng hóa '"+ product.getTen_hh() +"'! ");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static void delete(String id) throws ClassNotFoundException {
         String query ="delete from hanghoa where ma_hh='"+id+"'";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Hàng hóa đã được xóa thành công!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public static ArrayList<Product> getAllRecordsByCategory(String category) throws ClassNotFoundException{
        ArrayList<Product> list = new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
            Statement st =con1.createStatement();
            ResultSet rs;
            rs =st.executeQuery("SELECT hanghoa.ten_hh FROM hanghoa INNER JOIN loaihang ON hanghoa.ma_loaihang = loaihang.ma_loaihang where loaihang.ten_loaihang like N'%"+category+"%'");
            while(rs.next()){
                Product product =new Product();
                product.setTen_hh(rs.getString("ten_hh"));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public static ArrayList<Product> filterProductByName(String name ,String category) throws ClassNotFoundException{
        ArrayList<Product> list = new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
            Statement st =con1.createStatement();
            ResultSet rs;
            rs =st.executeQuery("SELECT hanghoa.ten_hh FROM hanghoa INNER JOIN loaihang ON hanghoa.ma_loaihang = loaihang.ma_loaihang where hanghoa.ten_hh  like N'%"+name+"%' and loaihang.ten_loaihang='"+category+"'");
            while(rs.next()){
                Product product =new Product();
                product.setTen_hh(rs.getString("ten_hh"));
                list.add(product);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public static Product getProductByName(String name) throws ClassNotFoundException{
        Product product =new Product();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
            Statement st =con1.createStatement();
            ResultSet rs;
            rs =st.executeQuery("select *from hanghoa inner join loaihang on hanghoa.ma_loaihang=loaihang.ma_loaihang where ten_hh like N'%"+name+"%'");
            while(rs.next()){
                product.setTen_hh(rs.getString(3));
                product.setTen_loaihang(rs.getString(6));
                product.setGia_hh(rs.getString(4));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return product;
    }
    
    public static void savechitietbanhang(int idhd,int idhh,String sl ) throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            String query = "insert into chitietbanhang values ('"+idhd+"','"+idhh+"','"+sl+"')";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static int getIdbyName(String name) throws ClassNotFoundException {
        int id = 1;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            java.sql.Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from hanghoa where ten_hh like N'%"+name+"%'");
            if (rs.next()) {
                id = rs.getInt("ma_hh");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
}
