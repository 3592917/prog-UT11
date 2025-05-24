package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropietarioDAO {

    private final ConexionBBDD conn;

    public PropietarioDAO(ConexionBBDD conn) {
        this.conn = conn;
    }

    public Persona getPersonaPorDNI(String dni) {
        Persona persona = null;
        PersonaMapper mapper = new PersonaMapper();
        try {
            Statement stmt = conn.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM propietarios where dni = '" + dni + "';");
            persona = rs.next() ? mapper.toObject(rs) : null;
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return persona;
    }

    public Integer insertarPropietario(Persona nuevoPropietario) {
        int resultado = 0;
        try {
            Statement stmt = conn.getConexion().createStatement();
            resultado = stmt.executeUpdate("INSERT INTO propietarios " +
                    "(dni, nombre, apellidos) " +
                    "VALUES " +
                    "('" + nuevoPropietario.getDni() + "', " +
                    "'" + nuevoPropietario.getNombre() + "', " +
                    "'" + nuevoPropietario.getApellidos() + "');");

            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public Integer eliminarPropietario(String dni) {
        int resultado = 0;
        try {
            Statement stmt = conn.getConexion().createStatement();
            resultado = stmt.executeUpdate("DELETE FROM propietarios WHERE dni = '" + dni + "';");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public void cerrarConexion() {
        try {
            conn.cerrarConexion();
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage() +
                    " ," + e.getErrorCode());
        }
    }
}
