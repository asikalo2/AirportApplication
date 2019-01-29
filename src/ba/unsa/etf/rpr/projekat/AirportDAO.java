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

    public ObservableList<Passenger> getPassengers() {
        ArrayList<Passenger> res = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("select * from (select * from passengers join flights f " +
                    "on passengers.flight = f.id");
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

    @Override
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

    @Override
    public void addPassenger(Passenger passenger) {

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO passengers(id, name, flight) VALUES(?,?,?)");
            stmt.setInt(1, passenger.getId());
            stmt.setString(2, passenger.getName());
            stmt.setString(3, passenger.getFlight().getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void changePassenger(Passenger passenger) {
        try {PreparedStatement stmt = conn.prepareStatement("UPDATE passengers SET id=?, name=? WHERE id=?");

            stmt.setInt(1, passenger.getId());
            stmt.setString(2, passenger.getName());
            stmt.setInt(3, passenger.getFlight().getId());
            stmt.setInt(4, passenger.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deletePassenger(Passenger passenger) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM passengers WHERE id=?");
            stmt.setInt(1, passenger.getId());
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
