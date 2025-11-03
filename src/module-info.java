/**
 * 
 */
/**
 * @author swatisingh
 *
 */
module HouseRental {
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	
	opens Rental to javafx.fxml;
    exports Rental;
}