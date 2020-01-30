package ba.unsa.etf.rpr.projekat;

public class Airplane {
    private int id;
    private Airline airline;
    private String manufacturer;
    private String type;
    private int numberOfSeats;

    public Airplane(){}

    public Airplane(int id, Airline airline, String manufacturer, String type, int numberOfSeats) throws IllegalNumberOfSeats {
        if(numberOfSeats > 300) {
            throw new IllegalNumberOfSeats("Illegal number of seats!");
        }
        this.id = id;
        this.airline = airline;
        this.manufacturer = manufacturer;
        this.type = type;
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats)  { this.numberOfSeats = numberOfSeats;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getAirlineName() { return airline.getName(); }

}
