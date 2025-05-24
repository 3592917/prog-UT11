package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Vehiculo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoMapper {
    private final PersonaMapper mapperP = new PersonaMapper();

    public Vehiculo toObject(ResultSet rs) throws SQLException {

        return new Vehiculo(
                rs.getString("MATRICULA"),
                rs.getString("MARCA"),
                rs.getInt("ANIO_MATRICULACION"),
                rs.getDouble("PRECIO_VENTA"),
                rs.getInt("KILOMETRAJE"),
                mapperP.toObject(rs)
        );
    }
}
