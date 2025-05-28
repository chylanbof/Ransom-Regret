package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/juego";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql";

    public static void guardarPartida(int totalTimePlayed, int level, int coin) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO partidas (totalTimePlayed, nivel, coin) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, totalTimePlayed);
            stmt.setInt(2, level);
            stmt.setInt(3,coin);

            stmt.executeUpdate();
            System.out.println("✅ Partida guardada en la base de datos");

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}