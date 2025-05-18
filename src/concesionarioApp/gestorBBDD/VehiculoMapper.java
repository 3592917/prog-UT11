package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Persona;
import concesionarioApp.dominio.Vehiculo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoMapper {
    PropietarioDAO propietarioDAO;

    public VehiculoMapper(PropietarioDAO propietarioDAO) {
        this.propietarioDAO = propietarioDAO;
    }

    public Vehiculo toObject(ResultSet rs) throws SQLException {
        Persona propietario = null;

        if (propietarioDAO != null) {
            propietario = propietarioDAO.getPersonaPorDNI(rs.getString("DNI_PROP"));
        }

        return new Vehiculo(
                rs.getString("MATRICULA"),
                rs.getString("MARCA"),
                rs.getInt("ANIO_MATRICULACION"),
                rs.getDouble("PRECIO_VENTA"),
                rs.getInt("KILOMETRAJE"),
                propietario
        );
    }
}
