package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.TimeZone;

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

    public Airplane getPlaneById(int id) {

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

    public User getUserById(int id) {

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from users join roles " +
                    "on users.role = roles.id where users.id = ?");

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

    public ObservableList<Flight> getFlights() {
        ArrayList<Flight> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from flights join flight_types, gates on \n" +
                    "flights.flight_type=flight_types.id and flights.gate=gates.id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Airplane airplane = getPlaneById(rs.getInt(3));
                User user = getUserById(rs.getInt(7));
                LocalDateTime startOfUsingTheRunway = LocalDateTime.ofInstant(Instant.ofEpochSecond(rs.getLong(4)),
                        TimeZone.getDefault().toZoneId());
                LocalDateTime endOfUsingTheRunway = LocalDateTime.ofInstant(Instant.ofEpochSecond(rs.getLong(5)),
                        TimeZone.getDefault().toZoneId());
                FlightType flightType = new FlightType(rs.getInt(8), rs.getString(9));
                Gate gate = new Gate(rs.getInt(11), rs.getString(12));
                Flight flight = new Flight(rs.getInt(1), rs.getString(2), airplane,
                                        startOfUsingTheRunway, endOfUsingTheRunway, flightType, user, gate);
                res.add(flight);

            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Flight getFlightById(int id) {

        try {
            PreparedStatement stmt = conn.prepareStatement("select * from flights join flight_types, gates on " +
                    "flights.flight_type=flight_types.id and flights.gate=gates.id where flights.id=?");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Airplane airplane = getPlaneById(rs.getInt(3));
                User user = getUserById(rs.getInt(7));
                LocalDateTime startOfUsingTheRunway = LocalDateTime.ofInstant(Instant.ofEpochSecond(rs.getLong(4)),
                        TimeZone.getDefault().toZoneId());
                LocalDateTime endOfUsingTheRunway = LocalDateTime.ofInstant(Instant.ofEpochSecond(rs.getLong(5)),
                        TimeZone.getDefault().toZoneId());
                FlightType flightType = new FlightType(rs.getInt(8), rs.getString(9));
                Gate gate = new Gate(rs.getInt(11), rs.getString(12));
                return new Flight(rs.getInt(1), rs.getString(2), airplane,
                        startOfUsingTheRunway, endOfUsingTheRunway, flightType, user, gate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public ObservableList<Luggage> getLuggages() {
        ArrayList<Luggage> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from luggages join passengers p " +
                    "on luggages.passenger = p.id");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //Image img = new Image(new ByteArrayInputStream(rs.getBytes(6)));
                Flight flight = getFlightById(rs.getInt(5));
                Passenger passenger = new Passenger(rs.getInt(3), rs.getString(4), flight,
                        null);
                Luggage luggage = new Luggage(rs.getInt(1), passenger);
                res.add(luggage);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException | IllegalCode ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<User> getUsers() {
        ArrayList<User> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from users join roles r " +
                    "on users.role = r.id)");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Role ro = new Role(rs.getInt(4), rs.getString(5));
                User u = new User(rs.getInt(1), rs.getString(2), ro);
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
                Airline airline = new Airline(rs.getInt(6), rs.getString(7), rs.getString(8));
                Airplane airplane = new Airplane(rs.getInt(1), airline, rs.getString(3),
                        rs.getString(4), rs.getInt(5));
                res.add(airplane);
            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException | IllegalNumberOfSeats ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ObservableList<Passenger> getPassengers() {
        ArrayList<Passenger> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from passengers");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Image img;
                byte[] buf = rs.getBytes(4);
                if (buf != null) {
                    img = Utils.getImageFromByteArray(buf);
                } else {
                    img = null;
                }
                Flight flight = getFlightById(rs.getInt(3));
                Passenger passenger = new Passenger(rs.getInt(1), rs.getString(2), flight,
                        img);
                res.add(passenger);

            }
            return FXCollections.observableArrayList(res);
        } catch (SQLException | IllegalCode ex) {
            ex.printStackTrace();
        }
        return null;
    }

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

    public void addUser(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users(id, name, role) VALUES(?,?,?)");
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setInt(3, user.getRole().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAirplane(Airplane airplane) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO planes(id, airline_company," +
                    "supplier, type, seats) VALUES(?,?,?,?,?)");
            stmt.setInt(1, airplane.getId());
            stmt.setInt(2, airplane.getAirline().getId());
            stmt.setString(3, airplane.getManufacturer());
            stmt.setString(4, airplane.getType());
            stmt.setInt(5, airplane.getNumberOfSeats());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAirline(Airline airline) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO airline_companies(id, name, code) VALUES(?,?,?)");
            stmt.setInt(1, airline.getId());
            stmt.setString(2, airline.getName());
            stmt.setString(3, airline.getCode());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addLuggage(Luggage luggage) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO luggages(id, passenger) VALUES(?,?)");
            stmt.setInt(1, luggage.getId());
            stmt.setInt(2, luggage.getPassenger().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addFlightType(FlightType flightType) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO flight_types(id, name) " +
                    "VALUES(?,?)");
            stmt.setInt(1, flightType.getId());
            stmt.setString(2, flightType.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addFlight(Flight flight) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO flights (id,flight_number, plane, " +
                    "runway_occupy_start, runway_occupy_end,flight_type,reg_user VALUES(?,?,?,?,?,?,?)");
            stmt.setInt(1, flight.getId());
            stmt.setString(2, flight.getCode());
            stmt.setInt(3, flight.getAirplane().getId());
            stmt.setString(4, String.valueOf(flight.getStartOfUsingTheRunway().
                    atZone(ZoneId.systemDefault()).toEpochSecond()));
            stmt.setString(5, String.valueOf(flight.getEndOfUsingTheRunway().
                    atZone(ZoneId.systemDefault()).toEpochSecond()));
            stmt.setInt(6, flight.getFlightType().getId());
            stmt.setInt(7, flight.getUser().getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addRole(Role role) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO roles(id, name) VALUES(?,?)");
            stmt.setInt(1, role.getId());
            stmt.setString(2, role.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changePassenger(Passenger passenger) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE passengers SET name=?, flight=?," +
                    "qr_code=? WHERE id=?");

            stmt.setString(1, passenger.getName());
            stmt.setInt(2, passenger.getFlight().getId());
            stmt.setBytes(3, Utils.getByteArrayFromImage(passenger.getQrCode()));
            stmt.setInt(4, passenger.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeAirplane(Airplane airplane) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE planes SET airline_company=?," +
                    "supplier=?, type=?, seats=? WHERE id=?");


            stmt.setInt(1, airplane.getAirline().getId());
            stmt.setString(2, airplane.getManufacturer());
            stmt.setString(3, airplane.getType());
            stmt.setInt(4, airplane.getNumberOfSeats());
            stmt.setInt(5, airplane.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeAirline(Airline airline) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE airline_companies SET name=?, code=? WHERE id=?");

            stmt.setString(1, airline.getName());
            stmt.setString(2, airline.getCode());
            stmt.setInt(3, airline.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeFlight(Flight flight) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE flights SET flight_number=?," +
                    "plane=?, runway_occupy_start=?, runway_occupy_end=?, flight_type=?, reg_user=? WHERE id=?");


            stmt.setString(1, flight.getCode());
            stmt.setInt(2, flight.getAirplane().getId());
            stmt.setString(3, String.valueOf(flight.getStartOfUsingTheRunway().
                    atZone(ZoneId.systemDefault()).toEpochSecond()));
            stmt.setString(4, String.valueOf(flight.getEndOfUsingTheRunway().
                    atZone(ZoneId.systemDefault()).toEpochSecond()));
            stmt.setInt(5, flight.getFlightType().getId());
            stmt.setInt(6, flight.getUser().getId());
            stmt.setInt(7, flight.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeFlightType(FlightType flightType) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE flight_types SET name=? WHERE id=?");

            stmt.setString(1, flightType.getName());
            stmt.setInt(2, flightType.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeLuggage(Luggage luggage) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE luggages SET passenger=? WHERE id=?");


            stmt.setInt(1, luggage.getPassenger().getId());
            stmt.setInt(2, luggage.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeRole(Role role) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE roles SET name=? WHERE id=?");


            stmt.setString(1, role.getName());
            stmt.setInt(2, role.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeUser(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET name=?, role=? WHERE id=?");


            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getRole().getId());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addPassenger(Passenger passenger) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO passengers(id, name, flight, qr_code) \n" +
                    "VALUES(?,?,?,?)");
            stmt.setInt(1, passenger.getId());
            stmt.setString(2, passenger.getName());
            stmt.setInt(3, passenger.getFlight().getId());
            stmt.setBytes(4, Utils.getByteArrayFromImage(passenger.getQrCode()));
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteGate(Gate gate) {

        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM gates WHERE id=?");
            stmt.setInt(1, gate.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addGate(Gate gate) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO gates(id, name) VALUES(?,?)");
            stmt.setInt(1, gate.getId());
            stmt.setString(2, gate.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void changeGate(Gate gate) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE gates SET name=? WHERE id=?");


            stmt.setString(1, gate.getName());
            stmt.setInt(2, gate.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
