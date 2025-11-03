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
public class Reg_SuccessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane successPane;
    @FXML
    private Button btn1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void Reg_Log(ActionEvent event) throws IOException{
        //Stage stage1 = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("/Rental/Login.fxml"));
        successPane.getChildren().setAll(root1);
    }
}
