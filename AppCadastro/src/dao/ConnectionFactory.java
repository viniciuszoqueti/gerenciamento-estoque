package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionFactory {

   // private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/db_cadastro?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "vi-963852741";

    public static Connection getConnection() throws InstantiationException, IllegalAccessException {
        try {
            
           Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null,  ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex);

        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar finalizar conexão! (Connection)");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar finalizar conexão! (PreparedStatement)");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar finalizar conexão! (Resullt)");
        }
    }
}
