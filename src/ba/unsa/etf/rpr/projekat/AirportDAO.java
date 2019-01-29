package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class AirportDAO {
    private static AirportDAO instance = null;
    private static Connection conn;


    public static Connection getConn() {
        return conn;
    }

    public AirportDAO() {
        conn = null;

        try {
            String url = "jdbc:sqlite:AirportDB.db";
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void initialize() {
        instance = new AirportDAO();
    }

    public static AirportDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }

    public static void removeInstance() {
        try {
            conn.close();
            conn = null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        instance = null;
    }

    public ObservableList<Airline> getAirlines() {
        ArrayList<Airline> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name, code FROM airline_companies");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Airline m = new Airline(rs.getInt(1), rs.getString(2), rs.getString(3));
                res.add(m);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<Role> getRoles() {
        ArrayList<Role> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM roles");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role m = new Role(rs.getInt(1), rs.getString(2));
                res.add(m);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<FlightType> getFlightTypes() {
        ArrayList<FlightType> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, name FROM flight_types");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FlightType m = new FlightType(rs.getInt(1), rs.getString(2));
                res.add(m);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
/*
    public ObservableList<Luggage> getLuggages() {
        ArrayList<Luggage> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from luggages join passengers p " +
                    "on luggages.passenger = p.id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Passenger p = new Passenger(rs.getInt(2), rs.getString());
                Luggage l = new Luggage(rs.getInt(1), p);
                res.add(l);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
*/
    public ObservableList<User> getUsers() {
        ArrayList<User> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from users join roles r " +
                    "on users.role = r.id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role ro = new Role(rs.getInt(1), rs.getString(2));
                User u = new User(rs.getInt(1), rs.getString(2),ro);
                res.add(u);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
/*
    public ObservableList<Passenger> getPassengers() {
        ArrayList<Passenger> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from vozilo join proizvodjac, vlasnik, mjesto on \n" +
                    "vozilo.proizvodjac=proizvodjac.id and \n" +
                    "vozilo.vlasnik=vlasnik.id and \n" +
                    "vlasnik.mjesto_rodjenja=mjesto.id) join mjesto on\n" +
                    "mjesto_prebivalista=mjesto.id;");

            PreparedStatement stmt = conn.prepareStatement("select * from (select * from passengers join flights, " +
                    "airline_companies, planes, flight_types, flights, luggages, roles, users on " +
                    "passengers.flight = flights.id and \n" +
                    "f");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Airline airline = new Airline();
                Airplane airplane = new Airplane();
                FlightType flightType = new FlightType();
                Luggage luggage = new Luggage();
                Role role = new Role();
                User user = new User();
                Flight flight = new Flight(rs.getInt(3));
                Passenger passenger = new Passenger(rs.getInt(1), rs.getString(2),
                        flight);
                res.add(passenger);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }*/
    /*
    public ObservableList<Vozilo> getVozila() {
        ArrayList<Vozilo> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from vozilo join proizvodjac, vlasnik, mjesto on \n" +
                    "vozilo.proizvodjac=proizvodjac.id and \n" +
                    "vozilo.vlasnik=vlasnik.id and \n" +
                    "vlasnik.mjesto_rodjenja=mjesto.id) join mjesto on\n" +
                    "mjesto_prebivalista=mjesto.id;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proizvodjac p = new Proizvodjac(rs.getInt(2), rs.getString(8));
                Mjesto mjestoRodjenja = new Mjesto(rs.getInt(18), rs.getString(19), rs.getString(20));
                Mjesto mjestoPrebivalista = new Mjesto(rs.getInt(21), rs.getString(22), rs.getString(23));
                LocalDate datumRodjenja = Instant.ofEpochMilli(Integer.valueOf(
                        rs.getString(13))).atZone(ZoneId.systemDefault()).toLocalDate();
                Vlasnik vlasnik = new Vlasnik(rs.getInt(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), datumRodjenja, mjestoRodjenja, rs.getString(15),
                        mjestoPrebivalista, rs.getString(17));
                Vozilo vozilo = new Vozilo(rs.getInt(1), p, rs.getString(3), rs.getString(4),
                        rs.getString(5), vlasnik);
                res.add(vozilo);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Mjesto> getMjesta() {
        ArrayList<Mjesto> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, naziv, postanski_broj FROM mjesto " +
                    "ORDER BY naziv ASC");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Mjesto m = new Mjesto(rs.getInt(1), rs.getString(2), rs.getString(3));
                res.add(m);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ObservableList<Proizvodjac> getProizvodjaci() {
        ArrayList<Proizvodjac> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM proizvodjac " +
                    "ORDER BY naziv ASC");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proizvodjac m = new Proizvodjac(rs.getInt(1), rs.getString(2));
                res.add(m);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }*/

    private int highestIdAirline() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from airline_companies");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdAirplane() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from planes");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdFlights() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from flights");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdFlightTypes() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from flight_types");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdLuggage() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from luggages");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdPassenger() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from passengers");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdRole() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from roles");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private int highestIdUser() {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT max(id) from users");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }


/*
    private void dodajMjesto(Mjesto mjesto) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO mjesto(id, naziv, postanski_broj) VALUES(?,?,?)");
            stmt.setInt(1, mjesto.getId());
            stmt.setString(2, mjesto.getNaziv());
            stmt.setString(3, mjesto.getPostanskiBroj());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void dodajVlasnika(Vlasnik vlasnik) {
        if (vlasnik.getMjestoRodjenja().getId() == 0) {
            vlasnik.getMjestoRodjenja().setId(dajNajveciIdMjesta() + 1);
            dodajMjesto(vlasnik.getMjestoRodjenja());
        }
        if (vlasnik.getMjestoPrebivalista().getId() == 0) {
            vlasnik.getMjestoPrebivalista().setId(dajNajveciIdMjesta() + 1);
            dodajMjesto(vlasnik.getMjestoPrebivalista());
        }
        vlasnik.setId(dajNajveciIdVlasnika()+1);
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO vlasnik(id, ime, prezime, " +
                    "ime_roditelja, datum_rodjenja, mjesto_rodjenja, adresa_prebivalista, mjesto_prebivalista," +
                    "jmbg) VALUES(?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, vlasnik.getId());
            stmt.setString(2, vlasnik.getIme());
            stmt.setString(3, vlasnik.getPrezime());
            stmt.setString(4, vlasnik.getImeRoditelja());
            stmt.setString(5, String.valueOf(vlasnik.getDatumRodjenja().atStartOfDay(
                    ZoneId.systemDefault()).toEpochSecond()));
            stmt.setInt(6, vlasnik.getMjestoRodjenja().getId());
            stmt.setString(7, vlasnik.getAdresaPrebivalista());
            stmt.setInt(8, vlasnik.getMjestoPrebivalista().getId());
            stmt.setString(9, vlasnik.getJmbg());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void promijeniVlasnika(Vlasnik vlasnik) {
        try {

            if (vlasnik.getMjestoRodjenja().getId() == 0) {
                vlasnik.getMjestoRodjenja().setId(dajNajveciIdMjesta() + 1);
                dodajMjesto(vlasnik.getMjestoRodjenja());
            }

            if (vlasnik.getMjestoPrebivalista().getId() == 0) {
                vlasnik.getMjestoPrebivalista().setId(dajNajveciIdMjesta() + 1);
                dodajMjesto(vlasnik.getMjestoPrebivalista());
            }

            PreparedStatement stmt = conn.prepareStatement("UPDATE vlasnik SET ime=?, prezime=?, " +
                    "ime_roditelja=?, datum_rodjenja=?, mjesto_rodjenja=?, adresa_prebivalista=?, mjesto_prebivalista=?," +
                    "jmbg=? WHERE id=?");
            stmt.setString(1, vlasnik.getIme());
            stmt.setString(2, vlasnik.getPrezime());
            stmt.setString(3, vlasnik.getImeRoditelja());
            stmt.setString(4, String.valueOf(vlasnik.getDatumRodjenja().atStartOfDay(
                    ZoneId.systemDefault()).toEpochSecond()));
            stmt.setInt(5, vlasnik.getMjestoRodjenja().getId());
            stmt.setString(6, vlasnik.getAdresaPrebivalista());
            stmt.setInt(7, vlasnik.getMjestoPrebivalista().getId());
            stmt.setString(8, vlasnik.getJmbg());
            stmt.setInt(9, vlasnik.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }*/

    private Boolean doesAirlineExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from airline_companies WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesAirplaneExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from planes WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesFlightExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from flights WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesLuggageExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from luggages WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesRoleExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from roles WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesPassengerExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from passengers WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesUserExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from users WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Boolean doesFlightTypeExist(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from flight_types WHERE id=?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void deleteFlight(Flight flight) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM flights WHERE id=?");
            stmt.setInt(1, flight.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAirplane(Airplane airplane) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM planes WHERE id=?");
            stmt.setInt(1, airplane.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAirline(Airline airline) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM airline_companies WHERE id=?");
            stmt.setInt(1, airline.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteLuggage(Luggage luggage) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM luggages WHERE id=?");
            stmt.setInt(1, luggage.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deletePassenger(Passenger passenger) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM passengers WHERE id=?");
            stmt.setInt(1, passenger.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteFlightType(FlightType flightType) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM flight_types WHERE id=?");
            stmt.setInt(1, flightType.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteRole(Role role) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM roles WHERE id=?");
            stmt.setInt(1, role.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteUser(User user) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id=?");
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    /*
    private boolean daLiPosjedujeVozilo(Vlasnik vlasnik) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT id from vozilo WHERE vlasnik=?");
            stmt.setInt(1, vlasnik.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
*/

    public void addPassenger(Passenger passenger) {
     /*   if (!daLiPostojiVlasnik(vozilo.getVlasnik().getId())) {
            throw new IllegalArgumentException("Ne postoji vlasnik!");
        }
        if (vozilo.getId() == 0) {
            vozilo.setId(dajNajveciIdVozila()+1);
        }
        if (vozilo.getProizvodjac().getId() == 0) {
            vozilo.getProizvodjac().setId(dajNajveciIdProizvodjac()+1);
            dodajProizvodjac(vozilo.getProizvodjac());
        }*/
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO passengers(id, name, flight) VALUES(?,?,?)");
            stmt.setInt(1, passenger.getId());
            stmt.setString(2, passenger.getName());
            stmt.setInt(3, passenger.getFlight().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
/*
    private void dodajProizvodjac(Proizvodjac proizvodjac) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO proizvodjac(id, naziv) VALUES(?,?)");
            stmt.setInt(1, proizvodjac.getId());
            stmt.setString(2, proizvodjac.getNaziv());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
*/

    public void changePassenger(Passenger passenger) {
        try {
            /*if (vozilo.getProizvodjac().getId() == 0) {
                vozilo.getProizvodjac().setId(dajNajveciIdProizvodjac()+1);
                dodajProizvodjac(vozilo.getProizvodjac());
            }*/

            PreparedStatement stmt = conn.prepareStatement("UPDATE passengers SET id=?, name=? WHERE id=?");

            stmt.setInt(1, passenger.getId());
            stmt.setString(2, passenger.getName());
            stmt.setInt(3, passenger.getFlight().getId());
            stmt.setInt(4, passenger.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void close() {
        try {
            conn.close();
            conn = null;
            instance = null;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
