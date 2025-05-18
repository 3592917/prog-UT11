package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Vehiculo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    private static final Integer OK = 0;
    private static final Integer KO = -1;

    private final Connection conn;

    PropietarioDAO propietarioDAO;
    VehiculoMapper mapper;

    public VehiculoDAO(Connection conn) {
        this.conn = conn;
        this.propietarioDAO = new PropietarioDAO(conn);
        this.mapper = new VehiculoMapper(propietarioDAO);
    }

    public Integer insertarVehiculo(Vehiculo nuevoVehiculo) {
        int resultado = KO;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO vehiculos " +
                    "(matricula, " +
                    "kilometraje, " +
                    "marca, " +
                    "anio_matriculacion, " +
                    "precio_venta) " +
                    "VALUES " +
                    "('" + nuevoVehiculo.getMatricula() + "', " +
                    "'" + nuevoVehiculo.getKilometraje() + "', " +
                    "'" + nuevoVehiculo.getMarca() + "', " +
                    nuevoVehiculo.getAnyoMatriculacion() + ", " +
                    nuevoVehiculo.getPrecioVenta() + ");");
            resultado = OK;
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public Vehiculo getVehiculoPorMatricula(String matricula) {
        Vehiculo vehiculo = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM vehiculos where matricula = '" + matricula + "';");
            vehiculo = rs.next() ? mapper.toObject(rs) : null;
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculo;
    }

    public Integer updatePropietarioMatriculaDNI(String dni, String matricula) {
        int resultado = KO;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE vehiculos SET dni_prop = '" + dni + "' WHERE matricula = '" + matricula + "';");
            resultado = OK;
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public List<String> getVehiculosPorDni(String dni) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        List<String> vehiculosString = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM vehiculos where dni_prop = '" + dni + "';"
            );
            while (rs.next()) {
                vehiculos.add(mapper.toObject(rs));
            }
            if (!vehiculos.isEmpty()) {
                vehiculosString.add(vehiculos.toString());
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculosString;
    }

    public Integer eliminarVehiculo(String matricula) {
        int resultado = 0;
        try {
            Statement stmt = conn.createStatement();
            resultado = stmt.executeUpdate("DELETE FROM vehiculos WHERE matricula = '" + matricula + "';");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public List<String> getVehiculosPorMarca(String marca) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        List<String> vehiculosString = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM vehiculos where marca = '" + marca + "';"
            );
            while (rs.next()) {
                vehiculos.add(mapper.toObject(rs));
            }
            if (!vehiculos.isEmpty()) {
                vehiculosString.add(vehiculos.toString());
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculosString;
    }

    public List<String> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        List<String> vehiculosString = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM vehiculos;"
            );
            while (rs.next()) {
                vehiculos.add(mapper.toObject(rs));
            }
            if (!vehiculos.isEmpty()) {
                vehiculosString.add(vehiculos.toString());
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculosString;
    }
}
