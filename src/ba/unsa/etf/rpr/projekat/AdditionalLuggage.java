package ba.unsa.etf.rpr.projekat;

public class AdditionalLuggage extends Luggage {

    private double weight;
    private double payExtra;

    public enum Type {METAL, CLOTHES, MONEY};

    private Type addLuggageType;

    public AdditionalLuggage(int id, Passenger passenger, double weight, double payExtra, Type addLuggageType) {
        super(id, passenger);
        this.weight = weight;
        this.payExtra = payExtra;
        this.addLuggageType = addLuggageType;
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
