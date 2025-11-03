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
public class PurchaseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button btn1;
    String username;
    @FXML
    private AnchorPane buyPane;
    @FXML
    private TableView<Houses> table;
    @FXML
    private TableColumn<Houses,String> locColumn;
    @FXML
    private TableColumn<Houses,String> dimColumn;
    @FXML
    private TableColumn<Houses,String> roomColumn;
    @FXML
    private TableColumn<Houses,String> bathColumn;
    @FXML
    private TableColumn<Houses,String> descColumn;
    @FXML
    private TableColumn<Houses,String> rentColumn;
    @FXML
    private TableColumn<Houses,String> soldColumn;
    @FXML
    private Button rentBtn;
    @FXML
    private Label lbl1;
    Connection conn;
    PreparedStatement pst;
    PreparedStatement pst1;
    ResultSet rs;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        locColumn.setCellValueFactory(new PropertyValueFactory<>("locProperty"));
        dimColumn.setCellValueFactory(new PropertyValueFactory<>("dimProperty"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("roomProperty"));
        bathColumn.setCellValueFactory(new PropertyValueFactory<>("bathProperty"));
        descColumn.setCellValueFactory(new PropertyValueFactory<>("descProperty"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<>("rentProperty"));
        soldColumn.setCellValueFactory(new PropertyValueFactory<>("soldProperty"));
        try {
            table.setItems(getData());
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    public ObservableList<Houses> getData() throws SQLException{
        ObservableList<Houses> h =FXCollections.observableArrayList();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
            pst = (PreparedStatement) conn.prepareStatement("Select * from house_sell where status=?");
            pst.setBoolean(1,true);
            rs = pst.executeQuery();
            while(rs.next()){
                h.add(new Houses(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(1)));
                
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
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }
    public void getUser(String user){
        username = user;
    }
    public void onRent(ActionEvent event) throws ClassNotFoundException, SQLException, IOException{
        Houses h = table.getSelectionModel().getSelectedItem();
        String a = h.getSoldProperty();
        String b = h.getDimProperty();
        String c = h.getRoomProperty();
        String d = h.getBathProperty();
        String e = h.getRentProperty();
        String f = h.getLocProperty();
        String g = h.getDescProperty();
        if((h.getSoldProperty()).equals(username)) {
            lbl1.setText("You cannot Rent a house sold by you!");
        } else {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/houserentalsystem","root","");
                pst = (PreparedStatement) conn.prepareStatement("update house_sell set status=? where username=? and dimensions=? and rooms=? and bathrooms=? and rent=?");
                pst.setBoolean(1,false);
                pst.setString(2, a);
                pst.setString(3,b);
                pst.setString(4,c);
                pst.setString(5,d);
                pst.setString(6,e);
                pst.executeUpdate();
                pst1 = (PreparedStatement) conn.prepareStatement("Insert into buyer_detail (location,dimension,room,bathroom,description,rent,sold_by,purchase_by)"+"values(?,?,?,?,?,?,?,?)");
                pst1.setString(1,f);
                pst1.setString(2,b);
                pst1.setString(3,c);
                pst1.setString(4,d);
                pst1.setString(5,g);
                pst1.setString(6,e);
                pst1.setString(7,a);
                pst1.setString(8,username);
                pst1.execute();
                conn.close();
                FXMLLoader loader = new FXMLLoader();
                Parent root1 = loader.load(getClass().getResource("/Rental/buy_success.fxml").openStream());
                Buy_successController u = (Buy_successController)loader.getController();
                u.getUser(username);
                buyPane.getChildren().setAll(root1);
            }
            catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void back(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        Parent root1 = loader.load(getClass().getResource("/Rental/MainPage.fxml").openStream());
        MainPageController u = (MainPageController)loader.getController();
        u.getUser(username);
        buyPane.getChildren().setAll(root1);
    } 
}
