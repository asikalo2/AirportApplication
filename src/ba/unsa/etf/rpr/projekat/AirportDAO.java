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
            String url = "jdbc:sqlite:resources/AirportDB.db";
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

    public Airplane getPlaneById(int id){

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from planes join airline_companies " +
                    "on planes.airline_company=airline_companies.id where planes.id = ?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Airline airline = new Airline();
            Airplane airplane = new Airplane();

            while (rs.next()) {
                airplane.setId(id);
                airline.setId(rs.getInt(2));
                airline.setName(rs.getString(7));
                airline.setCode(rs.getString(8));
                airplane.setAirline(airline);
                airplane.setManufacturer(rs.getString(3));
                airplane.setType(rs.getString(4));
                airplane.setNumberOfSeats(rs.getInt(5));
            }
            return airplane;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id){

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from users join roles " +
                    "on users.role = roles.id; where planes.id = ?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            User user = new User();
            Role role = new Role();

            while (rs.next()) {
                user.setId(id);
                role.setId(rs.getInt(4));
                role.setName(rs.getString(5));
                user.setName(rs.getString(2));
                user.setRole(role);
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

  /*  public Passenger getPassengerById(int id){

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from passengers join flights f " +
                    "on passengers.flight = f.idairline_companies where passengers.id = ?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Passenger passenger = new Passenger();
            Flight flight = new Flight();

            while (rs.next()) {
                passenger.setId(id);
                flight.setId(rs.getInt(2));
                flight.setName(rs.getString(7));
                flight.setCode(rs.getString(8));
                passenger.setName(rs.getString(2));
                passenger.setFlight(flight);
            }
            return passenger;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }*/

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
    public ObservableList<Flight> getFlights() {
        int i = 0;
        ArrayList<Flight> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from flights");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Airline airline = new Airline(rs.getInt(1), rs.getString(2), rs.getString(3));
                Airplane airplane = new Airplane(rs.getInt(1), airline, rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                Role role = new Role(rs.getInt(1), rs.getString(2));
                User user = new User(rs.getInt(1), rs.getString(2), role);
                LocalDate startOfUsingTheRunway = Instant.ofEpochMilli(Integer.valueOf(
                        rs.getString(13))).atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate endOfUsingTheRunway = Instant.ofEpochMilli(Integer.valueOf(
                        rs.getString(13))).atZone(ZoneId.systemDefault()).toLocalDate();
                FlightType flightType = new FlightType(rs.getInt(1), rs.getString(2));
                Flight flight = new Flight(rs.getInt(1), airplane, rs.getString(2),
                        startOfUsingTheRunway, endOfUsingTheRunway, flightType, user);
                res.add(flight)

            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }*/
/*
    public ObservableList<Luggage> getLuggages() {
        ArrayList<Luggage> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from luggages join passengers p," +
                    "" +
                    "" +
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
    }*/

    public ObservableList<User> getUsers() {
        ArrayList<User> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from users join roles r " +
                    "on users.role = r.id)");
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

    public ObservableList<Airplane> getAirplanes() {
        ArrayList<Airplane> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from planes join airline_companies a " +
                    "on planes.airline_company = a.id)");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Airline airline = new Airline(rs.getInt(1), rs.getString(2), rs.getString(3));
                Airplane airplane = new Airplane(rs.getInt(1), airline, rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                res.add(airplane);
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

    public void addPassenger(Passenger passenger)
    {
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

    public void changePassenger(Passenger passenger) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE passengers SET id=?, name=? WHERE id=?");

            stmt.setInt(1, passenger.getId());
            stmt.setString(2, passenger.getName());
            stmt.setInt(3, passenger.getFlight().getId());
          //  stmt.setInt(4, passenger.getId());

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
