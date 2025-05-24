package concesionarioApp.gestorBBDD;

import concesionarioApp.dominio.Vehiculo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    private final ConexionBBDD conn;

    private final VehiculoMapper mapper;

    public VehiculoDAO(ConexionBBDD conn) {
        this.conn = conn;
        this.mapper = new VehiculoMapper();
    }

    public Integer insertarVehiculo(Vehiculo nuevoVehiculo) {
        int resultado = 0;
        try {
            Statement stmt = conn.getConexion().createStatement();
            resultado = stmt.executeUpdate("INSERT INTO vehiculos " +
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

            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public Vehiculo getVehiculoPorMatricula(String matricula) {
        Vehiculo vehiculo = null;
        try {
            Statement stmt = conn.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " +
                    "   v.matricula, " +
                    "   v.kilometraje, " +
                    "   v.marca, " +
                    "    v.anio_matriculacion, " +
                    "    v.precio_venta, " +
                    "    p.dni, " +
                    "    p.nombre, " +
                    "    p.apellidos " +
                    "FROM vehiculos v " +
                    "LEFT JOIN propietarios p ON p.dni = v.dni_prop " +
                    " WHERE matricula = '" + matricula + "';");
            vehiculo = rs.next() ? mapper.toObject(rs) : null;
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculo;
    }

    public Integer updatePropietarioMatriculaDNI(String dni, String matricula) {
        int resultado = 0;
        try {
            Statement stmt = conn.getConexion().createStatement();
            resultado = stmt.executeUpdate("UPDATE vehiculos SET dni_prop = '" + dni + "' WHERE matricula = '" + matricula + "';");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public List<Vehiculo> getVehiculosPorDni(String dni) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try {
            Statement stmt = conn.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT " +
                            "   v.matricula, " +
                            "   v.kilometraje, " +
                            "   v.marca, " +
                            "    v.anio_matriculacion, " +
                            "    v.precio_venta, " +
                            "    p.dni, " +
                            "    p.nombre, " +
                            "    p.apellidos " +
                            "FROM vehiculos v " +
                            "LEFT JOIN propietarios p ON p.dni = v.dni_prop " +
                            " WHERE dni_prop = '" + dni + "';"
            );
            while (rs.next()) {
                vehiculos.add(mapper.toObject(rs));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }

    public Integer eliminarVehiculo(String matricula) {
        int resultado = 0;
        try {
            Statement stmt = conn.getConexion().createStatement();
            resultado = stmt.executeUpdate("DELETE FROM vehiculos WHERE matricula = '" + matricula + "';");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultado;
    }

    public List<Vehiculo> getVehiculosPorMarca(String marca) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try {
            Statement stmt = conn.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT " +
                            "   v.matricula, " +
                            "   v.kilometraje, " +
                            "   v.marca, " +
                            "    v.anio_matriculacion, " +
                            "    v.precio_venta, " +
                            "    p.dni, " +
                            "    p.nombre, " +
                            "    p.apellidos " +
                            "FROM vehiculos v " +
                            "LEFT JOIN propietarios p ON p.dni = v.dni_prop " +
                            "WHERE marca = '" + marca + "';"
            );
            while (rs.next()) {
                vehiculos.add(mapper.toObject(rs));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }

    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        try {
            Statement stmt = conn.getConexion().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT " +
                            "   v.matricula, " +
                            "   v.kilometraje, " +
                            "   v.marca, " +
                            "    v.anio_matriculacion, " +
                            "    v.precio_venta, " +
                            "    p.dni, " +
                            "    p.nombre, " +
                            "    p.apellidos " +
                            "FROM vehiculos v " +
                            "LEFT JOIN propietarios p ON p.dni = v.dni_prop;"
            );
            while (rs.next()) {
                vehiculos.add(mapper.toObject(rs));
            }
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return vehiculos;
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
