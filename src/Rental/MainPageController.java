/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rental;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class MainPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane mainPane;
    @FXML    
    private Button btn1;
    
    @FXML
    private Button btn2;
    
    @FXML
    private Button btn3;
    
    @FXML
    private Button btn4;
    
    @FXML
    private ImageView imagerent;
    @FXML
    private Label lbl2;
    String username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void getUser(String user){
        username=user;
        lbl2.setText("Welcome, "+user);
    }
    public void Buy(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/Purchase.fxml").openStream());
        PurchaseController u = (PurchaseController)loader.getController();
        u.getUser(username);
        mainPane.getChildren().setAll(root1);
        /*
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("House Rental Management System");
        stage.show();
*/
    }
    public void sell(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/Sell.fxml").openStream());
        SellController s = (SellController)loader.getController();
        s.getUser(username);
        mainPane.getChildren().setAll(root1);
    }
    
    public void view(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/View.fxml").openStream());
        ViewController s = (ViewController)loader.getController();
        s.getUser(username);
        mainPane.getChildren().setAll(root1);
    }
    public void signOut(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Rental/SignOut.fxml"));
        mainPane.getChildren().setAll(root);
    }
    public void profile(ActionEvent event) throws IOException, SQLException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/MyProfile.fxml").openStream());
        MyProfileController s = (MyProfileController)loader.getController();
        s.MyProfileController(username);
        mainPane.getChildren().setAll(root1);
    }
}
