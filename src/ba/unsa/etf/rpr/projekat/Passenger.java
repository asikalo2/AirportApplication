package ba.unsa.etf.rpr.projekat;

import javafx.scene.image.Image;

public class Passenger {
    private int id;
    private String name;
    private Flight flight;
    private Image qrCode;

    public Passenger(){}

    public Passenger(int id, String name, Flight flight, Image qrCode) {
        this.id = id;
        this.name = name;
        this.flight = flight;
        this.qrCode = qrCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Image getQrCode() {
        return qrCode;
    }

    public void setQrCode(Image qrCode) {
        this.qrCode = qrCode;
    }

}
