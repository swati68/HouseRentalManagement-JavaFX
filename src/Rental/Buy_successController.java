/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rental;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class Buy_successController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn;
    @FXML
    private AnchorPane buySuccessPane;
    String username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void getUser(String user){
        username = user;
    }
    public void goBack(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
        MainPageController u = (MainPageController)loader.getController();
        u.getUser(username);
        buySuccessPane.getChildren().setAll(root1);
    }
}
