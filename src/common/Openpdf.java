/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class Openpdf {
    public static void OpenDpf(String id){
        try{
            if((new File("C:\\Dulieuhoadon\\"+id+".pdf")).exists()){
                Process p=Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\Dulieuhoadon\\"+id+".pdf");
            }
            else{
                JOptionPane.showMessageDialog(null, "Tệp không tồn tại");
            }
                
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }
}
