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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class SellController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextArea tf5;
    @FXML
    private TextField tf6;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane sellPane;
    @FXML
    private Button btn2;
    Connection conn;
    PreparedStatement pst;
    PreparedStatement pst1;
    ResultSet rs;
    String username;
    public void getUser(String user){
        username = user;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void toList(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
        String loc = tf1.getText();
        String dim = tf2.getText();
        String room = tf3.getText();
        String bath = tf4.getText();
        String desc = tf5.getText();
        String price = tf6.getText();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
            pst = (PreparedStatement) conn.prepareStatement("Insert into house_sell (username,location,dimensions,rooms,bathrooms,description,rent,status)"+"values(?,?,?,?,?,?,?,?)");
            pst.setString(1,username);
            pst.setString(2,loc);
            pst.setString(3,dim);
            pst.setString(4,room);
            pst.setString(5,bath);
            pst.setString(6,desc);
            pst.setString(7,price);
            pst.setBoolean(8,true);
            pst.execute();
            
            FXMLLoader loader = new FXMLLoader();
            Parent root1 = loader.load(getClass().getResource("/Rental/sell_success.fxml").openStream());
            Sell_successController u = (Sell_successController)loader.getController();
            u.getUser(username);
            sellPane.getChildren().setAll(root1);
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    public void Back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
        MainPageController u = (MainPageController)loader.getController();
        u.getUser(username);
        sellPane.getChildren().setAll(root1);
    }
}
