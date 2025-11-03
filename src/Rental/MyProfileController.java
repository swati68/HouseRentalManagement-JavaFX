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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class MyProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;
    @FXML
    private Label lbl5;
    @FXML
    private Label lbl6;
    @FXML
    private Label lbl7;
    @FXML
    private Label lbl8;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane profilePane;
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    String username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void MyProfileController(String user) throws SQLException{
        username = user;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
            pst = (PreparedStatement) conn.prepareStatement("Select * from register where name=?");
            pst.setString(1,username);
            
            rs = pst.executeQuery();
            if(rs.next()){
                lbl1.setText(rs.getString(1));
                lbl2.setText(rs.getString(2));
                lbl3.setText(rs.getString(3));
                lbl4.setText(rs.getString(4));
                lbl5.setText(rs.getString(5)+","+rs.getString(6)+","+rs.getString(7)+","+rs.getString(8)+","+rs.getString(9)+","+rs.getString(10));
                lbl6.setText(rs.getString(11));
                lbl7.setText(rs.getString(12));
                lbl8.setText(rs.getString(13));
                //Stage stage1 = new Stage();
                //pst1.setString(2,pass);
                /*
                rs1 = pst1.executeQuery();
                if(rs1.next()){
                    FXMLLoader loader = new FXMLLoader();
                    Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
                    MainPageController u = (MainPageController)loader.getController();
                    u.getUser(rs1.getString("name"));
                    LoginPane.getChildren().setAll(root1);
                }
                */
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public void getUser(String user){
        username = user;
    }
*/
    public void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
        MainPageController u = (MainPageController)loader.getController();
        u.getUser(username);
        profilePane.getChildren().setAll(root1);
    }
}
