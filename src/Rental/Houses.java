/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rental;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Swati
 */
public class Houses {
    private SimpleStringProperty locProperty;
    private SimpleStringProperty dimProperty;
    private SimpleStringProperty roomProperty;
    private SimpleStringProperty bathProperty;
    private SimpleStringProperty descProperty;
    private SimpleStringProperty rentProperty;
    private SimpleStringProperty soldProperty;
    public Houses(String a,String b,String c,String d,String e,String f,String g){
        this.locProperty = new SimpleStringProperty(a);
        this.dimProperty = new SimpleStringProperty(b);
        this.roomProperty = new SimpleStringProperty(c);
        this.bathProperty = new SimpleStringProperty(d);
        this.descProperty = new SimpleStringProperty(e);
        this.rentProperty = new SimpleStringProperty(f);
        this.soldProperty = new SimpleStringProperty(g);
    }

    public String getLocProperty() {
        return locProperty.get();
    }

    public void setLocProperty(SimpleStringProperty locProperty) {
        this.locProperty = locProperty;
    }

    public String getDimProperty() {
        return dimProperty.get();
    }

    public void setDimProperty(SimpleStringProperty dimProperty) {
        this.dimProperty = dimProperty;
    }

    public String getRoomProperty() {
        return roomProperty.get();
    }

    public void setRoomProperty(SimpleStringProperty roomProperty) {
        this.roomProperty = roomProperty;
    }

    public String getBathProperty() {
        return bathProperty.get();
    }

    public void setBathProperty(SimpleStringProperty bathProperty) {
        this.bathProperty = bathProperty;
    }

    public String getDescProperty() {
        return descProperty.get();
    }

    public void setDescProperty(SimpleStringProperty descProperty) {
        this.descProperty = descProperty;
    }

    public String getRentProperty() {
        return rentProperty.get();
    }

    public void setRentProperty(SimpleStringProperty rentProperty) {
        this.rentProperty = rentProperty;
    }

    public String getSoldProperty() {
        return soldProperty.get();
    }

    public void setSoldProperty(SimpleStringProperty soldProperty) {
        this.soldProperty = soldProperty;
    }
}
