package ba.unsa.etf.rpr.projekat;

public class Airplane {
    private int id;
    private Airline airline;
    private String manufacturer;
    private String type;
    private int number_of_seats;

    public Airplane(){}

    public Airplane(int id, Airline airline, String manufacturer, String type, int number_of_seats) {
        this.id = id;
        this.airline = airline;
        this.manufacturer = manufacturer;
        this.type = type;
        this.number_of_seats = number_of_seats;
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

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
