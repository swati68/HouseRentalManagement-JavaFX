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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Swati
 */
public class ViewController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private AnchorPane viewPane;
    @FXML
    private Button btn1;
    private String username;
    @FXML
    private TableView<Houses> viewTable;
    @FXML
    private TableColumn<Houses,String> loc;
    @FXML
    private TableColumn<Houses,String> dim;
    @FXML
    private TableColumn<Houses,String> room;
    @FXML
    private TableColumn<Houses,String> bath;
    @FXML
    private TableColumn<Houses,String> desc;
    @FXML
    private TableColumn<Houses,String> rent;
    @FXML
    private TableColumn<Houses,String> user_buy;
    @FXML
    private Label lbl1;
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loc.setCellValueFactory(new PropertyValueFactory<>("locProperty"));
        dim.setCellValueFactory(new PropertyValueFactory<>("dimProperty"));
        room.setCellValueFactory(new PropertyValueFactory<>("roomProperty"));
        bath.setCellValueFactory(new PropertyValueFactory<>("bathProperty"));
        desc.setCellValueFactory(new PropertyValueFactory<>("descProperty"));
        rent.setCellValueFactory(new PropertyValueFactory<>("rentProperty"));
        user_buy.setCellValueFactory(new PropertyValueFactory<>("soldProperty"));
        try {
            viewTable.setItems(getData());
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public ObservableList<Houses> getData() throws SQLException{
        ObservableList<Houses> h =FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
            pst = (PreparedStatement) conn.prepareStatement("Select * from buyer_detail where sold_by=?");
            pst.setString(1,username);
            rs = pst.executeQuery();
            if(rs.next()){
                h.add(new Houses(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(8)));
                }
            else{
                lbl1.setText("You have not Put any house on Rent or Your House is not rented Yet!");
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }
    public void getUser(String user){
        username = user;
        //lbl1.setText(username);
    }
    public void back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
        MainPageController u = (MainPageController)loader.getController();
        u.getUser(username);
        viewPane.getChildren().setAll(root1);
    } 
    
}
