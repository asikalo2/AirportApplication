package ba.unsa.etf.rpr.projekat;

public class AdditionalLuggage extends Luggage {

    private double weight;
    private double payExtra;

    private enum items {metal, clothes, money};

    public AdditionalLuggage(int id, Passenger passenger, double weight, double payExtra) {
        super(id, passenger);
        this.weight = weight;
        this.payExtra = payExtra;
    }

    public AdditionalLuggage(){}

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
