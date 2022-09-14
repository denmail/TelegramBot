package ru.primath.Manager;

import ru.primath.Objects.Primat;
import ru.primath.TelebotApplication;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    static String url = "jdbc:mysql://localhost/primath_db";
    static String user = "admin";
    static String password = "admin";



    public static void primatToDB(byte subGroup, String username, String name, long chatId, String role) {
        String query = "INSERT INTO makara(username, sub_group, name_, chatId, role_) VALUES(?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, username);
            pst.setByte(2, subGroup);
            pst.setString(3, name);
            pst.setLong(4, chatId);
            pst.setString(5, role);
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(TelebotApplication.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static void loadPrimatsFromDB() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM makara");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Primat primat = new Primat(rs.getByte(2),
                        rs.getString(1),
                        rs.getString(3),
                        rs.getLong(4),
                        rs.getString(5));
                PrimatManager.addPrimat(primat);
            }
            System.out.println("LOADED FROM DB");

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(TelebotApplication.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }


}
