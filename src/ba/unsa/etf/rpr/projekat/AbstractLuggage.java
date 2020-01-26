package ba.unsa.etf.rpr.projekat;

public abstract class AbstractLuggage {
    private int id;
    private Passenger passenger;

    public AbstractLuggage(){}

    public AbstractLuggage(int id, Passenger passenger) {
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

    public abstract Double getWeight();

    public abstract void setWeight(double weight);

    public abstract Double getPayExtra();

    public abstract void setPayExtra(double payExtra);

    public abstract AdditionalLuggage.Type getAddLuggageType();

    public abstract void setAddLuggageType(AdditionalLuggage.Type addLuggageType);

}
