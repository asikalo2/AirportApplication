package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.DatePicker;

public class Flight {
    private int id;
    private int airplane;
    private String code;
    private DatePicker startOfUsingTheRunway;
    private DatePicker endOfUsingTheRunway;;
    private int flightType;
    private int user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAirplane() {
        return airplane;
    }

    public void setAirplane(int airplane) {
        this.airplane = airplane;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DatePicker getStartOfUsingTheRunway() {
        return startOfUsingTheRunway;
    }

    public void setStartOfUsingTheRunway(DatePicker startOfUsingTheRunway) {
        this.startOfUsingTheRunway = startOfUsingTheRunway;
    }

    public DatePicker getEndOfUsingTheRunway() {
        return endOfUsingTheRunway;
    }

    public void setEndOfUsingTheRunway(DatePicker endOfUsingTheRunway) {
        this.endOfUsingTheRunway = endOfUsingTheRunway;
    }

    public int getFlightType() {
        return flightType;
    }

    public void setFlightType(int flightType) {
        this.flightType = flightType;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
