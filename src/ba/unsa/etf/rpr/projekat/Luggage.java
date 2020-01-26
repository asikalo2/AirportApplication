package ba.unsa.etf.rpr.projekat;

public class Luggage {
    private int id;
    private Passenger passenger;

    public Luggage(){}

    public Luggage(int id, Passenger passenger) {
        this.id = id;
        this.passenger = passenger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getPassengerName() {
        if (passenger == null)
            return "";
        return passenger.getName();
    }


}
