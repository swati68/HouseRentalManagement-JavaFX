/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private AnchorPane LoginPane;
    @FXML
    private TextField tf2;
    @FXML
    private PasswordField tf3;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    Connection conn;
    PreparedStatement pst;
    PreparedStatement pst1;
    ResultSet rs;
    ResultSet rs1;
    
    public void Register(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Rental/Register.fxml"));
        LoginPane.getChildren().setAll(root);
    }
    
    public void Login(ActionEvent event) throws IOException, SQLException{
        String uname=tf2.getText();
        String pass = tf3.getText();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
            pst = (PreparedStatement) conn.prepareStatement("Select * from user_login where username=? and password=?");
            pst.setString(1,uname);
            pst.setString(2,pass);
            
            rs = pst.executeQuery();
            if(rs.next()){
                //Stage stage1 = new Stage();
                pst1 = (PreparedStatement) conn.prepareStatement("Select * from register where email=?");
                pst1.setString(1,uname);
                //pst1.setString(2,pass);
            
                rs1 = pst1.executeQuery();
                if(rs1.next()){
                    FXMLLoader loader = new FXMLLoader();
                    Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
                    MainPageController u = (MainPageController)loader.getController();
                    u.getUser(rs1.getString("name"));
                    LoginPane.getChildren().setAll(root1);
                }
            }
            else{
                tf2.clear();
                tf3.clear();
                lbl1.setText("Incorrect Username or Password!");
                lbl2.setText("Please Register if you don't have an account");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
