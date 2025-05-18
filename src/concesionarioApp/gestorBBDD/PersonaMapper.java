package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaMapper {
    public Persona toObject(ResultSet rs) throws SQLException {
        return new Persona(
                rs.getString("DNI"),
                rs.getString("NOMBRE"),
                rs.getString("APELLIDOS")
        );
    }
}
