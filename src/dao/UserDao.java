/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class UserDao {
    public static void save(User user) throws ClassNotFoundException{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
		try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
		Statement st =con1.createStatement();
                String query = "insert into nhanvien (ten_nv,sdt_nv,email,mk_nv,cauhoi,traloi,status) values('"+user.getName()+"','"+user.getMobilenumber()+"','"+user.getEmail()+"','"+user.getPassword()+"','"+user.getQuestion()+"','"+user.getAnswer()+"','false')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Đăng kí thành công , chờ quản trị viên phê duyệt");
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }
    public static User Login(String Email,String Password) throws ClassNotFoundException{
        User user =null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
                
        try(java.sql.Connection con1=DriverManager.getConnection(dbURL)) {
            Statement st =con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from nhanvien where email='"+Email+"' and mk_nv='"+Password+"'");
            while(rs.next()){
                user =new User();
                user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        return user;
    }
    
    public static User getSecurityQuestion(String email) throws ClassNotFoundException{
        User user =null;
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
                try(java.sql.Connection con1=DriverManager.getConnection(dbURL)) {
                Statement st =con1.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select *from nhanvien where email='"+email+"'");
                while(rs.next()){
                    user =new User();
                    user.setQuestion(rs.getString("cauhoi"));
                    user.setAnswer(rs.getString("traloi"));
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
                return user;
    }
    
    public static void Update(String email , String newpassword ) throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
		try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
		Statement st =con1.createStatement();
                String query = "update nhanvien set mk_nv = '"+ newpassword +"' where email='"+ email +"'";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Mật khẩu đã được đổi thành công");
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }
    
    public static ArrayList<User> getAllRecordsUser(String email) throws ClassNotFoundException{
        ArrayList<User> arrayList=new ArrayList<>();
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
                try(java.sql.Connection con1=DriverManager.getConnection(dbURL)) {
                 Statement st =con1.createStatement();
                ResultSet rs;
                rs = st.executeQuery("select *from nhanvien where email like '%"+email+"%'");   
                while(rs.next()){
                    User user=new User();
                    user.setId(rs.getInt("ma_nv"));
                    user.setName(rs.getString("ten_nv"));
                    user.setEmail(rs.getString("email"));
                    user.setMobilenumber(rs.getString("sdt_nv"));
                    user.setQuestion(rs.getString("cauhoi"));
                    user.setStatus(rs.getString("status"));
                    arrayList.add(user);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    
    public static void UpdateStatus(String email, String status) throws ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String dbURL="jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
		try(java.sql.Connection con1=DriverManager.getConnection(dbURL)){
		Statement st =con1.createStatement();
                String query = "update nhanvien set status = '"+ status +"' where email='"+ email +"'";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(null,"Trạng thái đã được thay đổi thành công!");
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }
    
     public static int getIdByEmail(String email) throws ClassNotFoundException {
        int id = 0;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from nhanvien where email like '" +email+ "' ");
            while (rs.next()) {
                id = rs.getInt("ma_nv");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
     
      public static String getNameByEmail(String email) throws ClassNotFoundException {
        String name="";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from nhanvien where email like '" +email+ "' ");
            while (rs.next()) {
                name = rs.getString("ten_nv");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
      
      public static String getMailById(int id) throws ClassNotFoundException {
        String email="";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost;databaseName=cafeman2;user=sa;password=123";
        try ( java.sql.Connection con1 = DriverManager.getConnection(dbURL)) {
            Statement st = con1.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select *from nhanvien where ma_nv = '" +id+ "' ");
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }
}
     

