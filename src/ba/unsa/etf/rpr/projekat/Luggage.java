package ba.unsa.etf.rpr.projekat;

public class Luggage extends AbstractLuggage {

    public Luggage() {};

    public Luggage(int id, Passenger passenger) {
        super(id, passenger);
    }

    public String getLuggageType() {
        if (this.getClass().equals(Luggage.class))
            return "Standard";
        else if (this.getClass().equals(HandLuggage.class)) {
            return "Hand Luggage";
        }
        else
            return "Additional Luggage";
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
