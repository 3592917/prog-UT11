package concesionarioApp.gestorBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
    private Connection conn;

    public ConexionBBDD() {
        try {
            String url = "jdbc:mysql://localhost:3306/concesionario";
            String usuario = "root";
            String password = "";

            conn = DriverManager.getConnection(url, usuario, password);

            System.out.println("Se ha establecido la conexión con la base de datos: " + conn.getMetaData().getDatabaseProductName() +
                    conn.getMetaData().getURL());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cerrarConexion() throws SQLException {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConexion() throws SQLException {
        return conn;
    }
}
