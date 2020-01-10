package ba.unsa.etf.rpr.projekat;

public class HandLuggage extends Luggage
{
    private double weight;
    private double payExtra;

    public HandLuggage(int id, Passenger passenger, double weight, double payExtra) {
        super(id, passenger);
        this.weight = weight;
        this.payExtra = payExtra;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPayExtra() {
        return payExtra;
    }

    public void setPayExtra(double payExtra) {
        this.payExtra = payExtra;
    }
}
