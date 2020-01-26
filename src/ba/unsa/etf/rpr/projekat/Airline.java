package ba.unsa.etf.rpr.projekat;

public class Airline {
    private int id;
    private String name;
    private String code;

    private String country;
   // private enum TypeOfAirline {regular, lowCost}

    public Airline(){}

    public Airline(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
