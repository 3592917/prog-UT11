package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Persona;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PropietarioDAO {
    private static final Integer OK = 0;
    private static final Integer KO = -1;

    private final Connection conn;

    public PropietarioDAO(Connection conn) {
        this.conn = conn;
    }

    public Persona getPersonaPorDNI(String dni) {
        Persona persona = null;
        PersonaMapper mapper = new PersonaMapper();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM propietarios where dni = '" + dni + "';");
            persona = rs.next() ? mapper.toObject(rs) : null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return persona;
    }

    public Integer insertarPropietario(Persona nuevoPropietario) {
        int resultado = KO;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO propietarios " +
                    "(dni, nombre, apellidos) " +
                    "VALUES " +
                    "('" + nuevoPropietario.getDni() + "', " +
                    "'" + nuevoPropietario.getNombre() + "', " +
                    "'" + nuevoPropietario.getApellidos() + "');");
            resultado = OK;
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public Integer eliminarPropietario(String dni) {
        int resultado = 0;
        try {
            Statement stmt = conn.createStatement();
            resultado = stmt.executeUpdate("DELETE FROM propietarios WHERE dni = '" + dni + "';");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }
}
