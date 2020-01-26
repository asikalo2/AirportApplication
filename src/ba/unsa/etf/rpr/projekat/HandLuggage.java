package ba.unsa.etf.rpr.projekat;

public class HandLuggage extends Luggage
{
    private Double weight;
    private Double payExtra;

    public HandLuggage() {}

    public HandLuggage(int id, Passenger passenger, Double weight, Double payExtra) {
        super(id, passenger);
        this.weight = weight;
        this.payExtra = payExtra;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPayExtra() {
        return payExtra;
    }

    public void setPayExtra(Double payExtra) {
        this.payExtra = payExtra;
    }
}
