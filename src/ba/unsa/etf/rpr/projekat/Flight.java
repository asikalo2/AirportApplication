package ba.unsa.etf.rpr.projekat;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Flight {
    private int id;
    private String code;
    private Airplane airplane;
    private LocalDateTime startOfUsingTheRunway;
    private LocalDateTime endOfUsingTheRunway;
    private FlightType flightType;
    private User user;
    private Gate gate;

    public Flight(){
    }

    public Flight(int id, String code, Airplane airplane, LocalDateTime startOfUsingTheRunway,
                  LocalDateTime endOfUsingTheRunway, FlightType flightType, User user, Gate gate) {
        this.id = id;
        this.airplane = airplane;
        this.code = code;
        this.startOfUsingTheRunway = startOfUsingTheRunway;
        this.endOfUsingTheRunway = endOfUsingTheRunway;
        this.flightType = flightType;
        this.user = user;
        this.gate = gate;
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

    public LocalDateTime getStartOfUsingTheRunway() {
        return startOfUsingTheRunway;
    }

    public void setStartOfUsingTheRunway(LocalDateTime startOfUsingTheRunway) {
        this.startOfUsingTheRunway = startOfUsingTheRunway;
    }

    public LocalDateTime getEndOfUsingTheRunway() {
        return endOfUsingTheRunway;
    }

    public void setEndOfUsingTheRunway(LocalDateTime endOfUsingTheRunway) {
        this.endOfUsingTheRunway = endOfUsingTheRunway;
    }



    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
    public String getAirlineName() { return airplane.getAirlineName(); }

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
    public String getUserName() { return user.getName(); }
    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }

}
