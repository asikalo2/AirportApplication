package ba.unsa.etf.rpr.projekat;

public class Luggage extends AbstractLuggage {

    public Luggage() {};

    public Luggage(int id, Passenger passenger) {
        super(id, passenger);
    }

    @Override
    public Double getWeight() {
        return null;
    }

    @Override
    public void setWeight(double weight) {

    }

    @Override
    public Double getPayExtra() {
        return null;
    }

    @Override
    public void setPayExtra(double payExtra) {

    }

    @Override
    public AdditionalLuggage.Type getAddLuggageType() {
        return null;
    }

    @Override
    public void setAddLuggageType(AdditionalLuggage.Type addLuggageType) {

    }

}
