/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class RegisterController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private AnchorPane regPane;
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf5;
    @FXML
    private TextField tf6;
    @FXML
    private TextField tf7;
    @FXML
    private TextField tf8;
    @FXML
    private TextField tf9;
    @FXML
    private TextField tf10;
    @FXML
    private Button btn1;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField tf11;
    @FXML
    private PasswordField tf12;
    @FXML
    private PasswordField tf13;
    @FXML
    private Label lb1;
    @FXML
    private Button btn2;
    
    Connection conn;
    PreparedStatement pst;
    PreparedStatement pst1;
    ResultSet rs;
    public void OnRegister(ActionEvent event)throws IOException, SQLException{
        String name = tf1.getText();
        String email = tf2.getText();
        String phone = tf3.getText();
        String house = tf4.getText();
        String locality = tf5.getText();
        String street = tf6.getText();
        String city = tf7.getText();
        String state = tf8.getText();
        String pin = tf9.getText();
        String aadhar=tf10.getText();
        String pan = tf11.getText();
        LocalDate date = dob.getValue();
        java.sql.Date sqld = java.sql.Date.valueOf(date);
        String pass1 = tf12.getText();
        String pass2 = tf13.getText();
        if(pass1.equals(pass2)==false){
            lb1.setText("Password don't match");
        }
        else{
            try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
            pst = (PreparedStatement) conn.prepareStatement("Insert into register (name,datebirth,email,phone,house,locality,street,city,state,pincode,aadhar,pan,pass)"+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,name);
            pst.setDate(2,sqld);
            pst.setString(3,email);
            pst.setString(4, phone);
            pst.setString(5,house);
            pst.setString(6,locality);
            pst.setString(7,street);
            pst.setString(8, city);
            pst.setString(9, state);
            pst.setString(10,pin);
            pst.setString(11,aadhar);
            pst.setString(12,pan);
            pst.setString(13,pass1);
            
            pst.execute();
            
            pst1 = (PreparedStatement) conn.prepareStatement("Insert into user_login (username,password)"+"values(?,?)");
            pst1.setString(1,email);
            pst1.setString(2,pass1);
            
            pst1.execute();
            
            Parent root1 = FXMLLoader.load(getClass().getResource("/Rental/Reg_Success.fxml"));
            regPane.getChildren().setAll(root1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void LoginAgain(ActionEvent event) throws IOException{
        Parent root1 = FXMLLoader.load(getClass().getResource("/Rental/Login.fxml"));
        regPane.getChildren().setAll(root1);
    }
}
