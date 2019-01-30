package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Flight {
    private int id;
    private String code;
    private Airplane airplane;
    private LocalDate startOfUsingTheRunway;
    private LocalDate endOfUsingTheRunway;
    private FlightType flightType;
    private User user;

    public Flight(){
    }

    public Flight(int id, String code, Airplane airplane, LocalDate startOfUsingTheRunway,
                  LocalDate endOfUsingTheRunway, FlightType flightType, User user) {
        this.id = id;
        this.airplane = airplane;
        this.code = code;
        this.startOfUsingTheRunway = startOfUsingTheRunway;
        this.endOfUsingTheRunway = endOfUsingTheRunway;
        this.flightType = flightType;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getStartOfUsingTheRunway() {
        return startOfUsingTheRunway;
    }

    public void setStartOfUsingTheRunway(LocalDate startOfUsingTheRunway) {
        this.startOfUsingTheRunway = startOfUsingTheRunway;
    }

    public LocalDate getEndOfUsingTheRunway() {
        return endOfUsingTheRunway;
    }

    public void setEndOfUsingTheRunway(LocalDate endOfUsingTheRunway) {
        this.endOfUsingTheRunway = endOfUsingTheRunway;
    }



    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
